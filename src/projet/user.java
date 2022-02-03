package projet;

public class user extends personne {
	private String email,password,imgPath;
	
	public user() {
		super();
	}

	

	public user(String prenom, String nom, String tel,String email,String password,String imgPath) {
		super(prenom, nom, tel);
		this.email=email;
		this.password=password;
		this.imgPath=imgPath;
	}



	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getImgPath() {
		return imgPath;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "user [Email=" + getEmail() + ", Password=" + getPassword() + ", ImgPath=" + getImgPath()
				+ ", Prenom=" + getPrenom() + ", Nom=" + getNom() + ", Tel=" + getTel() + "]";
	}

	
	public void Affiche() {
		System.out.println(this.toString());
	}
}
