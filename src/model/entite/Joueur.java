package model.entite;
import Test.GamePanel;
import controller.KeyHandlerJoueur;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.gameplay.Inventaire;
import model.item.Arme;
import model.item.Armure;
import model.personnalisation.Personnalisation;

/**
 * Classe Joueur qui hérite de la classe Entité.
 * Elle représente le joueur dans le jeu, avec ses caractéristiques et son inventaire.
 */
public class Joueur extends Entite {
    private Personnalisation personnalisation;
    private Inventaire inventaire;
    private Armure armure;
    private Arme arme;
    public final int ScreenX, ScreenY;
    KeyHandlerJoueur KeyH;
    int standCounter = 0; // Compteur pour l'animation de marche

    //public int hasKey = 0; // Indique si le joueur a la clé (0 = non, 1 = oui)

    public Joueur (int vie, GamePanel gp, KeyHandlerJoueur KeyH) {
        super(gp);
        this.inventaire = new Inventaire();
        this.KeyH = KeyH;
        
        ScreenX = gp.ScreenWidth/2 - gp.TileSize/2;
        ScreenY = gp.ScreenHeight/2 - gp.TileSize/2;

        hitbox = new Rectangle(8, 16, 32, 32); // hitbox du joueur
        solidAreaDefaultX = hitbox.x;
        solidAreaDefaultY = hitbox.y;
        
        SetDefaultValues();
        getPlayerImage();
    }
    public void SetDefaultValues(){
        Worldx =gp.TileSize*23;
        Worldy =gp.TileSize*21;
        speed =4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_left_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update (){

        if (KeyH.UpPressed == true || KeyH.DownPressed == true ||
        KeyH.RightPressed == true || KeyH.LeftPressed == true){
            if (KeyH.UpPressed == true){
                direction ="up";
            }
            else if (KeyH.DownPressed == true){
                direction ="down";
            }
            else if (KeyH.RightPressed == true){
                direction ="right";
            }
            else if (KeyH.LeftPressed == true){
                direction ="left";
            }

            // Collision Checker
            collisionOn = false;
            gp.cChecker.CheckTile(this);
            // Vérification des collisions avec les objets
            int itemIndex = gp.cChecker.CheckObject(this, true);
            pickUpItem(itemIndex);

            // Vérification des collisions avec les PNJ
            int pnjIndex = gp.cChecker.checkEntite(this, gp.pnj);
            interactWithPNJ(pnjIndex);

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
        
    }

    public void pickUpItem(int index) {
        // Ajoute l'item à l'inventaire du joueur
        if (index != 999) {
            
        }
    }
    public void interactWithPNJ(int index) {
        // Interagit avec le PNJ si le joueur est proche
        if (index != 999) {
            // Logique d'interaction avec le PNJ
            if(gp.KeyH.enterPressed == true) {
                gp.gameState = gp.dialogueState; // Change l'état du jeu pour afficher le dialogue
                gp.pnj[index].speak(); // Appelle la méthode speak du PNJ pour afficher son dialogue
            }
        }
        gp.KeyH.enterPressed = false; // Réinitialise la touche entrée
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

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
    // Getters and Setters
    public Personnalisation getPersonnalisation() {
        return personnalisation;
    }

    public void setPersonnalisation(Personnalisation personnalisation) {
        this.personnalisation = personnalisation;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public Armure getArmure() {
        return armure;
    }

    public void setArmure(Armure armure) {
        this.armure = armure;
    }

    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }
    
}
