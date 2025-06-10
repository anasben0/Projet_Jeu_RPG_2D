package model.item;

import javax.imageio.ImageIO;

public class Coffre extends Item {
    
    public Coffre() {
        nom = "Coffre";
        try {
            image = ImageIO.read(getClass().getResource("/res/Objects/chest.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
