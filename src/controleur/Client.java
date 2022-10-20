package controleur;

public class Client 
{
	private int idclient; 
	private String nom, siret, tel, email, adresse, CP, ville;
		
	public Client(int idclient, String nom, String siret, String tel, String email, String adresse, String CP, String ville) 
	{
		super();
		this.idclient = idclient;
		this.nom = nom;
		this.siret = siret;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
		this.CP = CP;
		this.ville = ville;

	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}



	
	
}
