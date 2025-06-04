package Test;

import model.item.Clef;
import model.item.Coffre;
import model.item.Porte;

//cette classe sert à verifier les assets du jeu

public class AssetChecker {
    GamePanel gp;

    public AssetChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void createItem() {
        gp.item[0] = new Clef();
        gp.item[0].Worldx = 23 * gp.TileSize;
        gp.item[0].Worldy = 7 * gp.TileSize;

        gp.item[1] = new Clef();
        gp.item[1].Worldx = 23 * gp.TileSize;
        gp.item[1].Worldy = 40 * gp.TileSize;

        gp.item[2] = new Clef();
        gp.item[2].Worldx = 38 * gp.TileSize;
        gp.item[2].Worldy = 9 * gp.TileSize;

        gp.item[3] = new Porte();
        gp.item[3].Worldx = 10 * gp.TileSize;
        gp.item[3].Worldy = 11 * gp.TileSize;

        gp.item[4] = new Porte();
        gp.item[4].Worldx = 8 * gp.TileSize;
        gp.item[4].Worldy = 28 * gp.TileSize;

        gp.item[5] = new Porte();
        gp.item[5].Worldx = 12 * gp.TileSize;
        gp.item[5].Worldy = 22 * gp.TileSize;

        gp.item[6] = new Coffre();
        gp.item[6].Worldx = 10 * gp.TileSize;
        gp.item[6].Worldy = 7 * gp.TileSize;

    }
}