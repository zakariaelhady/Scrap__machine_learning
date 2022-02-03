package projet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import load.loader;

public class test {

	public static void main(String[] args) {
		scraper s=new scraper();
		ArrayList<offre> offres=s.scrap();
		loader lo=new loader();
		Connection con =lo.connection();
		
		//insert in database
//		for(int i=0;i<offres.size();i++) {
//			lo.insert(con, offres.get(i));
//		}
		
		
		try {
			ResultSet rs=lo.Select(con);
			while(rs.next()) {
				System.out.println(rs.getString("poste")+"/"+rs.getString("localisation")+"/"+rs.getString("domaine")+"/"+rs.getString("contrat")+"/"+
						rs.getString("entreprise")+"/"+rs.getString("salaire")+"/"+rs.getString("niveau_etude")+"/"+rs.getString("competences"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
