package Test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

// cette classe gère l'interface utilisateur du jeu
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font cambria_40, cambria_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0; // pour le menu de commande
    
    public UI(GamePanel gp) {
        this.gp = gp;
        cambria_40 = new Font("Cambria", Font.PLAIN, 40);
        cambria_80B = new Font("Cambria", Font.BOLD, 80);
        // on charge l'image de la clé
        //Clef clef = new Clef();
        //keyImage = clef.image; 
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    // on dessine l'interface utilisateur
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(cambria_40);
        g2.setColor(Color.WHITE);
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();

        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }
    // on dessine l'écran de titre
    public void drawTitleScreen() {
        // on dessine le fond
        g2.setColor(new Color(0,0,0)); // couleur noir
        g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        // on dessine le titre
        String text = "R . P . G";
        //g2.setFont(cambria_80B);
        int x = getXforCenteredText(text);
        int y = gp.TileSize * 3;
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);
        // SHADOW
        g2.setColor(Color.WHITE);
        g2.drawString(text, x , y );
        // on dessine le personnage principal
        x = gp.ScreenWidth / 2  - (gp.TileSize*2 )/ 2;
        y += gp.TileSize * 2;
        g2.drawImage(gp.joueur.down1, x, y-20, gp.TileSize*2, gp.TileSize*2, null);
        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        // Nouvelle partie
        text = "NOUVELLE PARTIE";
        x = getXforCenteredText(text);
        y += gp.TileSize * 3;
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);
        // SHADOW
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.TileSize, y); // on dessine le curseur
        }
        // Continuer
        text = "CONTINUER";
        x = getXforCenteredText(text);
        y += gp.TileSize + 20;
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);
        // SHADOW
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.TileSize, y); // on dessine le curseur
        }
        // Quitter
        text = "QUITTER";
        x = getXforCenteredText(text);
        y += gp.TileSize + 20;
        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);
        // SHADOW
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.TileSize, y); // on dessine le curseur
        }
    }
    // on dessine l'écran de pause
    public void drawPauseScreen() {
        String text = "PAUSE";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        int x = getXforCenteredText(text);
        int y = gp.ScreenHeight / 2 - (gp.TileSize * 2);
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        //on dessine le fond du dialogue
        int x = gp.TileSize * 2;
        int y = gp.TileSize / 2;
        int width = gp.ScreenWidth - (gp.TileSize * 4);
        int height = gp.TileSize * 4;
        drawSubWindow(x, y, width, height);
        // on dessine le texte du dialogue
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 29F));
        x += gp.TileSize;
        y += gp.TileSize -1;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40; // on espace les lignes de 40 pixels
        }
    }
    // on dessine une fenêtre de dialogue
    public void drawSubWindow(int x, int y, int width, int height){
        // on dessine le fond du dialogue
        Color c = new Color(0, 0, 0, 200); // couleur noire avec transparence
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255); // couleur blanche
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    // on centre le texte dans l'écran
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.ScreenWidth / 2 - length / 2;
    }
}
