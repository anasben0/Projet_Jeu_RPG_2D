package Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16 ;
    final int scale = 3;
    final int TileSize = originalTileSize* scale ;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int ScreenWidth = maxScreenCol * TileSize;
    final int ScreenHeight = maxScreenRow * TileSize;

    Thread gameThread ;

    public GamePanel () {
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);

    }

    public void startGameThread (){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        while ( gameThread != null) {

            System.out.println("The game is looping");

            update();
            
            repaint();
        }
    }
    public void update (){

    }
    public void paintComponent (Graphics g) {


        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect( 100,100, TileSize, TileSize);
        g2.dispose();

    }


}
