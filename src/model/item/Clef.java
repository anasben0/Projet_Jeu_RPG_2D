package model.item;

import Test.GamePanel;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import model.entite.Entite;

public class Clef extends Entite{

    public Clef(GamePanel gp){
        super(gp);

        nom = "Clef";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/key.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        int screenX = Worldx - gp.joueur.Worldx + gp.joueur.ScreenX;
        int screenY = Worldy - gp.joueur.Worldy + gp.joueur.ScreenY;

        // Vérifie si l'objet est dans la vue de la caméra
        if (Worldx + gp.TileSize > gp.joueur.Worldx - gp.joueur.ScreenX &&
            Worldx - gp.TileSize < gp.joueur.Worldx + gp.joueur.ScreenX &&
            Worldy + gp.TileSize > gp.joueur.Worldy - gp.joueur.ScreenY &&
            Worldy - gp.TileSize < gp.joueur.Worldy + gp.joueur.ScreenY) {
            
            g2.drawImage(image, screenX, screenY, gp.TileSize, gp.TileSize, null);
            
            
        }
    }
}
