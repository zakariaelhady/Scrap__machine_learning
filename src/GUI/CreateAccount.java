package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import load.loader;
import projet.user;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField prenomField;
	private JTextField nomField;
	private JTextField teleField;
	private JTextField mailField;
	private JPasswordField passwordField;
	loader lo=new loader();
	Connection con =lo.connection();
	String path="";
	private static CreateAccount frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CreateAccount();
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
	public CreateAccount() {
		
		setBounds(100, 100, 822, 513);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblprenom = new JLabel("First Name : ");
		lblprenom.setForeground(SystemColor.window);
		lblprenom.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblprenom.setBounds(311, 62, 131, 26);
		contentPane.add(lblprenom);
		
		JLabel lblLastName = new JLabel("Last Name : ");
		lblLastName.setForeground(SystemColor.window);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastName.setBounds(311, 121, 131, 26);
		contentPane.add(lblLastName);
		
		JLabel lblPhoneN = new JLabel("Phone N\u00B0 : ");
		lblPhoneN.setForeground(SystemColor.window);
		lblPhoneN.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPhoneN.setBounds(311, 183, 131, 26);
		contentPane.add(lblPhoneN);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setForeground(SystemColor.window);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEmail.setBounds(311, 246, 131, 26);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setForeground(SystemColor.window);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassword.setBounds(311, 309, 131, 26);
		contentPane.add(lblPassword);
		
		JLabel lblProfilePicture = new JLabel("Profile picture : ");
		lblProfilePicture.setForeground(SystemColor.window);
		lblProfilePicture.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblProfilePicture.setBounds(311, 369, 161, 26);
		contentPane.add(lblProfilePicture);
		
		prenomField = new JTextField();
		prenomField.setBounds(504, 55, 259, 33);
		contentPane.add(prenomField);
		prenomField.setColumns(10);
		
		nomField = new JTextField();
		nomField.setColumns(10);
		nomField.setBounds(504, 114, 259, 33);
		contentPane.add(nomField);
		
		teleField = new JTextField();
		teleField.setColumns(10);
		teleField.setBounds(504, 176, 259, 33);
		contentPane.add(teleField);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(504, 239, 259, 33);
		contentPane.add(mailField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(504, 302, 259, 33);
		contentPane.add(passwordField);
		
		JButton filebtn = new JButton("Choose File");
		filebtn.setBackground(SystemColor.controlShadow);
		filebtn.setForeground(SystemColor.menu);
		filebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(); 
				j.showSaveDialog(null);
				File f=j.getSelectedFile();
				path=f.getAbsolutePath();
			}
		});
		filebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		filebtn.setBounds(504, 369, 113, 27);
		contentPane.add(filebtn);
		
		
		JButton submit = new JButton("Submit");
		submit.setForeground(SystemColor.inactiveCaptionBorder);
		submit.setBackground(SystemColor.windowBorder);
		submit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom,nom,tel,mail,pswd;
				prenom=prenomField.getText();
				nom=nomField.getText();
				tel=teleField.getText();
				mail=mailField.getText();
				pswd=passwordField.getText();
				if(prenom.equals("")||nom.equals("")||tel.equals("")||mail.equals("")||pswd.equals("")) {
					JOptionPane.showMessageDialog(null, "Tout les champs doivent etre remplis");
				}
				else {
					if(path.equals("")) {path="img/profile.png";}
					path=path.replace("\\", "/");
					user us=new user(prenom,nom,tel,mail,pswd,path);
					try {
					    String id =lo.insertuser(con,us);
					    JOptionPane.showMessageDialog(null, "Votre compte a été créé avec succès");
					    interface1 i=new interface1();
			    		i.getid(id);
			    		i.show();
			    		frame.dispose();
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		submit.setBounds(649, 417, 113, 33);
		contentPane.add(submit);	
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 0, 274, 476);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_2.setFont(new Font("Viner Hand ITC", Font.BOLD, 36));
		lblNewLabel_2.setBounds(51, 77, 202, 54);
		panel.add(lblNewLabel_2);
		
		JLabel loginLabel = new JLabel("");
		loginLabel.setBounds(58, 152, 169, 169);
		loginLabel.setBounds(58, 152, 169, 169);
		ImageIcon img=new ImageIcon("img/3.png");
		Image img1=img.getImage();
		Image imgscale=img1.getScaledInstance(loginLabel.getWidth(), loginLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon=new ImageIcon(imgscale);
		loginLabel.setIcon(icon);
		panel.add(loginLabel);
		
		
	}
}
