package controleur;

public class Facture 
{
	
	private int num_facture, num_prestation;
	private String date_facture, etat;
	private float montant;
	
	public Facture(int num_facture, int num_prestation, String date_facture, float montant, String etat) {
		super();
		this.num_facture = num_facture;
		this.num_prestation = num_prestation;
		this.date_facture = date_facture;
		this.etat = etat;
		this.montant = montant;
	}

	public int getNum_facture() {
		return num_facture;
	}

	public void setNum_facture(int num_facture) {
		this.num_facture = num_facture;
	}

	public int getNum_prestation() {
		return num_prestation;
	}

	public void setNum_prestation(int num_prestation) {
		this.num_prestation = num_prestation;
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
