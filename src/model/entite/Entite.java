package model.entite;

import java.awt.image.BufferedImage;

/**
* Création des classes entités
*/
public abstract class Entite {
    protected int vie;
    protected int vieMax;
    protected int force;
    public int x;
    public int y;
    public int speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

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
