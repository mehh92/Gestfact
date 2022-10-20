package controleur;

public class Vfacture 
{
	private int num_facture, num_contrat, num_prestation;
	private String nom_client, nom_prestation, date_facture, etat;
	private float montant;
	
	public Vfacture(int num_facture, int num_contrat,  String nom_client, int num_prestation, String nom_prestation,
			String date_facture , float montant, String etat) {
		
		super();
		this.num_facture = num_facture;
		this.num_contrat = num_contrat;
		this.nom_client = nom_client;
		this.num_prestation = num_prestation;
		this.nom_prestation = nom_prestation;
		this.date_facture = date_facture;
		this.montant = montant;
		this.etat = etat;
		
	}

	public int getNum_facture() {
		return num_facture;
	}

	public void setNum_facture(int num_facture) {
		this.num_facture = num_facture;
	}

	public int getNum_contrat() {
		return num_contrat;
	}

	public void setNum_contrat(int num_contrat) {
		this.num_contrat = num_contrat;
	}

	public int getNum_prestation() {
		return num_prestation;
	}

	public void setNum_prestation(int num_prestation) {
		this.num_prestation = num_prestation;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getNom_prestation() {
		return nom_prestation;
	}

	public void setNom_prestation(String nom_prestation) {
		this.nom_prestation = nom_prestation;
	}

	public String getDate_facture() {
		return date_facture;
	}

	public void setDate_facture(String date_facture) {
		this.date_facture = date_facture;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	
	
}

