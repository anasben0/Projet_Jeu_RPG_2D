package model.item;

import Test.GamePanel;
import javax.imageio.ImageIO;
import model.entite.Entite;

public class Coffre extends Entite {
    
    public Coffre(GamePanel gp) {

        super(gp);
        nom = "Coffre";
        try {
            image = ImageIO.read(getClass().getResource("/res/Objects/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
