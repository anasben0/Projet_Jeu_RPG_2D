package model.entite;
import Test.GamePanel;
import controller.KeyHandlerJoueur;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.gameplay.Inventaire;
import model.item.Arme;
import model.item.Armure;
import model.personnalisation.CatalogueApparence;
import model.personnalisation.Personnalisation;

/**
 * Classe Joueur qui hérite de la classe Entité.
 * Elle représente le joueur dans le jeu, avec ses caractéristiques et son inventaire.
 */
public class Joueur extends Entite {
    private Personnalisation personnalisation;
    private int indexCheveux;
    private int indexHaut;
    private int indexBas;
    public BufferedImage corps; // image du corps de base
    private Inventaire inventaire;
    private Armure armure;
    private Arme arme;
    public final int ScreenX, ScreenY;
    private CatalogueApparence catalogue;
    KeyHandlerJoueur KeyH;
    int standCounter = 0; // Compteur pour l'animation de marche

    //public int hasKey = 0; // Indique si le joueur a la clé (0 = non, 1 = oui)

    public Joueur (int vie, GamePanel gp, KeyHandlerJoueur KeyH) {
        super(gp);
        this.catalogue = new CatalogueApparence();
        this.inventaire = new Inventaire();
        this.KeyH = KeyH;
        
        ScreenX = gp.ScreenWidth/2 - gp.TileSize/2;
        ScreenY = gp.ScreenHeight/2 - gp.TileSize/2;

        hitbox = new Rectangle(8, 16, 32, 32); // hitbox du joueur
        solidAreaDefaultX = hitbox.x;
        solidAreaDefaultY = hitbox.y;
        
        SetDefaultValues();
        // getPlayerImage();
        getJoueurAttaqueImage();
    }
    public void SetDefaultValues(){
        //Worldx =gp.TileSize*23;
        //Worldy =gp.TileSize*21;
        Worldx =gp.TileSize*10;
        Worldy =gp.TileSize*13;
        speed =4;
        direction = "down";

        // Satut du joueur
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/corps_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Player/boy_left_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void getJoueurAttaqueImage(){
        try{
            attackup1 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_up_1.png"));
            attackup2 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_up_2.png"));
            attackdown1 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_down_1.png"));
            attackdown2 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_down_2.png"));
            attackright1 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_right_1.png"));
            attackright2 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_right_2.png"));
            attackleft1 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_left_1.png"));
            attackleft2 = ImageIO.read(getClass().getResourceAsStream("/res/Assets/Attaque/boy_attack_left_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update (){

        if (KeyH.UpPressed == true || KeyH.DownPressed == true ||
        KeyH.RightPressed == true || KeyH.LeftPressed == true ||
        KeyH.enterPressed == true) {
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

            // verification des collisions avec les events
            gp.eHandler.checkEvent();

            // Vérification des collisions avec les monstres
            int monstreIndex = gp.cChecker.checkEntite(this, gp.monstre);
            contactMonstre(monstreIndex);

            

            if(collisionOn==false && KeyH.enterPressed == false){
                // Si pas de collision, on met à jour la position du joueur
                switch (direction) {
                    case "up": Worldy -= speed; break;
                    case "down": Worldy += speed; break;
                    case "left": Worldx -= speed; break;
                    case "right": Worldx += speed; break;
                }
            }
            gp.KeyH.enterPressed = false; // Réinitialise la touche entrée

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

        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60) { // 60 frames d'invincibilité
                invincible = false;
                invincibleCounter = 0; // Réinitialise le compteur d'invincibilité
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
                System.out.println("Interaction avec le PNJ : " + index);
                gp.pnj[index].speak(); // Appelle la méthode speak du PNJ pour afficher son dialogue
            }
        }
        
    }

    public void contactMonstre(int index) {
        // Gère le contact avec les monstres
        if (index != 999) {
            if (!invincible) {
                life -= 1; // Réduit la vie du joueur de 1
                invincible = true;
            }
            if (life <= 0) {
                System.out.println("Le joueur est mort !");
                // Logique de mort du joueur
            }
        }
    }

    
    public void draw(Graphics2D g2) {
        int x = ScreenX;
        int y = ScreenY;
        int frame = SpriteNum; // 1 ou 2

        BufferedImage bas = catalogue.getBas(indexBas, direction, frame);
        BufferedImage haut = catalogue.getHaut(indexHaut, direction, frame);
        BufferedImage cheveux = catalogue.getCheveux(indexCheveux, direction, frame);
        BufferedImage corps = catalogue.getCorps(direction, frame);

        // On change l'opacité si le joueur est invincible
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        // Dessine le joueur avec les images de personnalisation
        g2.drawImage(corps, x, y, gp.TileSize, gp.TileSize, null);
        if (bas != null) g2.drawImage(bas, x, y, gp.TileSize, gp.TileSize, null);
        if (haut != null) g2.drawImage(haut, x, y, gp.TileSize, gp.TileSize, null);
        if (cheveux != null) g2.drawImage(cheveux, x, y, gp.TileSize, gp.TileSize, null);
        // on reset l'opacité à 1 pour les prochaines images
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
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

    public int getIndexCheveux() {
        return indexCheveux;
    }

    public void setIndexCheveux(int indexCheveux) {
        this.indexCheveux = indexCheveux;
    }

    public int getIndexHaut() {
        return indexHaut;
    }

    public void setIndexHaut(int indexHaut) {
        this.indexHaut = indexHaut;
    }

    public int getIndexBas() {
        return indexBas;
    }

    public void setIndexBas(int indexBas) {
        this.indexBas = indexBas;
    }

    public CatalogueApparence getCatalogue() {
        return catalogue;
    }
    
}
