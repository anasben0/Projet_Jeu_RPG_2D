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

        attackArea.width = 36;
        attackArea.height = 36;
        
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

        if ( attacking == true){
            attacking();
        }

        else if (KeyH.UpPressed == true || KeyH.DownPressed == true ||
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

        if(life <= 0){
            gp.gameState = gp.gameOverState;
        }
        
    }

    public void attacking(){
        SpriteCounter++;
        if (SpriteCounter <= 5) {
            SpriteNum = 1; // Premier sprite d'attaque
        }
        if (SpriteCounter > 5 && SpriteCounter <= 25) {
            SpriteNum = 2; // Deuxième sprite d'attaque

            int currentWorldX = Worldx;
            int currentWorldY = Worldy;
            int solidAreaWidth = hitbox.x;
            int solidAreaHeight = hitbox.y;

            switch(direction){
            case "up" : Worldy -= attackArea.height; break;
            case "down" : Worldy += attackArea.height; break;
            case "left" : Worldx -= attackArea.width; break;
            case "right" : Worldx += attackArea.width; break;
            }

            hitbox.width = attackArea.width;
            hitbox.height = attackArea.height;
            //collision avec les monstres
            int monsterIndex = gp.cChecker.checkEntite(this, gp.monstre);
            damageMonster(monsterIndex);

            Worldx = currentWorldX;
            Worldy = currentWorldY;
            hitbox.width = solidAreaWidth;
            hitbox.height = solidAreaHeight;
        }
        if (SpriteCounter > 25) {
            attacking = false; // Fin de l'attaque
            SpriteNum = 1; // Réinitialise le sprite à la position de base
            SpriteCounter = 0; // Réinitialise le compteur de sprite
        }
    }

    public void pickUpItem(int index) {
        // Ajoute l'item à l'inventaire du joueur
        if (index != 999) {
            
        }
    }
    public void interactWithPNJ(int index) {
        if(gp.KeyH.enterPressed == true){

            if (index != 999){
                gp.gameState = gp.dialogueState; // Change l'état du jeu pour afficher le dialogue
                gp.pnj[index].speak(); // Appelle la méthode speak du PNJ pour afficher son dialogue

            }
            else {
                attacking = true; // Le joueur attaque
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

    public void damageMonster(int index){

        if( index != 999){

            if(gp.monstre[index].invincible == false){
                gp.monstre[index].life -=1;
                gp.monstre[index].invincible = true;

                if(gp.monstre[index].life <= 0){
                    //gp.monstre[index] = null;
                    gp.monstre[index].dying = true;
                }
            }
        }
    }

    
    public void draw(Graphics2D g2) {
        int x = ScreenX;
        int y = ScreenY;
        int frame = SpriteNum; // 1 ou 2
        BufferedImage attack = null;
        int tempScreenX = ScreenX;
        int tempScreenY = ScreenY;

        BufferedImage bas = catalogue.getBas(indexBas +1, direction, frame);
        BufferedImage haut = catalogue.getHaut(indexHaut +1, direction, frame);
        BufferedImage cheveux = catalogue.getCheveux(indexCheveux +1, direction, frame);
        BufferedImage corps = catalogue.getCorps(direction, frame);
        BufferedImage épée = catalogue.getEpee(direction, frame);

        // Si le joueur est en train d'attaquer, on utilise les images d'attaque
        if (attacking) {
            switch (direction) {
                case "up":
                    tempScreenY = ScreenX - gp.TileSize;
                    attack = attackup2;
                    break;
                case "down":
                    attack = attackdown2;
                    break;
                case "left":
                    tempScreenX = ScreenX - gp.TileSize;
                    attack = attackleft2;
                    break;
                case "right":
                    attack = attackright2;
                    break;
            }
        }

        // On change l'opacité si le joueur est invincible
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        if (!attacking) {
            // Dessine le joueur avec les images de personnalisation
            g2.drawImage(corps, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize, null);
            if (bas != null) g2.drawImage(bas, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize, null);
            if (haut != null) g2.drawImage(haut, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize, null);
            if (cheveux != null) g2.drawImage(cheveux, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize, null);
        }

        /*
        // Dessine l'attaque si le joueur est en train d'attaquer
        if (attack == attackup2 || attack == attackdown2) g2.drawImage(attack, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
        if (attack == attackright2 || attack == attackleft2) g2.drawImage(attack, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
        // on reset l'opacité à 1 pour les prochaines images
        */ 

        if (attack == attackup2 || attack == attackdown2) {
            g2.drawImage(corps, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
            if (bas != null) g2.drawImage(bas, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
            if (haut != null) g2.drawImage(haut, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
            if (cheveux != null) g2.drawImage(cheveux, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
            g2.drawImage(épée, tempScreenX, tempScreenY, gp.TileSize, gp.TileSize*2, null);
        }
        if (attack == attackright2 || attack == attackleft2) {
            g2.drawImage(corps, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
            if (bas != null) g2.drawImage(bas, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
            if (haut != null) g2.drawImage(haut, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
            if (cheveux != null) g2.drawImage(cheveux, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
            g2.drawImage(épée, tempScreenX, tempScreenY, gp.TileSize*2, gp.TileSize, null);
        }
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
