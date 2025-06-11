package model.item;

import Test.GamePanel;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import model.entite.Entite;

public class Porte extends Entite {
    
    public Porte(GamePanel gp){
        super(gp);
        nom = "Porte";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/door.png"));
            if (image == null) {
                System.out.println("Erreur: L'image door.png n'a pas pu être chargée");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de door.png");
            e.printStackTrace();
        }
        collision = true; // La porte est un objet solide
        hitbox.x = 0;
        hitbox.y = 16;
        hitbox.width = 48;
        hitbox.height = 32; // Ajustement de la hitbox pour la porte
        solidAreaDefaultX = hitbox.x;
        solidAreaDefaultY = hitbox.y;
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
