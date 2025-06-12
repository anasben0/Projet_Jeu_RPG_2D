package Test;

import controller.ControllerPersonnalisation;
import controller.EventHandler;
import controller.KeyHandlerJoueur;
import controller.MouseHandlerPersonnalisation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import model.entite.Entite;
import model.entite.Joueur;
import tile.TileManager;
import view.ViewPersonnalisation;



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

    // Systeme du jeu
    TileManager tileM = new TileManager(this);
    public KeyHandlerJoueur KeyH = new KeyHandlerJoueur(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetChecker aChecker = new AssetChecker(this);

    // Sons du jeu
    public Sound music = new Sound();
    public Sound se = new Sound();

    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);

    Thread gameThread ;

    // Entités du jeu
    public Joueur joueur = new Joueur(10,this,KeyH);
    public Entite item[]= new Entite[10];
    public Entite pnj[] = new Entite[10];
    public Entite monstre[] = new Entite[10];
    ArrayList<Entite> entityList = new ArrayList<>();
    // Game state
    public int gameState;
    public final int titleState = 0; // écran titre
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int personnalisationState = 4;
    public final int winState = 5; // État pour l'écran "Vous avez Gagné"
    public final int gameOverState = 6;

    // Constructeur de la classe GamePanel
    public GamePanel () {
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.addMouseListener(new MouseHandlerPersonnalisation(this));
        this.setFocusable(true);
        
        // Vérification des ressources
        try {
            InputStream is = getClass().getResourceAsStream("/res/Monstre/greenslime_down_1.png");
            if (is == null) {
                System.out.println("ERREUR: Impossible de trouver les ressources du monstre");
            } else {
                is.close();
                System.out.println("Ressources du monstre trouvées avec succès");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour configurer le jeu
    public void setUpGame () {
        aChecker.createItem();
        aChecker.setMonstre();
        aChecker.setPNJ();
        playMusic(4); // on démarre la musique de fond
        gameState = titleState; // on démarre le jeu en mode "title"
        ui.viewPersonnalisation = new ViewPersonnalisation(this, joueur, joueur.getCatalogue());
        ui.controllerPersonnalisation = new ControllerPersonnalisation(this, joueur, joueur.getCatalogue(), ui.viewPersonnalisation);
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
    // ici on met à jour les éléments du jeu
    public void update (){

        if(gameState == playState) {
            joueur.update();

            for (int i = 0; i < pnj.length; i++) {
                if(pnj[i] != null) {
                    pnj[i].update();
                }
            }

            boolean allMonstersDead = true; // Variable pour vérifier si tous les monstres sont morts
            for (int i = 0; i < monstre.length; i++) {
                if (monstre[i] != null) {
                    if (monstre[i].alive == true && monstre[i].dying == false) {
                        monstre[i].update();
                        allMonstersDead = false; // Au moins un monstre est encore vivant
                    }
                    if (monstre[i].alive == false) {
                        monstre[i] = null;
                    }
                }
            }

            // Si tous les monstres sont morts, passez à l'état "winState"
            if (allMonstersDead) {
                gameState = winState;
                stopMusic();
                //playMusic(2);
            }

            // Si le joueur est mort, passez à l'état "gameOverState"
            if (joueur.life <= 0) {
                System.out.println("Le joueur est mort. Transition vers gameOverState.");
                gameState = gameOverState;
                stopMusic();
            }
        }

        if(gameState == pauseState) {
            // on ne fait rien, le jeu est en pause
        }

        
    }

    // ici on dessine les elements du jeu
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    // État title
    if (gameState == titleState) {
        ui.draw(g2);
    } 
    // État personnalisation
    else if (gameState == personnalisationState) {
        if (ui.viewPersonnalisation != null) {
            ui.viewPersonnalisation.draw(g2);
        }
    }
    // État game over
    else if (gameState == gameOverState) {
        ui.gameOverScreen(g2); // Passez g2 en paramètre
    }
    // État victoire
    else if (gameState == winState) {
        ui.drawWinState(g2); // Passez g2 en paramètre
    }
    // Autres états (play, pause, dialogue)
    else {
        tileM.draw(g2);
        
        // Ajout des entités
        entityList.add(joueur);
        for (Entite pnj : pnj) if (pnj != null) entityList.add(pnj);
        for (Entite monstre : monstre) if (monstre != null) entityList.add(monstre);
        
        // Tri et dessin
        Collections.sort(entityList, (e1, e2) -> Integer.compare(e1.Worldy, e2.Worldy));
        for (Entite e : entityList) e.draw(g2);
        entityList.clear();
        
        ui.draw(g2);
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
