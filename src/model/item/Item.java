package model.item;

import Test.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/** 
* Création des classes item
*/

// Cette classe représente un item dans le jeu.
public abstract class Item {
    public String nom;
    public BufferedImage image;
    public boolean collision = false;
    public int Worldx, Worldy;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48); // hitbox de l'item
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    //UtilityTool uTool = new UtilityTool();
    public String getNom() {
        return nom;
    }

    //on dessine l'item à l'écran
    public void draw(Graphics2D g2, GamePanel gp) {
        int ScreenX = Worldx - gp.joueur.Worldx + gp.joueur.ScreenX;
        int ScreenY = Worldy - gp.joueur.Worldy + gp.joueur.ScreenY;

        if(Worldx + gp.TileSize > gp.joueur.Worldx - gp.joueur.ScreenX &&
           Worldx - gp.TileSize < gp.joueur.Worldx + gp.joueur.ScreenX &&
           Worldy + gp.TileSize > gp.joueur.Worldy - gp.joueur.ScreenY &&
           Worldy - gp.TileSize < gp.joueur.Worldy + gp.joueur.ScreenY) {

           g2.drawImage(image, ScreenX, ScreenY, gp.TileSize, gp.TileSize, null);
        }

        
    }

}
