package model.entite;

import Test.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
* Création des classes entités
*/
public abstract class Entite {
    GamePanel gp;
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
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogue[] = new String[20];
    int dialogueIndex = 0;

    public Entite(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){
        // Méthode à implémenter dans les classes filles pour définir le comportement de l'entité
    }
    public void speak(){
        // Méthode pour afficher le dialogue de l'entité
        if(dialogue[dialogueIndex] == null) {
            dialogueIndex = 0; // Réinitialise l'index si on atteint la fin du dialogue
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;

        switch(gp.joueur.direction) {
            case "up":
                direction = "down"; // Le PNJ regarde le joueur
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void update(){
        // Méthode à implémenter dans les classes filles pour mettre à jour l'état de l'entité
        setAction();
        collisionOn = false;
        gp.cChecker.CheckTile(this); // Vérification des collisions avec les tuiles
        gp.cChecker.CheckObject(this, false); // Vérification des collisions avec les objets
        gp.cChecker.checkJoueur(this); // Vérification des collisions avec le joueur
        if(collisionOn==false){
                // Si pas de collision, on met à jour la position du joueur
                switch (direction) {
                    case "up": Worldy -= speed; break;
                    case "down": Worldy += speed; break;
                    case "left": Worldx -= speed; break;
                    case "right": Worldx += speed; break;
                }
            }

            SpriteCounter ++;
            if (SpriteCounter > 10){
                if(SpriteNum == 1){
                    SpriteNum = 2;
                }
                else if ( SpriteNum == 2){
                    SpriteNum = 1;
                }
                SpriteCounter=0;
            }


    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int ScreenX = Worldx - gp.joueur.Worldx + gp.joueur.ScreenX;
        int ScreenY = Worldy - gp.joueur.Worldy + gp.joueur.ScreenY;

        if(Worldx + gp.TileSize > gp.joueur.Worldx - gp.joueur.ScreenX &&
           Worldx - gp.TileSize < gp.joueur.Worldx + gp.joueur.ScreenX &&
           Worldy + gp.TileSize > gp.joueur.Worldy - gp.joueur.ScreenY &&
           Worldy - gp.TileSize < gp.joueur.Worldy + gp.joueur.ScreenY) {
            switch (direction) {
            case "up":
                if( SpriteNum == 1){
                    image = up1;
                }
                if( SpriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if( SpriteNum == 1){
                    image = down1;
                }
                if( SpriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if( SpriteNum == 1){
                    image = left1;
                }
                if ( SpriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if ( SpriteNum == 1){
                    image = right1;
                }
                if ( SpriteNum == 2){
                    image = right2;
                }
                break;
        }

           g2.drawImage(image, ScreenX, ScreenY, gp.TileSize, gp.TileSize, null);
        }
    }   

    /*Entite(int vie, int force) {
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
    }*/

}
