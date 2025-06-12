package Test;

import controller.ControllerPersonnalisation;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import model.entite.Entite;
import model.item.Heart;
import view.ViewPersonnalisation;


// Cette classe gère l'interface utilisateur du jeu
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font cambria_40, cambria_80B;
    BufferedImage heart_full,heart_half,heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0; // pour le menu de commande
    public int titleScreenState = 0; // pour l'état de l'écran de titre
    public ViewPersonnalisation viewPersonnalisation;
    public ControllerPersonnalisation controllerPersonnalisation;

    // Constructeur de la classe UI
    public UI(GamePanel gp) {
        this.gp = gp;
        cambria_40 = new Font("Cambria", Font.PLAIN, 40);
        cambria_80B = new Font("Cambria", Font.BOLD, 80);
        // on charge l'image de la clé
        //Clef clef = new Clef();
        //keyImage = clef.image;

        Entite heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    
    // on dessine l'interface utilisateur
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(cambria_40);

        // Remplir l'arrière-plan en noir seulement pour l'écran titre
        if(gp.gameState == gp.titleState) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
            drawTitleScreen();
        }
        // Pour tous les autres états
        else {
            // Dessiner d'abord le jeu
            drawGame();
            
            // Puis les éléments UI
            drawPlayerLife();
            
            if(messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.TileSize/2, gp.TileSize*5);
                
                messageCounter++;
                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
            
            // Dessiner les écrans spéciaux par-dessus
            if(gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
            if(gp.gameState == gp.dialogueState) {
                drawDialogueScreen();
            }
        }
    }

    // Nouvelle méthode pour dessiner le jeu
    private void drawGame() {
        // Dessiner les tuiles
        gp.tileM.draw(g2);

        // Ajouter les entités à la liste
        gp.entityList.add(gp.joueur);

        for(int i = 0; i < gp.pnj.length; i++) {
            if(gp.pnj[i] != null) {
                gp.entityList.add(gp.pnj[i]);
            }
        }

        for(int i = 0; i < gp.item.length; i++) {
            if(gp.item[i] != null) {
                gp.entityList.add(gp.item[i]);
            }
        }

        // Trier les entités par position Y
        Collections.sort(gp.entityList, (e1, e2) -> 
            Integer.compare(e1.Worldy, e2.Worldy));

        // Dessiner toutes les entités
        for(Entite entity : gp.entityList) {
            entity.draw(g2);
        }

        // Vider la liste
        gp.entityList.clear();
    }

    public void drawPlayerLife(){

        //gp.joueur.life = 5;
        //position
        int x = gp.TileSize/2;
        int y = gp.TileSize/2;
        int i = 0;
        
        // MAX LIFE
        while (i< gp.joueur.maxLife/2){
            g2.drawImage(heart_blank, x, y,null);
            i++;
            x += gp.TileSize;
        }

        // RESET

        x = gp.TileSize/2;
        y = gp.TileSize/2;
        i = 0;

        // vie actuelle
        while ( i< gp.joueur.life){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if (i < gp.joueur.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.TileSize;
        }


    }

    // on dessine l'écran de titre
    public void drawTitleScreen() {

        if(titleScreenState == 0) {
            //gp.playMusic(0);

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
        /** else if(titleScreenState == 1){
            // personnalisation du personnage
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(40f));

            String text = "PERSONNALISATION";
            int x = getXforCenteredText(text);
            int y = gp.TileSize * 3;
            g2.drawString(text, x, y);

            text = "VALISOA";
            x = getXforCenteredText(text);
            y += gp.TileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x - gp.TileSize, y);
            }

            text = "GABOR";
            x = getXforCenteredText(text);
            y += gp.TileSize * 1;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x - gp.TileSize, y);
            }

            text = "VIDAL";
            x = getXforCenteredText(text);
            y += gp.TileSize * 1;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x - gp.TileSize, y);
            }

            text = "RETOUR";
            x = getXforCenteredText(text);
            y += gp.TileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3){
                g2.drawString(">", x - gp.TileSize, y);
            } **/
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
