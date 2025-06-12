package model.entite;
import Test.GamePanel;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Classe représentant un monstre dans le jeu.
 * Hérite de la classe Entité.
 */

public class Monstre extends Entite {
    
    public Monstre(GamePanel gp) {
        super(gp);
        type = 2; // Type 2 pour les monstres
        direction = "down";
        speed = 1;
        nom = "Green Slime";

        maxLife = 3;
        life = maxLife;
        

        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42;
        hitbox.height = 30;
        solidAreaDefaultX = hitbox.x;
        solidAreaDefaultY = hitbox.y;

        getImage();
    }

    public void getImage() {
        try {
            // Chargement des images
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Monstre/greenslime_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Monstre/greenslime_down_2.png"));
            
            // Utiliser les mêmes images pour toutes les directions
            up1 = down1;
            up2 = down2;
            left1 = down1;
            left2 = down2;
            right1 = down1;
            right2 = down2;

            if (down1 == null || down2 == null) {
                System.out.println("ERREUR: Images du monstre non chargées");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(){
        // TODO: Implement monster AI behavior
        actionLockCounter++;
        if (actionLockCounter == 120) { // Change de direction toutes les 120 frames
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Génère un nombre aléatoire entre 1 et 100
            if (i <= 25) {
                direction = "up";
            }if (i> 25 && i <= 50) {
                direction = "down";
            }if (i > 50 && i <= 75) {
                direction = "left";
            }if (i > 75) {
                direction = "right";
            }

            actionLockCounter = 0; // Réinitialise le compteur d'action
        }
    }

    

    

}
