package GUI;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ML.Classification;
import load.loader;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Stacking;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class predict extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	loader lo=new loader();
	Connection con =lo.connection();
	String nom,prenom,imgPath;
	private JPanel panel;
	private static predict frame;
	private JTextPane textPane;
	private JComboBox posteBox,contratBox,domaineBox,niveau_etudeBox,competencesBox,salaireBox,localisationBox,entrepriseBox;
	String id="1";
	String str="poste";
	String poste="Agent Marketing";
	String localisation="Agadir";
	String domaine="Agriculture / Environnement / Espaces Verts";
	String contrat="A discuter";
	String entreprise="1 Minute Script";
	String salaire="2 000 - 3 000 DH";
	String niveau_etude="Bac plus 2";
	String competences="angular";
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
				    frame = new predict();
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
	public predict() {
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
		
		
		posteBox = new JComboBox();
		posteBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poste=posteBox.getSelectedItem().toString();
			}
		});
		posteBox.setFont(new Font("Verdana", Font.BOLD, 14));
		posteBox.setModel(new DefaultComboBoxModel(new String[] {"Agent Marketing","Assistant(e) E-commerce","Backend Software Developer","Business Analyste IT","Chef de Projet DEVOPS","Chef de projet erp interne / bac+5","Community Manager  exp�riment�","Consultant PLSQL","Consultant(e) JavaEE","DEVELOPPEUR EN .NET A RABAT","DEVELOPPEUR ERP","DEVELOPPEUR FRONT END SENIOR","DEVELOPPEUR NODE JS","DEVELOPPEUR WEB & E-LEARNING","DEVELOPPEUR WEB","DEVELOPPEUR WINDEV CONFIRM�","Des d�veloppeurs exp�riment�s","Developpeur (se) React Native","Developpeur JAVA","Developpeur Python","Developpeur Windev","Developpeur react et laravel","Developpeur react native en freelance","Developpeurs VueJS, ReactJS","Developpeurs senior -- experience exigee","D�VELOPPEUR INT�GRATEUR WORDPRESS - MARRAKECH","D�VELOPPEUR LOGICIEL S�NIOR - Xamarin","D�VELOPPEUR MOBILE (REACT NATIVE)","D�veloppement Web","D�veloppeur front-end","D�veloppeur  Angular","D�veloppeur (se) web","D�veloppeur Applications Mobiles","D�veloppeur C#","D�veloppeur Delphi (h/f)","D�veloppeur E-commerce exp�riment�","D�veloppeur E-commerce","D�veloppeur Front-End /Back-End","D�veloppeur Informatique CASABLANCA","D�veloppeur Informatique Site Web","D�veloppeur Informatique","D�veloppeur JAVA JEE","D�veloppeur JAVA Senior","D�veloppeur JAVA","D�veloppeur JAVA/ ANGULAR","D�veloppeur Java","D�veloppeur Laravel (H/F)","D�veloppeur Mobile Android/IOS","D�veloppeur Mobile Flutter (H/F)","D�veloppeur Mobile flutter","D�veloppeur Mobile iOS","D�veloppeur Mobile","D�veloppeur PHP - Symfony - Laravel [Niveau: Exp�r","D�veloppeur PHP / PRESTASHOP","D�veloppeur PHP / Symfony - Casablanca","D�veloppeur PHP Confirm�","D�veloppeur PHP Laravel","D�veloppeur PHP Min 2 ans","D�veloppeur PHP","D�veloppeur PHP/Symfony Confirm�","D�veloppeur Php","D�veloppeur Python / Golang - France","D�veloppeur Python Flask Fastapi Typescript React","D�veloppeur Python pour ODOO","D�veloppeur Python","D�veloppeur Python/Odoo","D�veloppeur REACT Confirm�s","D�veloppeur React Natif (H/F)","D�veloppeur Shopify","D�veloppeur Swift UI","D�veloppeur TIBCO Senior","D�veloppeur WEB de Marrakech ( F  H )","D�veloppeur WEB","D�veloppeur WEB: PHP/CMS Javascript Exp�riment�","D�veloppeur Web / Charg� Marketing Digital","D�veloppeur Web / Mobile","D�veloppeur Web Angular","D�veloppeur Web JAVA","D�veloppeur Web Symfony","D�veloppeur Web","D�veloppeur WinDev","D�veloppeur WooCommerce","D�veloppeur Wordpress (H/F)","D�veloppeur applications mobile et siteweb","D�veloppeur cms et php","D�veloppeur confirm� maitrisant ReactNative","D�veloppeur consultant Zoho CRM","D�veloppeur en informatique Exp�riment�","D�veloppeur en php","D�veloppeur front-end / Int�grateur web","D�veloppeur iOS - IONIC","D�veloppeur iOS","D�veloppeur informatique (JAVA2EE)","D�veloppeur informatique PHP","D�veloppeur informatique","D�veloppeur java exp�riment� / bac +5 avec 5 ans minimum d'exp�rience","D�veloppeur java j2EE /JSF","D�veloppeur java","D�veloppeur mobile Flutter","D�veloppeur react native","D�veloppeur react senior","D�veloppeur react.js","D�veloppeur sur Flutter","D�veloppeur web / mobile - Marrakech","D�veloppeur web PHP","D�veloppeur web de marrakech","D�veloppeur web et d�veloppeur  mobile","D�veloppeur web et mobile","D�veloppeur web mobile React Native","D�veloppeur web wordpress","D�veloppeur web","D�veloppeur wordpress","D�veloppeur","D�veloppeur(e) Java exp�riment�(e)","D�veloppeur(euse) Prestashop / Wordpress","D�veloppeur(se) Applications mobile","D�veloppeur- charg� du projet- A distance","D�veloppeurs C#","D�veloppeurs JAVA/J2EE","D�veloppeurs Web","D�veloppeurs dart/flutter","D�veloppeurs informatique (H/F)","D�veloppeurs informatique","D�veloppeurs Full stack","D�veloppeurs web pour un Pr� embauche","D�veloppeurs � Casablanca","D�veloppeuse (F) Ma�trisant obligatoire < .NET <","Expert SEO/T�l�travail","Expert Wordpress - S�curit� - SEO","Formateur en D�veloppement Informatique","Formateur en python","Formateur(rice)s en reactjs","Formateuroriceos en ReactJS","ING�NIEUR D�VELOPPEUR DRUPAL 7 &8","Informaticien Technique H/F","Ingenieur Etude et Developpement","Ingenieur developpement web / bac+5","Ing�nieur  d�veloppement  multi plateforme ","Ing�nieur Data Scientist.","Ing�nieur Data","Ing�nieur Dev JAVA EE","Ing�nieur D�veloppement C# sur Casablanca","Ing�nieur D�veloppement C#","Ing�nieur D�veloppeur PHP/ Symfony","Ing�nieur Etude et D�veloppement PHP (H/F)","Ing�nieur d'etat en g�nie informatique","Ing�nieur d'�tat en informatique (3 ans)","Ing�nieur d'�tudes et developpement JAVA","Ing�nieur d'�tudes et d�veloppement php/js / bac+4","Ing�nieur d�veloppement JAVA","Ing�nieur d�veloppement","Ing�nieur d�veloppeur Drupal / PHP","Ing�nieur d�veloppeur JAVA","Ing�nieur d�veloppeur JAVA/REACTJS","Integrateur configurateur","Int�grateur / D�veloppeur Fullstack","Int�grateur / D�veloppeur H - WordPress","Int�grateur Web  Angular (Figma/AdobeXD)","Int�grateur/D�veloppeur (H/F) Laravel exp�riment�","It Team Leader","JAVA D�veloppeur","MOBILE DEVELOPER / FLUTTER","Offre emploi wordpress d�veloppement web","Profile d�veloppement  Informatique bas� � Temara","Project Coordinator","Python Developer","d�veloppeur C++","Responsable Informatique Industrielle","Responsable de l'analyse de Performance","Responsable technique en d�veloppement","Senior FULL STACK Developper","Senior en informatique (H/F)","Senior/expert en informatique / bac+5","Sharepoint developer","Software DevOps developpeur","Software Test (Quality Assurance - QA)","Software developer php","Technicien en d�veloppement informatique","Testeur","Web Developer / Mobile Developer","Webmaster Junior"}));
		posteBox.setToolTipText("");
		posteBox.setBounds(247, 197, 249, 31);
		contentPane.add(posteBox);
		
		contratBox = new JComboBox();
		contratBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contrat=contratBox.getSelectedItem().toString();
			}
		});
		contratBox.setFont(new Font("Verdana", Font.BOLD, 14));
		contratBox.setModel(new DefaultComboBoxModel(new String[] {"A discuter","Anapec","CDD","CDI","Interim"}));
		contratBox.setBounds(247, 257, 249, 31);
		contentPane.add(contratBox);
		
		competencesBox = new JComboBox();
		competencesBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				competences=competencesBox.getSelectedItem().toString();
			}
		});
		competencesBox.setFont(new Font("Verdana", Font.BOLD, 14));
		competencesBox.setModel(new DefaultComboBoxModel(new String[] {"angular","angular,next,node","angular,node","angular,oracle","c","c#","c#,vb.net","c++,c#","c++,python","c++,python,julia","c++,vb.net,oracle","css,laravel,wordpress","css,php","delphi","flutter","html","html,css,php","html,css,php,laravel,angular,node,wordpress","html,css,php,wordpress","html,css,shell,next","html,php,wordpress","j2ee","java","java,angular","java,angular,j2ee","java,angular,jee","java,angular,shell,jee,node","java,angular,shell,node","java,c#,javascript,html,css,oracle","java,c#,javascript,react,angular","java,c#,ruby,python","java,c++","java,c++,angular","java,c++,javascript","java,c++,javascript,php","java,c++,jquery,angular","java,c++,python","java,c++,python,j2ee,oracle","java,delphi,j2ee","java,flutter","java,html,css,angular","java,html,css,python","java,html,jee,j2ee,ajax","java,j2ee,bootstrap","java,j2ee,oracle","java,javascript,angular,jee","java,javascript,angular,jee,oracle","java,javascript,css,php,laravel","java,javascript,html,ajax","java,javascript,html,ajax,wordpress","java,javascript,html,css","java,javascript,html,css,laravel,angular","java,javascript,html,css,laravel,vue,angular","java,javascript,html,css,php","java,javascript,html,css,php,laravel,bootstrap","java,javascript,html,css,php,laravel,ruby,python,django,oracle","java,javascript,html,css,php,wordpress","java,javascript,html,css,vue,angular","java,javascript,html,css,wordpress","java,javascript,html,j2ee","java,javascript,html,php,laravel,flutter,wordpress","java,javascript,html,php,laravel,vue,next","java,javascript,html,php,laravel,wordpress","java,javascript,jquery,angular","java,javascript,jquery,css,php,laravel,angular,ajax,bootstrap,wordpress","java,javascript,jquery,css,php,laravel,swift,objective-c,angular,ajax,bootstrap,wordpress","java,javascript,jquery,html,css,ajax,bootstrap","java,javascript,jquery,html,css,angular","java,javascript,jquery,html,css,php","java,javascript,jquery,html,css,php,ajax","java,javascript,jquery,html,css,php,angular","java,javascript,jquery,html,css,php,angular,bootstrap","java,javascript,jquery,html,css,php,bootstrap,wordpress","java,javascript,jquery,html,css,php,laravel,angular,wordpress","java,javascript,jquery,html,css,php,laravel,bootstrap","java,javascript,jquery,html,css,php,laravel,python,vue,angular,wordpress","java,javascript,jquery,html,css,php,python,shell,ajax","java,javascript,jquery,html,css,python,scala,django","java,javascript,jquery,html,j2ee,bootstrap,oracle","java,javascript,jquery,html,php,ajax","java,javascript,jquery,php,ajax,bootstrap,wordpress","java,javascript,jquery,php,wordpress","java,javascript,jquery,react,html,css,php,laravel,angular,bootstrap,wordpress","java,javascript,jquery,react,html,css,php,laravel,angular,node,bootstrap","java,javascript,jquery,react,html,css,php,laravel,flutter,react native,vue,angular,node,ajax,bootstrap","java,javascript,jquery,react,html,css,php,laravel,react native,angular","java,javascript,jquery,react,html,css,php,laravel,vue,angular,jee,node,bootstrap,oracle","java,javascript,jquery,react,php,laravel,ajax,bootstrap","java,javascript,php","java,javascript,php,angular","java,javascript,python,scala","java,javascript,react","java,javascript,react,angular","java,javascript,react,angular,j2ee","java,javascript,react,html,css,php","java,javascript,react,html,css,php,laravel,vue,angular,jee,node,wordpress","java,javascript,react,html,css,php,python,react native,bootstrap,wordpress","java,javascript,react,html,css,react native,ajax","java,javascript,react,html,css,vue,angular","java,javascript,react,html,php,laravel,swift,kotlin,flutter,react native,vue,bootstrap","java,javascript,react,node","java,javascript,react,php,laravel,react native","java,javascript,react,php,react native","java,javascript,react,python,vue","java,javascript,react,react native,node","java,javascript,react,vue,node","java,javascript,wordpress","java,jee","java,jee,j2ee","java,jquery,angular,j2ee,oracle","java,jquery,react,html,css,j2ee","java,node","java,oracle","java,php","java,php,angular","java,php,jee,wordpress","java,php,python","java,python,scala,shell","java,react,angular","java,react,html,css,scala","java,react,php,vue","java,react,scala,j2ee","java,react,swift","java,react,swift,kotlin,react native,jee","java,swift","java,swift,kotlin,flutter","java,vue","jquery,html,css","jquery,html,css,php","jquery,html,css,php,flutter,wordpress","jquery,html,css,php,laravel,vue","jquery,html,css,php,wordpress","jquery,react,css,php,laravel,flutter,react native,vue,node,bootstrap,wordpress","jquery,react,html,css,php,vue,angular,wordpress","jquery,react,html,css,php,wordpress","laravel","laravel,flutter","laravel,node","laravel,vue,angular","laravel,wordpress","oracle","php","php,angular,wordpress","php,laravel","php,laravel,vue","php,node","php,oracle","php,wordpress","python","python,django","python,kotlin,flutter,vue,angular,node","react","react,flutter,react native","react,html,css,php,angular,wordpress","react,html,css,php,jee,bootstrap","react,html,css,php,laravel,angular,node,wordpress","react,html,css,vue","react,html,php,laravel,python,angular,django","react,laravel","react,next","react,php,laravel","react,php,laravel,flutter,react native,vue","react,python,next","react,react native","react,vue,angular","scala","swift","swift,flutter","vue","wordpress"}));
		competencesBox.setBounds(247, 327, 249, 31);
		contentPane.add(competencesBox);
		
		niveau_etudeBox = new JComboBox();
		niveau_etudeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				niveau_etude=niveau_etudeBox.getSelectedItem().toString();
			}
		});
		niveau_etudeBox.setFont(new Font("Verdana", Font.BOLD, 14));
		niveau_etudeBox.setModel(new DefaultComboBoxModel(new String[] {"Bac plus 2","Bac plus 3","Bac plus 4","Bac plus 5","Niveau Bac","Pas important"}));
		niveau_etudeBox.setBounds(247, 397, 249, 31);
		contentPane.add(niveau_etudeBox);
		
		entrepriseBox = new JComboBox();
		entrepriseBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entreprise=entrepriseBox.getSelectedItem().toString();
			}
		});
		entrepriseBox.setFont(new Font("Verdana", Font.BOLD, 14));
		entrepriseBox.setModel(new DefaultComboBoxModel(new String[] {"1 Minute Script","1pay","3WM MAROC","ABNSOFT","ACOVIT MAROC","ADELPHATECH","ALLPRO IT","ALTERIPSO Maroc","ALTERIPSO","ANONYME","APTUS Consulting","ASATECHNO CONSULTING","ASSSIG MAROC","ATLAS DOTNET SERVICE","ATT","Agence de communication","Amdimo","Anonyme","Ayouris","Azul Fish","BETTER APPS","Benarko","Bombs squad","Box","CBO","CMH","COMPULOG","CONFIDENTIEL","CPA MAROC","Centre de Formation","ClicWeb","Confidentiel","Confidentielle","DATAVALUE","DCM","DEV CONNECT","DEVOX","DG DEPLOY","DIGENCY","DIGITAL ELITE","DP","Dataweb","Digital Brains","Direct Invest","Dokumentive","E-matrex","E@sySolutions","ED","EI","ENGAGIA","ENGINEERING INSIDE","EVIDENCE-WAY","Emedia","Entreprise canadienne","Expanded Payment International XPI","FIANCITE INSTITUTE","FORNET","Filiale d'un groupe fran�ais","Forleaders","French Web","GEB A","GEB Analysis","GREEN SOLUTIONS","GRETIN","GSW","Geb Analysis","Genivar","Gerosoft","Global secur","Green Solutions Maroc","GroupMedia","Groupe PGM","H2C DEVELOPEMENT","HDCE Maroc","Hi-Tech","IEE","INFORMANCE SARL","ISP","IT Conseil","ITAB ACADEMY","Ihospitality","InCorp H","Industrielle, leader dans son domaine","Informatique","Itcom","Konilog","Konilog/Xperlean","L'NKBOOT MAROC","LAIMMASTERS","LHT","LITAJHIZ","Leader de l'Acier","Leman ebusiness","Livremoi.ma","M Media","MABORNE","MAPPING TECHNOLOGIES","MEDIA JOBS","MESSAGERIE","MIAGE RABAT SALE","MN AGENCY","Magnisfy Maroc","Mardis","Marketplace","MaxAB","Mobidigitech","Multivistas Media","NISMATECH","NT CONSULTING","Nat�sis","ObjectZone","Onestcom","PICAXEL","POWER FLEET","PRO SYSTEM SERVICE","PROWEBTECH","Pour le compte d'une grand entreprise industrielle","Pro Techno","Process solutions","Profonde Solution","QALQUL","Qualiso","Quintel SARL","RH WE","RH","RIM TELECOM","Roadmap consulting","SA","SARL","SHM","SILAMIR AFRIQUE","SIS Consultants","SOCIETE LOGISTIQUE & SI","SOCIETE SARL","SOFT AND SOFT","STARTUP","STE","SUCCESS","SUPDEV","Sarl","Simplon Maroc","Siway","Smart Influencer","Smart Transformation","Smartd","Smarteez","SoftDev","Staffone","Startup","Succursale D�veloppement IT","TANGER MED","TEAMWILL","TNA Consulting","TRAINVESTMENT SARL","Talents RH","Tanger","Teamwill Consulting","Telematic","Timlog Solutions","UA Tech S�rl","VIAPRESTIGE GROUPE","Virtual Marketing Solutions","Vitsolutions","Vivace Consulting","Vocation & Talents","WEBFORCE 3","WEBHI TECHNOLOGY SARL","WESHORE","Webcom","Webcom.ma","YC","YOU-PROF","Yar","Yarmo","ZoneCom","agence digital marketing","agence marrakech","centexam","chrono interim","cr�e","devweb","doosys","inovar solutions","mobidigitech","multinationale belge","onepay","sarl","soft & soft","une agence de conseil et d�veloppement web","valority","wex"}));
		entrepriseBox.setBounds(247, 463, 249, 31);
		contentPane.add(entrepriseBox);
		
		localisationBox = new JComboBox();
		localisationBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localisation=localisationBox.getSelectedItem().toString();
			}
		});
		localisationBox.setFont(new Font("Verdana", Font.BOLD, 14));
		localisationBox.setModel(new DefaultComboBoxModel(new String[] {"Agadir","Autre ville","Berrechid","Casablanca","F�s","K�nitra","Marrakech","Mekn�s","Mohammedia","Nador","Rabat","Safi","Sal�","Tanger","Temara","Tout le Maroc"}));
		localisationBox.setBounds(247, 527, 249, 31);
		contentPane.add(localisationBox);
		
		domaineBox = new JComboBox();
		domaineBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domaine=domaineBox.getSelectedItem().toString();
			}
		});
		domaineBox.setFont(new Font("Verdana", Font.BOLD, 14));
		domaineBox.setModel(new DefaultComboBoxModel(new String[] {"Agriculture / Environnement / Espaces Verts","Automobile","Autre","Banque / Finance / Assurance","Construction / Btp","Education / Social / Petite Enfance","Grande Distribution","Industrie / Ing�nierie / Energie","Informatique / Multim�dia / Internet","Marketing / Communication / Publicit� / Rp","Transport / Achat / Logistique"}));
		domaineBox.setBounds(247, 588, 249, 31);
		contentPane.add(domaineBox);
		
		salaireBox = new JComboBox();
		salaireBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salaire=salaireBox.getSelectedItem().toString();
			}
		});
		salaireBox.setFont(new Font("Verdana", Font.BOLD, 14));
		salaireBox.setModel(new DefaultComboBoxModel(new String[] {"2 000 - 3 000 DH","3 000 - 4 000 DH","4 000 - 6 000 DH","6 000 - 8 000 DH","8 000 - 10 000 DH","A discuter","Commission","Plus de 10 000 DH","Plus de 20 000 DH"}));
		salaireBox.setBounds(247, 140, 249, 31);
		contentPane.add(salaireBox);
		
		JLabel lblNewLabel_1 = new JLabel("Salaire :");
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1.setBounds(84, 141, 89, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Poste : ");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(84, 197, 89, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Contrat : ");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(84, 257, 89, 24);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Comp\u00E9tences : ");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(84, 329, 153, 24);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Niveau d'\u00E9tude : ");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_4.setBounds(84, 397, 153, 24);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Entreprise : ");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_5.setBounds(84, 463, 153, 24);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Localisation : ");
		lblNewLabel_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_6.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_6.setBounds(84, 527, 126, 24);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Domaine : ");
		lblNewLabel_1_7.setForeground(Color.WHITE);
		lblNewLabel_1_7.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_7.setBounds(84, 588, 126, 24);
		contentPane.add(lblNewLabel_1_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(591, 140, 391, 281);
		contentPane.add(scrollPane);
		
		
		JLabel lblNewLabel_1_8 = new JLabel("Choose the label : ");
		lblNewLabel_1_8.setForeground(Color.WHITE);
		lblNewLabel_1_8.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_8.setBounds(76, 10, 196, 39);
		contentPane.add(lblNewLabel_1_8);
		
		posteBox.setVisible(false);
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str = comboBox.getSelectedItem().toString();
				switch (str) {
					case "poste":
						posteBox.setVisible(false);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "contrat":
						posteBox.setVisible(true);
						contratBox.setVisible(false);
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "domaine":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(false);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "niveau_etude":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(false);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "competences":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(false);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "salaire":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(false);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(true);
						break;
					case "localisation":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(false);
						entrepriseBox.setVisible(true);
						break;
					case "entreprise":
						posteBox.setVisible(true);;
						contratBox.setVisible(true);;
						domaineBox.setVisible(true);
						niveau_etudeBox.setVisible(true);
						competencesBox.setVisible(true);
						salaireBox.setVisible(true);
						localisationBox.setVisible(true);
						entrepriseBox.setVisible(false);
						break;
				}
			}
		});
		comboBox.setToolTipText("label\r\n");
		comboBox.setFont(new Font("Verdana", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"poste", "localisation", "domaine", "contrat", "entreprise", "salaire", "niveau_etude", "competences"}));
		comboBox.setBounds(300, 10, 196, 32);
		contentPane.add(comboBox);
		
		JButton predictbtn = new JButton("Predict");
		predictbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			 		traindata.setClassIndex(traindata.attribute(str).index());
					testdata.setClassIndex(testdata.attribute(str).index());
					binary_traindata.setClassIndex(binary_traindata.attribute(str).index());
					binary_testdata.setClassIndex(binary_testdata.attribute(str).index());
					
					J48 tree=(J48)weka.core.SerializationHelper.read("models/"+str+"/j48.model");
					RandomForest randomforest=(RandomForest)weka.core.SerializationHelper.read("models/"+str+"/randomforest.model");
					NaiveBayes naivebayes=(NaiveBayes)weka.core.SerializationHelper.read("models/"+str+"/naivebayes.model");
					AdaBoostM1 adaboost=(AdaBoostM1)weka.core.SerializationHelper.read("models/"+str+"/adaboost.model");
					Stacking stacker=(Stacking)weka.core.SerializationHelper.read("models/"+str+"/stacker.model");
					
					
					Classifier cl = new J48();
					Evaluation eval = new Evaluation(binary_traindata);
					eval.crossValidateModel(cl, binary_testdata, 10, new Random(1), new Object[] {});
					ArrayList<Classifier>models = new ArrayList<Classifier>();
					models.add(adaboost);
					models.add(naivebayes);
					models.add(randomforest);
					Object[] evalModels=Classification.EvaluateModels(traindata, testdata,models,eval.pctCorrect());
					String bestModel="the best model is : "+evalModels[0]+" \t Correctly classified : "+evalModels[1]+" % ";
					
					String[] attributs= {"poste="+poste,"localisation="+localisation,"domaine="+domaine,"contrat="+contrat,
							"entreprise="+entreprise,"salaire="+salaire,"niveau_etude="+niveau_etude,"competences="+competences};
					Instance testinst=Classification.PredictInstance(binary_traindata,attributs);
					testinst.setDataset(binary_traindata);
					
					double label = tree.classifyInstance(testinst);
					String j48Prediction="J48 -->"+binary_traindata.classAttribute().value((int) label);
					
					double[] vals = new double[traindata.numAttributes()];
					vals[0]=traindata.attribute(0).indexOfValue(poste);
					vals[1] = traindata.attribute(1).indexOfValue(localisation);
					vals[2] = traindata.attribute(2).indexOfValue(domaine);
					vals[3] = traindata.attribute(3).indexOfValue(contrat); 
					vals[4] =traindata.attribute(4).indexOfValue(entreprise);
					vals[5] = traindata.attribute(5).indexOfValue(salaire);
					vals[6] =traindata.attribute(6).indexOfValue(niveau_etude);
					vals[7] = traindata.attribute(7).indexOfValue(competences);
					
					Instance offre = new DenseInstance(1.0,vals);
					//Assosiate your instance with Instance object in this case dataRaw
					offre.setDataset(traindata); 

					double label1 = randomforest.classifyInstance(offre);
					String RandomFPrediction="RandomForest -->"+traindata.classAttribute().value((int) label1);
					
					double label2 = naivebayes.classifyInstance(offre);
					String NaiveBPrediction="NaiveBayes -->"+traindata.classAttribute().value((int) label2);

					double label3 = adaboost.classifyInstance(offre);
					String AdaBoostPrediction="AdaBoost -->"+traindata.classAttribute().value((int) label3);
					
			  		double label4 = stacker.classifyInstance(offre);
			  		String StackingPrediction="Stacking -->"+traindata.classAttribute().value((int) label4);
			  		
			  	    textPane.setText(j48Prediction+"\n\n"+RandomFPrediction+"\n\n"+NaiveBPrediction+"\n\n"+AdaBoostPrediction+"\n\n"+StackingPrediction+"\n\n\n"+bestModel);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		predictbtn.setForeground(Color.WHITE);
		predictbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		predictbtn.setBackground(SystemColor.windowBorder);
		predictbtn.setBounds(807, 453, 175, 41);
		contentPane.add(predictbtn);
		
	    textPane = new JTextPane();
		textPane.setFont(new Font("Verdana", Font.BOLD, 14));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
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
