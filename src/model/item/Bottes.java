package model.item;

import Test.GamePanel;
import model.entite.Entite;

public class Bottes extends Entite {

    public Bottes(GamePanel gp) {
        super(gp);
        nom = "Bottes";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/Objects/boots.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false; // Les bottes ne sont pas un objet solide
    }

}
