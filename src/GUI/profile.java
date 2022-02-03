package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import load.loader;
import projet.user;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class profile extends JFrame {

	private JPanel contentPane;
	loader lo=new loader();
	Connection con =lo.connection();
	String nom,prenom,tel,email,password,imgPath,id;
	private JTextField nomF;
	private JTextField prenomF;
	private JTextField telF;
	private JTextField mailF;
	private JPasswordField passwordF;
	private JLabel profilepic;
	private static profile frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frame = new profile();
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
	public profile() {
		
		setBounds(100, 100, 963, 686);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nomF = new JTextField();
		nomF.setColumns(10);
		nomF.setBounds(428, 255, 259, 33);
		contentPane.add(nomF);
		
		JLabel lblLastName = new JLabel("Last Name : ");
		lblLastName.setForeground(SystemColor.window);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(235, 262, 131, 26);
		contentPane.add(lblLastName);
		
		JLabel lblprenom = new JLabel("First Name : ");
		lblprenom.setForeground(SystemColor.window);
		lblprenom.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblprenom.setBounds(235, 203, 131, 26);
		contentPane.add(lblprenom);
		
		prenomF = new JTextField();
		prenomF.setColumns(10);
		prenomF.setBounds(428, 196, 259, 33);
		contentPane.add(prenomF);
		
		telF = new JTextField();
		telF.setColumns(10);
		telF.setBounds(428, 317, 259, 33);
		contentPane.add(telF);
		
		JLabel lblPhoneN = new JLabel("Phone N\u00B0 : ");
		lblPhoneN.setForeground(SystemColor.window);
		lblPhoneN.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPhoneN.setBounds(235, 324, 131, 26);
		contentPane.add(lblPhoneN);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setForeground(SystemColor.window);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(235, 387, 131, 26);
		contentPane.add(lblEmail);
		
		mailF = new JTextField();
		mailF.setColumns(10);
		mailF.setBounds(428, 380, 259, 33);
		contentPane.add(mailF);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setForeground(SystemColor.window);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword.setBounds(235, 450, 131, 26);
		contentPane.add(lblPassword);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(428, 443, 259, 33);
		contentPane.add(passwordF);
		
		profilepic = new JLabel();
		profilepic.setBounds(368, 32, 154, 154);
		profilepic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser j = new JFileChooser(); 
				j.showSaveDialog(null);
				File f=j.getSelectedFile();
				imgPath=f.getAbsolutePath().replace("\\","/");
			}
		});
		
		JButton updatebtn = new JButton("Update");
		updatebtn.setForeground(SystemColor.window);
		updatebtn.setBackground(SystemColor.windowBorder);
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomU,prenomU,mailU,telU,passwordU,imgPathU;
				nomU=nomF.getText();
				prenomU=prenomF.getText();
				telU=telF.getText();
				mailU=mailF.getText();
				passwordU=passwordF.getText();
				imgPathU=imgPath;
				if(prenomU.equals("")||nomU.equals("")||telU.equals("")||mailU.equals("")||passwordU.equals("")) {
					JOptionPane.showMessageDialog(null, "Tout les champs doivent etre remplis");
				}
				else {
					user us=new user(prenomU,nomU,telU,mailU,passwordU,imgPathU);
					lo.Updateuser(con, id,us);
					JOptionPane.showMessageDialog(null, "Votre compte a été modifié avec succès");
				}
			}
		});
		updatebtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		updatebtn.setBounds(580, 504, 107, 33);
		contentPane.add(updatebtn);
		
		
	}
	public void getid(String id)  {		
		this.id=id;
			try {
				ResultSet rs=lo.Selectuser(con,id);
				while (rs.next()) {
					nom=rs.getString("nom");
					prenom=rs.getString("prenom");
					tel=rs.getString("télé");
					email=rs.getString("email");
					password=rs.getString("password");
					imgPath=rs.getString("photo");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		nomF.setText(nom);
		prenomF.setText(prenom);
		telF.setText(tel);
		mailF.setText(email);
		passwordF.setText(password);
		
		
		ImageIcon img=new ImageIcon(imgPath);
		Image img1=img.getImage();
		Image imgscale=img1.getScaledInstance(profilepic.getWidth(), profilepic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon=new ImageIcon(imgscale);
		profilepic.setIcon(icon);
		contentPane.add(profilepic);
	}
}
