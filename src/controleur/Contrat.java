package controleur;

public class Contrat {
	
	private int num_contrat, idclient;
	private String date_souscription, etat_du_contrat, objet_du_contrat;
	private float montant_mensuel_ht;
	
	public Contrat(int num_contrat, int idclient, String date_souscription, String etat_du_contrat,
			String objet_du_contrat, float montant_mensuel_ht) 
	{
		super();
		this.num_contrat = num_contrat;
		this.idclient = idclient;
		this.date_souscription = date_souscription;
		this.etat_du_contrat = etat_du_contrat;
		this.objet_du_contrat = objet_du_contrat;
		this.montant_mensuel_ht = montant_mensuel_ht;
	}

	public int getNum_contrat() {
		return num_contrat;
	}

	public void setNum_contrat(int num_contrat) {
		this.num_contrat = num_contrat;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getDate_souscription() {
		return date_souscription;
	}

	public void setDate_souscription(String date_souscription) {
		this.date_souscription = date_souscription;
	}

	public String getEtat_du_contrat() {
		return etat_du_contrat;
	}

	public void setEtat_du_contrat(String etat_du_contrat) {
		this.etat_du_contrat = etat_du_contrat;
	}

	public String getObjet_du_contrat() {
		return objet_du_contrat;
	}

	public void setObjet_du_contrat(String objet_du_contrat) {
		this.objet_du_contrat = objet_du_contrat;
	}

	public float getMontant_mensuel_ht() {
		return montant_mensuel_ht;
	}

	public void setMontant_mensuel_ht(float montant_mensuel_ht) {
		this.montant_mensuel_ht = montant_mensuel_ht;
	}
	
	
	
}
