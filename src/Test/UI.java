package Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.item.Clef;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        // on charge l'image de la clé
        Clef clef = new Clef();
        keyImage = clef.image; 
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if(gameFinished) {
            g2.setFont(arial_80B);
            g2.setColor(Color.WHITE);
            String text = "Vous avez gagné !";
            int x = gp.ScreenWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            int y = gp.ScreenHeight/2 - (gp.TileSize * 3);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
        }
        else {
            // Dessine le fond
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.TileSize/2,gp.TileSize/2,gp.TileSize, gp.TileSize, null);
            g2.drawString("x " + gp.joueur.hasKey, 74, 66);
            if(messageOn) {
                // Affiche le message
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message,gp.TileSize/2,gp.TileSize * 5);
                messageCounter++;
            if(messageCounter > 120) { // 2 secondes
                messageCounter = 0;
                messageOn = false;
            }
            }
        }
            
    }

}
