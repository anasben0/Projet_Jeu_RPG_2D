package model.entite;
import model.entite.Entite;

/**
 * Classe représentant un monstre dans le jeu.
 * Hérite de la classe Entité.
 */

public class Monstre extends Entite {
    private String type;
    private int difficulté;
    
    public Monstre(int vie, int force, String type) {
        super(vie, force);
        this.type = type;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public int getDifficulté() {
        return difficulté;
    }

    public int getVie() {
        return super.getVie();
    }

    public void setDifficulté(int difficulté) {
        this.difficulté = difficulté;
    }

}
