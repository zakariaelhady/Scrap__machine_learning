package projet;

public class personne {
	private String prenom,nom,tel;
	
	public personne(String prenom, String nom, String tel) {
		this.prenom = prenom;
		this.nom = nom;
		this.tel = tel;
	}

	public personne() {
		// TODO Auto-generated constructor stub
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getTel() {
		return tel;
	}

	

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
}
