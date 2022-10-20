package controleur;

public class Materiel extends Prestation
{
    private String nom_materiel;
    private Float cout_materiel;

    public Materiel(int num_prestation, int num_contrat, String nom_prestation,float prix_prestation,
            String nom_materiel, float cout_materiel) 
    {
        super(num_prestation, num_contrat, nom_prestation, prix_prestation);

        this.nom_materiel = nom_materiel;
        this.cout_materiel = cout_materiel;
    }
    public String getNom_materiel() {
        return nom_materiel;
    }
    public void setNom_materiel(String nom_materiel) {
        this.nom_materiel = nom_materiel;
    }
    public float getCout_materiel() {
        return cout_materiel;
    }
    public void setCout_materiel(float cout_materiel) {
        this.cout_materiel = cout_materiel;
    }
}