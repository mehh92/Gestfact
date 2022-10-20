package controleur;

public class Logiciel extends Prestation
{
    private String nom_logiciel, version;

    public Logiciel(int num_prestation, int num_contrat, String nom_prestation, float prix_prestation,
            String nom_logiciel, String version) 
    {
        super(num_prestation, num_contrat, nom_prestation, prix_prestation);

        this.nom_logiciel = nom_logiciel;
        this.version = version;
    }
    
    public String getNom_logiciel() {
        return nom_logiciel;
    }
    public void setNom_logiciel(String nom_logiciel) {
        this.nom_logiciel = nom_logiciel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}