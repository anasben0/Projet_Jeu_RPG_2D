package model.personnalisation;

import java.util.List;

// Mettre en place une liste de personnalisation disponible
public class CatalogueApparence {
    private List<String> chapeaux;
    private List<String> hauts;
    private List<String> bas;
    private List<String> chaussures;
    private List<String> couleursPeau;

    public CatalogueApparence(List<String> chapeaux, List<String> hauts, List<String> bas, List<String> chaussures, List<String> couleursPeau) {
        this.chapeaux = chapeaux;
        this.hauts = hauts;
        this.bas = bas;
        this.chaussures = chaussures;
        this.couleursPeau = couleursPeau;
    }

    // Getters
    public List<String> getChapeaux() {
        return chapeaux;
    }

    public List<String> getHauts() {
        return hauts;
    }

    public List<String> getBas() {
        return bas;
    }

    public List<String> getChaussures() {
        return chaussures;
    }

    public List<String> getCouleursPeau() {
        return couleursPeau;
    }
}
