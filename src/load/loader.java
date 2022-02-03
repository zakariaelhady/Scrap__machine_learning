package load;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import projet.offre;
import projet.user;


public class loader {
	
	public Connection connection() {
		Connection con=null;
		try {
			Class c=Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote=(Driver)c.newInstance();
			DriverManager.registerDriver(pilote);
			String protocole="jdbc:mysql:";
			String ip="localhost";
			String port="3306";
			String nomBase="java";
			String constring=protocole+"//"+ip+":"+port+"/"+nomBase;
			String nomConnection="root";
			String motDePasse="";
			con=DriverManager.getConnection(constring,nomConnection,motDePasse);	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public void insert(Connection con,offre of) {
		try {
			String sql="INSERT INTO `offre` (`poste`,`localisation`,`domaine`,`contrat`,`entreprise`,`salaire`,`niveau_etude`,`competences`) "
					+ "VALUES ('"+of.getPoste().replace("'", "''")+"','"+of.getLocalisation()+"','"+of.getDomaine()+"','"+of.getContrat()+"','"+of.getEntreprise().replace("'", "''")+
					"','"+of.getSalaire()+"','"+of.getNiveau_etude()+"','"+of.getCompetences()+"')";
			Statement smt=con.createStatement();
			smt.executeUpdate(sql);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet Select(Connection con) {
		try {
			String sql="select * from offre";
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery(sql);
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public String insertuser(Connection con,user us) {
		String id="";
		try {
			String sql="INSERT INTO `user` (`prenom`,`nom`,`télé`,`email`,`password`,`photo`) "
					+ "VALUES ('"+us.getPrenom()+"','"+us.getNom()+"','"+us.getTel()+"','"+us.getEmail()+"','"+us.getPassword()+"','"+us.getImgPath()+"')";
			Statement smt=con.createStatement();
			smt.executeUpdate(sql);
			
			String sql1="SELECT id FROM user GROUP BY id DESC LIMIT 1";
			Statement smt1=con.createStatement();
			ResultSet rs=smt1.executeQuery(sql1);
			while(rs.next()) {
				id=rs.getString("id");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	public ResultSet Selectuser(Connection con,String id) {
		try {
			String sql="select * from user where id="+id;
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery(sql);
			return rs;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	public void Updateuser(Connection con,String id,user us) {
		try {
			String sql="update `user` set `prenom`='"+us.getPrenom()+"',`nom`='"+us.getNom()+"',`télé`='"+us.getTel()+
					"',`email`='"+us.getEmail()+"',`password`='"+us.getPassword()+"',`photo`='"+us.getImgPath()+"'"
					+ "where id="+id;
			Statement smt=con.createStatement();
			smt.executeUpdate(sql);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
