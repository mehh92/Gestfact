package controleur;

public class Vprestation 
{
	private int num_prestation, num_contrat;
	private String nom_prestation, nom_logiciel, nom_materiel, version;
	private float prix_prestation, cout_materiel;
	
	public Vprestation(int num_prestation, int num_contrat, String nom_prestation,float prix_prestation,
			String nom_materiel,float cout_materiel, String nom_logiciel, String version)
	{
		super();
		this.num_prestation = num_prestation;
		this.num_contrat = num_contrat;
		this.nom_prestation = nom_prestation;
		this.prix_prestation = prix_prestation;
		this.nom_materiel = nom_prestation;
		this.cout_materiel = cout_materiel;
		this.nom_logiciel = nom_logiciel;
		this.version = version;
	}

	public int getNum_prestation() {
		return num_prestation;
	}

	public void setNum_prestation(int num_prestation) {
		this.num_prestation = num_prestation;
	}

	public int getNum_contrat() {
		return num_contrat;
	}

	public void setNum_contrat(int num_contrat) {
		this.num_contrat = num_contrat;
	}

	public String getNom_prestation() {
		return nom_prestation;
	}

	public void setNom_prestation(String nom_prestation) {
		this.nom_prestation = nom_prestation;
	}

	public String getNom_logiciel() {
		return nom_logiciel;
	}

	public void setNom_logiciel(String nom_logiciel) {
		this.nom_logiciel = nom_logiciel;
	}

	public String getNom_materiel() {
		return nom_materiel;
	}

	public void setNom_materiel(String nom_materiel) {
		this.nom_materiel = nom_materiel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public float getPrix_prestation() {
		return prix_prestation;
	}

	public void setPrix_prestation(float prix_prestation) {
		this.prix_prestation = prix_prestation;
	}

	public float getCout_materiel() {
		return cout_materiel;
	}

	public void setCout_materiel(float cout_materiel) {
		this.cout_materiel = cout_materiel;
	}
	
}