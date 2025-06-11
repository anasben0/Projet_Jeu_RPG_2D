package model.item;

import Test.GamePanel;
import javax.imageio.ImageIO;
import model.entite.Entite;

public class Heart extends Entite{

    public Heart(GamePanel gp){

        super(gp);
        nom = "Heart";


        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/Objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/Objects/heart_blank.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
