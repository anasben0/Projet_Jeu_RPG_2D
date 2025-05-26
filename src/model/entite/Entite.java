package model.entite;

/**
* Création des classes entités
*/
public abstract class Entite {
    private int vie;
    private int vieMax;
    private int force;
    private int x;
    private int y;

    Entite(int vie, int force) {
        this.vie = vie;
        this.vieMax = vie;
        this.force = force;
    }

    Entite(int vie, int force, int x, int y) {
        this.vie = vie;
        this.vieMax = vie;
        this.force = force;
        this.x = x;
        this.y = y;
    }

    // Getters and Setters

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getVieMax() {
        return vieMax;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getForce() {
        return force;
    }

}
