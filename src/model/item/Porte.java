package model.item;

import javax.imageio.ImageIO;

public class Porte extends Item {
    
    public Porte(){
        nom = "Porte";
        try {
            image = ImageIO.read(getClass().getResource("/res/Objects/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
