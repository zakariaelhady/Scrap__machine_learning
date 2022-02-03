package GUI;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import load.loader;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class interface1 extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	loader lo=new loader();
	Connection con =lo.connection();
	String nom,prenom,imgPath;
	private JPanel panel;
	private static interface1 frame;
	String id="1";
	private JLabel lblSelectTheUrl;
	private JButton scrapbtn;
	private JTable table;
	private DefaultTableModel model;
	private JButton classifybtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frame = new interface1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public interface1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 787);
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
		panel.setBounds(820, 10, 205, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(46, 0, 159, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
		
		lblSelectTheUrl = new JLabel("Select the URL : ");
		lblSelectTheUrl.setForeground(SystemColor.window);
		lblSelectTheUrl.setFont(new Font("Verdana", Font.BOLD, 17));
		lblSelectTheUrl.setBounds(85, 85, 272, 42);
		contentPane.add(lblSelectTheUrl);
		
		classifybtn = new JButton("Classify");
		classifybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				classify j;
				try {
					j = new classify();
					j.getid(id);
					j.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		});
		classifybtn.setForeground(SystemColor.window);
		classifybtn.setBackground(SystemColor.windowBorder);
		classifybtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		classifybtn.setBounds(523, 158, 136, 42);
		contentPane.add(classifybtn);
		classifybtn.setVisible(false);
		
		scrapbtn = new JButton("Scrap");
		scrapbtn.setForeground(SystemColor.window);
		scrapbtn.setBackground(SystemColor.windowBorder);
		scrapbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					classifybtn.setVisible(true);
				
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(87, 277, 906, 415);
					contentPane.add(scrollPane);
					//frame.getContentPane().add(scrollPane);
					
					table = new JTable();
					model=new DefaultTableModel();
					Object[] column= {"Poste","Localisation","Domaine","Contrat","Entreprise","Salaire","Niveau d'etude","Competences"};
					Object[] row=new Object[8];
					model.setColumnIdentifiers(column);
				try {
					ResultSet rs=lo.Select(con);
					while(rs.next()) {
				    	row[0]=rs.getString("poste");
				    	row[1]=rs.getString("localisation");
				    	row[2]=rs.getString("domaine");
				    	row[3]=rs.getString("contrat");
				    	row[4]=rs.getString("entreprise");
				    	row[5]=rs.getString("salaire");
				    	row[6]=rs.getString("niveau_etude");
				    	row[7]=rs.getString("competences");
				    	model.addRow(row);
				    	
					}
					table.setModel(model);
					scrollPane.setViewportView(table);	
					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}	
			}
		});
		scrapbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		scrapbtn.setBounds(333, 158, 136, 42);
		contentPane.add(scrapbtn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.window);
		comboBox.setFont(new Font("Tahoma", Font.ITALIC, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"https://www.marocannonces.com/maroc/"}));
		comboBox.setBounds(330, 88, 340, 32);
		contentPane.add(comboBox);
		
		
		
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
