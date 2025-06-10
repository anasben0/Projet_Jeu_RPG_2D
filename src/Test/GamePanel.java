package Test;

import controller.KeyHandlerJoueur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.entite.Entite;
import model.entite.Joueur;
import model.item.Item;
import tile.TileManager;



public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16 ;
    final int scale = 3;
    public final int TileSize = originalTileSize* scale ;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = maxScreenCol * TileSize;
    public final int ScreenHeight = maxScreenRow * TileSize;

    // GAME SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //public final int WorldWidth = TileSize * maxWorldCol;
    //public final int WorldHeight = TileSize * maxWorldRow;


    // FPS
    int FPS =60;
    TileManager tileM = new TileManager(this);
    public KeyHandlerJoueur KeyH = new KeyHandlerJoueur(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetChecker aChecker = new AssetChecker(this);

    // Sons du jeu
    public Sound music = new Sound();
    public Sound se = new Sound();

    public UI ui = new UI(this);

    Thread gameThread ;

    // Entités du jeu
    public Joueur joueur = new Joueur(10,this,KeyH);
    public Item item[]= new Item[10];
    public Entite pnj[] = new Entite[10];
    // Game state
    public int gameState;
    public final int titleState = 0; // écran titre
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel () {
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void setUpGame () {
        aChecker.createItem();
        aChecker.setPNJ();
        playMusic(4); // on démarre la musique de fond
        gameState = titleState; // on démarre le jeu en mode "title"
    }

    public void startGameThread (){
        gameThread = new Thread(this);
        gameThread.start();
    }


@Override
public void run() {
    double drawInterval = 1000000000/FPS; // 60 FPS
    double nextDrawTime = System.nanoTime() + drawInterval;
    
    while (gameThread != null) {
        update();
        repaint();
        
        try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;
            
            if(remainingTime < 0) {
                remainingTime = 0;
            }
            
            Thread.sleep((long) remainingTime);
            nextDrawTime += drawInterval;
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    public void update (){

        if(gameState == playState) {
            joueur.update();

            for (int i = 0; i < pnj.length; i++) {
                if(pnj[i] != null) {
                    pnj[i].update();
                }
            }
        }
        if(gameState == pauseState) {
            // on ne fait rien, le jeu est en pause
        }
    }

    // ici on dessine les elements du jeu
    public void paintComponent (Graphics g) {


        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        //debug
        long drawstart = 0;
        if(KeyH.checkDrawTime == true){
            drawstart = System.nanoTime();
        }
        
        // fenetre de titre
        if (gameState == titleState){
            ui.draw(g2);
        }
        //jeu en cours
        else{
            //tiles
            tileM.draw(g2);

            //items
            for (int i = 0; i < item.length; i++) {
                if(item[i] != null) {
                    item[i].draw(g2, this);
                }
            }
            //PNJ
            for (int i = 0; i < pnj.length; i++) {
                if(pnj[i] != null) {
                    pnj[i].draw(g2);
                }
            }
            //joueur
            joueur.draw(g2);
            // UI
            ui.draw(g2);
        }

        //DEBUG
        if(KeyH.checkDrawTime == true){
                long drawend = System.nanoTime();
                long passed = drawend - drawstart;
                g2.setColor(Color.WHITE);
                g2.drawString("drawtime: " + passed, 10, 400);
                System.out.println("Draw Time:" + passed);
        }
        g2.dispose();

    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

}
