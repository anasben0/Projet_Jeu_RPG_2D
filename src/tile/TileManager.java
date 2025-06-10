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
    public Tile [] tile;
    public int MapTileNum[][];
    // on initialise le tableau de tiles et le tableau de numéros de tiles
    public TileManager(GamePanel gp){
        this.gp=gp;
        // on initialise le tableau de tiles avec 50 tiles
        tile = new Tile[50];
        MapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        // on initialise les tiles
        GetTileImage();
        loadMap("/res/Maps/worldV2.txt");
    }
    
    // on charge les images des tiles
    /*
    public void GetTileImage(){
            
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/res/Tiles/grass00.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResource("/res/Tiles/grass02.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResource("/res/Tiles/grass03.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResource("/res/Tiles/grass04.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResource("/res/Tiles/grass05.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public void GetTileImage() {
        String[] tileNames = {
            "grass00", "grass00", "grass00", "grass00", "grass00", "grass00",
            "grass00", "grass00", "grass00", "grass00", "grass00",
            "grass01", "water00", "water01", "water02", "water03", "water04",
            "water05", "water06", "water07", "water08", "water09", "water10",
            "water11", "water12", "water13", "road00", "road01", "road02",
            "road03", "road04", "road05", "road06", "road07", "road08","road09",
            "road10", "road11", "road12", "earth", "wall", "tree"
        };

        try {
            for (int i = 0; i < tileNames.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResource("/res/Tiles/" + tileNames[i] + ".png"));

                // Si certaines tuiles ont des collisions, vous pouvez les configurer ici
                if (tileNames[i].equals("wall") || tileNames[i].equals("water00") || tileNames[i].equals("water01") || tileNames[i].equals("water02")
                        || tileNames[i].equals("water03") || tileNames[i].equals("water04") || tileNames[i].equals("water05")
                        || tileNames[i].equals("water06") || tileNames[i].equals("water07") || tileNames[i].equals("water08")
                        || tileNames[i].equals("water09") || tileNames[i].equals("water10") || tileNames[i].equals("water11")
                        || tileNames[i].equals("water12") || tileNames[i].equals("water13") || tileNames[i].equals("tree")) {
                    tile[i].collision = true;
                }
            }
        } catch (IOException e) {
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
