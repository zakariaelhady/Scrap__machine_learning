package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import load.loader;
import ML.Classification;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Stacking;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import javax.swing.JTextPane;


public class classify extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	loader lo=new loader();
	Connection con =lo.connection();
	String nom,prenom,imgPath;
	private JPanel panel;
	String id="1";
	private JLabel lblSelectTheUrl;
	private JButton scrapbtn;
	private JTable table;
	private DefaultTableModel model;
	private JButton classifybtn;
	private static classify frame;
	private JLabel lblNewLabel_2;
	JTextPane textPane;
	String str="poste";
	Instances binary_traindata;
	Instances binary_testdata;
	DataSource source1;
	DataSource source2;
	Instances traindata;
	Instances testdata;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new classify();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public classify() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1321, 787);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profile i=new profile();
				i.getid(id);
	    		i.show();
//	    		frame.dispose();
			}
		});
		panel.setBounds(1045, 10, 205, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(46, 0, 159, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Choose the label : ");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.setBounds(64, 152, 196, 39);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Choose the classifier : ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_2.setBounds(289, 152, 222, 39);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setToolTipText("label\r\n");
		comboBox.setFont(new Font("Verdana", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"poste", "localisation", "domaine", "contrat", "entreprise", "salaire", "niveau_etude", "competences"}));
		comboBox.setBounds(53, 210, 196, 32);
		contentPane.add(comboBox);
		
		
		JButton j48btn = new JButton("J48");
		j48btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 //data###################
					source1 = new DataSource("train.arff");
					traindata = source1.getDataSet();
					source2 = new DataSource("test.arff");
					testdata = source2.getDataSet();
					
					// nominal to binary###############
			 		binary_traindata=Classification.nominaltobinary(traindata,str);
			 		binary_testdata=Classification.nominaltobinary(testdata,str);
					
			 		/*
					 * Label selection#############
					 */
					binary_traindata.setClassIndex(binary_traindata.attribute(str).index());
					binary_testdata.setClassIndex(binary_testdata.attribute(str).index());
					
					J48 tree=(J48)weka.core.SerializationHelper.read("models/"+str+"/j48.model");
					/*
					 * Visualize decision tree
					 */
//					TreeVisualizer tv = new TreeVisualizer(null, tree.graph(),
//							new PlaceNode2());
//					JFrame frame = new javax.swing.JFrame("Tree Visualizer");
//					frame.setSize(800, 500);
//					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					frame.getContentPane().add(tv);
//					frame.setVisible(true);
//					tv.fitToScreen();
					
					J48 cl=new J48();
					Evaluation eval = new Evaluation(binary_traindata);
					eval.crossValidateModel(cl, binary_testdata, 10, new Random(1), new Object[] {});
					textPane.setText(eval.toSummaryString()+eval.toMatrixString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		j48btn.setForeground(Color.WHITE);
		j48btn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		j48btn.setBackground(SystemColor.windowBorder);
		j48btn.setBounds(299, 205, 175, 41);
		contentPane.add(j48btn);
		
		JButton randomforestbtn = new JButton("RandomForest");
		randomforestbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 //data###################
					source1 = new DataSource("train.arff");
					traindata = source1.getDataSet();
					source2 = new DataSource("test.arff");
					testdata = source2.getDataSet();
					
			 		/*
					 * Label selection#############
					 */
					traindata.setClassIndex(traindata.attribute(str).index());
					testdata.setClassIndex(testdata.attribute(str).index());
					
					RandomForest randomforest=(RandomForest)weka.core.SerializationHelper.read("models/"+str+"/randomforest.model");
					Evaluation eval = new Evaluation(traindata);
					eval.crossValidateModel(randomforest, testdata, 10, new Random(1), new Object[] {});
					textPane.setText(eval.toSummaryString()+eval.toMatrixString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		randomforestbtn.setForeground(Color.WHITE);
		randomforestbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		randomforestbtn.setBackground(SystemColor.windowBorder);
		randomforestbtn.setBounds(299, 286, 175, 41);
		contentPane.add(randomforestbtn);
		
		JButton stackingbtn = new JButton("Stacking");
		stackingbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 //data###################
					source1 = new DataSource("train.arff");
					traindata = source1.getDataSet();
					source2 = new DataSource("test.arff");
					testdata = source2.getDataSet();
					
			 		/*
					 * Label selection#############
					 */
					traindata.setClassIndex(traindata.attribute(str).index());
					testdata.setClassIndex(testdata.attribute(str).index());
					Stacking cl=new Stacking();
	
					Evaluation eval = new Evaluation(traindata);
					eval.crossValidateModel(cl, testdata, 10, new Random(1), new Object[] {});
					textPane.setText(eval.toSummaryString()+eval.toMatrixString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		stackingbtn.setForeground(Color.WHITE);
		stackingbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		stackingbtn.setBackground(SystemColor.windowBorder);
		stackingbtn.setBounds(299, 531, 175, 41);
		contentPane.add(stackingbtn);
		
		JButton adaboostbtn = new JButton("Adaboost");
		adaboostbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//data###################
					source1 = new DataSource("train.arff");
					traindata = source1.getDataSet();
					source2 = new DataSource("test.arff");
					testdata = source2.getDataSet();
					
			 		/*
					 * Label selection#############
					 */
					traindata.setClassIndex(traindata.attribute(str).index());
					testdata.setClassIndex(testdata.attribute(str).index());
					AdaBoostM1 cl=new AdaBoostM1();
					
					Evaluation eval = new Evaluation(traindata);
					eval.crossValidateModel(cl, testdata, 10, new Random(1), new Object[] {});
					textPane.setText(eval.toSummaryString()+eval.toMatrixString());
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		adaboostbtn.setForeground(Color.WHITE);
		adaboostbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		adaboostbtn.setBackground(SystemColor.windowBorder);
		adaboostbtn.setBounds(299, 449, 175, 41);
		contentPane.add(adaboostbtn);
		
		JButton naivebayesbtn = new JButton("NaiveBayes");
		naivebayesbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//data###################
					source1 = new DataSource("train.arff");
					traindata = source1.getDataSet();
					source2 = new DataSource("test.arff");
					testdata = source2.getDataSet();
					
			 		/*
					 * Label selection#############
					 */
					traindata.setClassIndex(traindata.attribute(str).index());
					testdata.setClassIndex(testdata.attribute(str).index());
					NaiveBayes cl=new NaiveBayes();
					
					Evaluation eval = new Evaluation(traindata);
					eval.crossValidateModel(cl, testdata, 10, new Random(1), new Object[] {});
					textPane.setText(eval.toSummaryString()+eval.toMatrixString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		naivebayesbtn.setForeground(Color.WHITE);
		naivebayesbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		naivebayesbtn.setBackground(SystemColor.windowBorder);
		naivebayesbtn.setBounds(299, 368, 175, 41);
		contentPane.add(naivebayesbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(589, 152, 648, 559);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Verdana", Font.BOLD, 14));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		JButton predictbtn = new JButton("Predict");
		predictbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				predict p=new predict();
				
				try {
					p.getid(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		p.show();
			}
		});
		predictbtn.setForeground(Color.WHITE);
		predictbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		predictbtn.setBackground(SystemColor.windowBorder);
		predictbtn.setBounds(1063, 87, 175, 41);
		contentPane.add(predictbtn);
		
		
	}
	public void getid(String id) throws SQLException {	
		this.id=id;
		ResultSet rs=lo.Selectuser(con,id);
		while (rs.next()) {
			nom=rs.getString("nom");
			prenom=rs.getString("prenom");
			imgPath=rs.getString("photo");
		}
		lblNewLabel.setText(prenom+" "+nom);
		
		JLabel profile = new JLabel();
		profile.setBounds(0, 0, 36, 32);
		ImageIcon img=new ImageIcon(imgPath);
		Image img1=img.getImage();
		Image imgscale=img1.getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon=new ImageIcon(imgscale);
		profile.setIcon(icon);
		panel.add(profile);
	}
}
