package model.entite;

import Test.GamePanel;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class PNJ extends Entite {
    
    public PNJ(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    /**
     * Méthode pour afficher le PNJ
     */
    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/PNJ/oldman_left_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        dialogue[0] = "Brrr Brrr PATAPIM !";
        dialogue[1] = "Bienvenue dans \nRaphael Pro Gamer v1.0 !";
        dialogue[2] = "Ce jeu est encore en \ndéveloppement !";
        dialogue[3] = "On va se voir bien souvent !";
    }
    public void setAction() {

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
        // le PNJ n'a pas d'action spécifique pour l'instant
        
    }
    public void speak() {
        super.speak(); // Appelle la méthode speak de la classe Entite
    }
}