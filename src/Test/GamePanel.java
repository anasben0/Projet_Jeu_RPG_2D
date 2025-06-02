package Test;

import controller.KeyHandlerJoueur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.entite.Joueur;



public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16 ;
    final int scale = 3;
    public final int TileSize = originalTileSize* scale ;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int ScreenWidth = maxScreenCol * TileSize;
    final int ScreenHeight = maxScreenRow * TileSize;

    int FPS =60;

    KeyHandlerJoueur KeyH = new KeyHandlerJoueur();
    Thread gameThread ;

    Joueur joueur = new Joueur(10,this,KeyH);

    // variables pour le joueur
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel () {
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread (){
        gameThread = new Thread(this);
        gameThread.start();
    }


@Override
public void run() {
    double drawInterval = 1000000000/60; // 60 FPS
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
        joueur.update();
    }
    public void paintComponent (Graphics g) {


        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        joueur.draw(g2);

        g2.dispose();

    }


}
