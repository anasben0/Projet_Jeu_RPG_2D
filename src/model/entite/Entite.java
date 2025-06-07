package model.entite;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
* Création des classes entités
*/
public abstract class Entite {
    protected int vie;
    protected int vieMax;
    protected int force;
    public int Worldx;
    public int Worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;
    public int SpriteCounter = 0;
    public int SpriteNum = 1;
    public Rectangle hitbox;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    Entite(int vie, int force) {
        this.vie = vie;
        this.vieMax = vie;
        this.force = force;
    }

    Entite(int vie, int force, int x, int y) {
        this.vie = vie;
        this.vieMax = vie;
        this.force = force;
        this.Worldx = x;
        this.Worldy = y;
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
        return Worldx;
    }

    public int getY() {
        return Worldy;
    }

    public void setPos(int x, int y) {
        this.Worldx = x;
        this.Worldy = y;
    }

    public int getForce() {
        return force;
    }

}
