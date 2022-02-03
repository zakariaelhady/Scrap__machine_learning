package projet;

public class offre {
	private String poste,localisation,domaine,contrat,entreprise,salaire,niveau_etude,competences;

	public offre() {
	}

	public offre(String poste, String localisation, String domaine, String contrat, String entreprise, String salaire,
			String niveau_etude, String competences) {
		this.poste = poste;
		this.localisation = localisation;
		this.domaine = domaine;
		this.contrat = contrat;
		this.entreprise = entreprise;
		this.salaire = salaire;
		this.niveau_etude = niveau_etude;
		this.competences = competences;
	}

	public String getPoste() {
		return poste;
	}

	public String getLocalisation() {
		return localisation;
	}

	public String getDomaine() {
		return domaine;
	}

	public String getContrat() {
		return contrat;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public String getSalaire() {
		return salaire;
	}

	public String getNiveau_etude() {
		return niveau_etude;
	}

	public String getCompetences() {
		return competences;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public void setContrat(String contrat) {
		this.contrat = contrat;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}

	public void setNiveau_etude(String niveau_etude) {
		this.niveau_etude = niveau_etude;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	@Override
	public String toString() {
		return "offre [poste=" + poste + ", localisation=" + localisation + ", domaine=" + domaine + ", contrat="
				+ contrat + ", entreprise=" + entreprise + ", salaire=" + salaire + ", niveau_etude=" + niveau_etude
				+ ", competences=" + competences + "]";
	}
	
	public void affiche() {
		System.out.println(this.toString());
	}
	

}
