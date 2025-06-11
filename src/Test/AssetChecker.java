package Test;

import model.entite.PNJ;
import model.item.Clef;
import model.item.Porte;

public class AssetChecker {
    GamePanel gp;

    public AssetChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void createItem() {
       gp.item[0] = new Porte(gp);
       gp.item[0].Worldx = gp.TileSize * 12;
       gp.item[0].Worldy = gp.TileSize * 23;
       
       gp.item[1] = new Clef(gp);
       gp.item[1].Worldx = gp.TileSize * 14;
       gp.item[1].Worldy = gp.TileSize * 22;
       System.out.println("Item created");

    }
    public void setPNJ() {
        gp.pnj[0] = new PNJ(gp);
        gp.pnj[0].Worldx = 21 * gp.TileSize;
        gp.pnj[0].Worldy = 21 * gp.TileSize;

        gp.pnj[1] = new PNJ(gp);
        gp.pnj[1].Worldx = 31 * gp.TileSize;
        gp.pnj[1].Worldy = 21 * gp.TileSize;
    }
}