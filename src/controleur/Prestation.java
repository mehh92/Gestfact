package controleur;

public class Prestation 
{
    private int num_prestation, num_contrat;
    private String nom_prestation;
    private float prix_prestation;

    public Prestation(int num_prestation, int num_contrat, String nom_prestation, float prix_prestation) {
        super();
        this.num_prestation = num_prestation;
        this.num_contrat = num_contrat;
        this.nom_prestation = nom_prestation;
        this.prix_prestation = prix_prestation;
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

    public float getPrix_prestation() {
        return prix_prestation;
    }

    public void setPrix_prestation(float prix_prestation) {
        this.prix_prestation = prix_prestation;
    }



}