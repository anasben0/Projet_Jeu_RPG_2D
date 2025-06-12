package model.entite;

import Test.GamePanel;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
* Création des classes entités
*/
public abstract class Entite {
    public GamePanel gp;
    protected int vie;
    protected int vieMax;
    protected int force;
    public int Worldx;
    public int Worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage attackup1, attackup2, attackdown1, attackdown2, attackright1, attackright2, attackleft1, attackleft2;
    public String direction= "down";
    public int SpriteCounter = 0;
    public int SpriteNum = 1;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    String dialogue[] = new String[20];
    int dialogueIndex = 0;
    public String nom;
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    public int type ; // 0 = joueur, 1 = PNJ, 2 = monstre, 3 = objet, 4 = portail

    //Statut du joueur
    public int maxLife;
    public int life;

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
        gp.cChecker.checkEntite(this, gp.monstre); // Vérification des collisions avec les monstres
        gp.cChecker.checkEntite(this, gp.pnj);
        boolean contactJoueur = gp.cChecker.checkJoueur(this);

        if(this.type == 2 && contactJoueur){
            if(gp.joueur.invincible == false){
                gp.joueur.life -= 1; // Réduit la vie du joueur de 1
                gp.joueur.invincible = true;
            }
        }

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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 30) { // 60 frames d'invincibilité
                invincible = false;
                invincibleCounter = 0; // Réinitialise le compteur d'invincibilité
            }
        }


    }
    public void draw(Graphics2D g2) {
        int screenX = Worldx - gp.joueur.Worldx + gp.joueur.ScreenX;
        int screenY = Worldy - gp.joueur.Worldy + gp.joueur.ScreenY;

        boolean isVisible = 
            Worldx + gp.TileSize > gp.joueur.Worldx - gp.joueur.ScreenX &&
            Worldx - gp.TileSize < gp.joueur.Worldx + gp.joueur.ScreenX &&
            Worldy + gp.TileSize > gp.joueur.Worldy - gp.joueur.ScreenY &&
            Worldy - gp.TileSize < gp.joueur.Worldy + gp.joueur.ScreenY;

        if(isVisible) {
            BufferedImage imageToDraw = null;
            
            // Sélection de l'image selon la direction et le sprite
            switch(direction) {
                case "up":    imageToDraw = (SpriteNum == 1) ? up1 : up2; break;
                case "down":  imageToDraw = (SpriteNum == 1) ? down1 : down2; break;
                case "left":  imageToDraw = (SpriteNum == 1) ? left1 : left2; break;
                case "right": imageToDraw = (SpriteNum == 1) ? right1 : right2; break;
            }

            if (invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            }
            if(dying == true){
                dyingAnimation(g2);
            }

            if (imageToDraw != null) {
                g2.drawImage(imageToDraw, screenX, screenY, gp.TileSize, gp.TileSize, null);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }
    
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;

        if(dyingCounter <= 5){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > 5 && dyingCounter <=10){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > 10 && dyingCounter <=15){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > 15 && dyingCounter <=20){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > 20 && dyingCounter <=25){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > 25 && dyingCounter <=30){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > 30 && dyingCounter <=35){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > 35 && dyingCounter <=40){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > 40){
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2,float alphavalue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphavalue));
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
