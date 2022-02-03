package projet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class scraper {
	public ArrayList<offre> scrap() {
		ArrayList<offre> offres=new ArrayList<offre>();
		try{	
			for(int j=1;j<31;j++) {
			    Document document = Jsoup.connect("https://www.marocannonces.com/maroc/offres-emploi-fonction-informatique-developpement-b309.html?"
			    		+ "f_574=Informatique+-+D%C3%A9veloppement&pge="+j).get();
			    Elements li = document.select(".cars-list li:not(.adslistingpos)");
			    for(int i=0;i<li.size();i++) {
				    Elements a=li.get(i).select("a[title]");
				    
				    String poste=a.attr("title"); //poste
				    if(poste.toLowerCase().contains("stage")|| poste.toLowerCase().contains("stagiaire")) {}
				    else {
					    String a_url="https://www.marocannonces.com/";
					    a_url+=URLEncoder.encode(a.attr("href"),"utf-8").replace("%2F", "/");

					    try {
						    Document document2=Jsoup.connect(a_url).get();
						    if(document2.select("div.exalert_title").text().contains("Désolé")) {}
						    else {
							    Elements description=document2.select("div.desccatemploi");
							    
							    String location=description.select("ul.info-holder>li>a").text(); //location
							    Elements parameters=description.select("ul#extraQuestionName.extraQuestionName li");
							    if(parameters.size()==6){
								    String domaine=parameters.get(0).select("a").text(); //domaine
								    String contrat=parameters.get(2).select("a").text();  //contrat
								    String entreprise=parameters.get(3).select("a").text(); //entreprise
								    String salaire=parameters.get(4).select("a").text();  //salaire
								    String niveau_etude=parameters.get(5).select("a").text(); //niveau d'etude
								    
								    String offre_desc = description.select("div.block").text();
								    String[] liste_competence= {"java"," c ","c++"," c# ","javascript","jquery","react","html","css","php","laravel","ruby","swift","python","kotlin",
								    		"objective-c","flutter","react native","julia","scala"," vue","angular","matlab","visual basic","perl","delphi"," go ","groovy","vb.net"
								    		,"sql,pl/sql", "shell"," lua","haskell","rust","jee","j2ee","arduino"," next","node","django","ajax","bootstrap","wordpress","oracle"};
								    String competences="";
								    for(String k : liste_competence) {
								    	if(offre_desc.toLowerCase().contains(k)) {
								    		competences+=k.trim()+",";
								    	}
								    }
								    
						
								    if(competences==""||contrat.contains("Stage")) {}
								    else {
								    	competences = competences.substring(0, competences.length() - 1);
								    	offre of=new offre(poste.trim(),location.trim(),domaine.trim(),contrat.trim(),entreprise.trim(),salaire.trim(),niveau_etude.trim(),
								    			competences.trim());
								    	offres.add(of);
								    	
								    }    
							    }
						    }
					    }
					    catch(HttpStatusException e) {
					    }
				    }    	
			    }    
			}
		}
		catch(IOException ioe){
		    System.out.println("Unable to connect to the URL");
		    ioe.printStackTrace();
		}
		return offres;
	}
}
