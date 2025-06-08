package model.item;

public class Bottes extends Item {

    public Bottes() {
        nom = "Bottes";
        try {
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/Objects/boots.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false; // Les bottes ne sont pas un objet solide
    }

}
