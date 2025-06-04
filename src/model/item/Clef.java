package model.item;

import javax.imageio.ImageIO;

public class Clef extends Item{
    public Clef(){
        nom = "Clef";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/key.png"));

        } catch (Exception e) {
            e.printStackTrace();
    }
}
}
