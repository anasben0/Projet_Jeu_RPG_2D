package model.personnalisation;

/** 
 * Création de la personnalisation du joueur
 */
public class Personnalisation {
    private String chapeau;
    private String haut;
    private String bas;
    private String chaussures;
    private String couleurPeau;

    public Personnalisation(String chapeau, String haut, String bas, String chaussures, String couleurPeau) {
        this.chapeau = chapeau;
        this.haut = haut;
        this.bas = bas;
        this.chaussures = chaussures;
        this.couleurPeau = couleurPeau;
    }

    // Getters and Setters

    public String getChapeau() {
        return chapeau;
    }

    public void setChapeau(String chapeau) {
        this.chapeau = chapeau;
    }

    public String getHaut() {
        return haut;
    }

    public void setHaut(String haut) {
        this.haut = haut;
    }

    public String getBas() {
        return bas;
    }

    public void setBas(String bas) {
        this.bas = bas;
    }

    public String getChaussures() {
        return chaussures;
    }

    public void setChaussures(String chaussures) {
        this.chaussures = chaussures;
    }

    public String getCouleurPeau() {
        return couleurPeau;
    }

    public void setCouleurPeau(String couleurPeau) {
        this.couleurPeau = couleurPeau;
    }

}
