package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;


import load.loader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class login {

	private JFrame frame;
	private JTextField loginField;
	private JPasswordField pswdField;
	loader lo=new loader();
	Connection con =lo.connection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 0, 102));
		frame.setBounds(100, 100, 770, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login :");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(371, 116, 106, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password : ");
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(371, 203, 150, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		loginField = new JTextField();
		loginField.setBounds(487, 127, 239, 32);
		frame.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton.setBackground(SystemColor.windowBorder);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login=loginField.getText();
				String pswd=pswdField.getText();
				if(login.equals("")||pswd.equals("")) {
					JOptionPane.showMessageDialog(null, "Tout les champs doivent etre remplis");
				}
				else {
					try {
						
						String sql="select * from user";
						Statement smt=con.createStatement();
						ResultSet rs=smt.executeQuery(sql);
						while(rs.next()) {
					    	String mail=rs.getString("email");
					    	String tel=rs.getString("télé");
					    	String mdp=rs.getString("password");
					    	String id=rs.getString("id");
					    	if((login.equals(mail) || login.equals("0"+tel)) && pswd.equals(mdp)) {
					    		JOptionPane.showMessageDialog(null, "vous êtes connecté avec succès");
					    		interface1 i=new interface1();
					    		i.getid(id);
					    		i.show();
					    		frame.dispose();
					    		   		
					    	}
					    	else {
					    		JOptionPane.showMessageDialog(null, "le login ou mot de passe est incorrect");
					    	}
						}
					}
					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(633, 276, 92, 32);
		frame.getContentPane().add(btnNewButton);
		
		pswdField = new JPasswordField();
		pswdField.setBounds(487, 203, 239, 32);
		frame.getContentPane().add(pswdField);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1.setBackground(SystemColor.windowBorder);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateAccount i=new CreateAccount();
	    		i.show();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.setBounds(526, 276, 92, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.windowBorder);
		panel.setBounds(0, 0, 274, 476);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel_2.setFont(new Font("Viner Hand ITC", Font.BOLD, 36));
		lblNewLabel_2.setBounds(51, 77, 202, 54);
		panel.add(lblNewLabel_2);
		
		JLabel loginLabel = new JLabel("");
		loginLabel.setBounds(58, 152, 169, 169);
		ImageIcon img=new ImageIcon("img/3.png");
		Image img1=img.getImage();
		Image imgscale=img1.getScaledInstance(loginLabel.getWidth(), loginLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon=new ImageIcon(imgscale);
		loginLabel.setIcon(icon);
		panel.add(loginLabel);
	}
}
