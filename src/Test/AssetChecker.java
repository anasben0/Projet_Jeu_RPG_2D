package Test;

import model.entite.PNJ;

public class AssetChecker {
    GamePanel gp;

    public AssetChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void createItem() {
       

    }
    public void setPNJ() {
        gp.pnj[0] = new PNJ(gp);
        gp.pnj[0].Worldx = 21 * gp.TileSize;
        gp.pnj[0].Worldy = 21 * gp.TileSize;
    }
}