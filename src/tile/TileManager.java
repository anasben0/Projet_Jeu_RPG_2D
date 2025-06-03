package tile;

import Test.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

// ici on a le debut du plateau de jeu en "tiles"

public class TileManager {
    GamePanel gp;
    Tile [] tile;
    int MapTileNum[][];
    // on initialise le tableau de tiles et le tableau de numéros de tiles
    public TileManager(GamePanel gp){
        this.gp=gp;
        // on initialise le tableau de tiles avec 10 tiles
        tile = new Tile[10];
        MapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        // on initialise les tiles
        GetTileImage();
        loadMap("/res/Maps/world01.txt");
    }
    // on charge les images des tiles
    public void GetTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/water00.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/Tiles/earth.png"));

        }catch ( IOException e){
            e.printStackTrace();
        }
    }
    // on charge la map depuis un fichier texte
    public void loadMap(String filepath){
        
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // on lit le fichier ligne par ligne et on remplit le tableau MapTileNum
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    MapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }
    public void draw(Graphics2D g2){

        int Worldcol = 0;
        int Worldrow = 0;


        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow){

            int tileNum = MapTileNum[Worldcol][Worldrow];

            // on calcule la position du monde en fonction de la position de la tile
            int Worldx = Worldcol * gp.TileSize;
            int Worldy = Worldrow * gp.TileSize;

            // on calcule la position de l'écran en fonction de la position du joueur
            int ScreenX = Worldx - gp.joueur.Worldx + gp.joueur.ScreenX;
            int ScreenY = Worldy - gp.joueur.Worldy + gp.joueur.ScreenY;

            if (Worldx + gp.TileSize> gp.joueur.Worldx - gp.joueur.ScreenX &&
                Worldx - gp.TileSize < gp.joueur.Worldx + gp.joueur.ScreenX  &&
                Worldy + gp.TileSize > gp.joueur.Worldy - gp.joueur.ScreenY &&
                Worldy - gp.TileSize < gp.joueur.Worldy + gp.joueur.ScreenY ){
                // si la tile est en dehors de l'écran, on ne la dessine pas
                g2.drawImage(tile[tileNum].image, ScreenX, ScreenY,gp.TileSize,gp.TileSize, null);
            }
            //concrètement on va dessiner les tiles en fonction de leur numéro
            Worldcol++;

            if(Worldcol >= gp.maxWorldCol){
                Worldcol = 0;
                Worldrow ++;
            }
        }
    }
}
