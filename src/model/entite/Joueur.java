package model.entite;
import Test.GamePanel;
import controller.KeyHandlerJoueur;
import java.awt.Graphics2D;
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

    GamePanel gp;
    KeyHandlerJoueur KeyH ; 

    public Joueur (int vie, GamePanel gp, KeyHandlerJoueur KeyH) {
        super(vie, 1);
        this.inventaire = new Inventaire();
        this.gp = gp;
        this.KeyH = KeyH;
        SetDefaultValues();
        getPlayerImage();
    }
    public void SetDefaultValues(){
        x =100;
        y=100;
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
        if (KeyH.UpPressed == true){
            direction ="up";
            y -= speed;
        }
        else if (KeyH.DownPressed == true){
            direction ="down";
            y += speed;
        }
        else if (KeyH.RightPressed == true){
            direction ="right";
            x += speed;
        }
        else if (KeyH.LeftPressed == true){
            direction ="left";
            x -= speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up": 
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.TileSize, gp.TileSize, null);

       
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
