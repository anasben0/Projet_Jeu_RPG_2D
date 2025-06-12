package Test;

import model.entite.Monstre;
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

    public void setMonstre() {
        try {
            gp.monstre[0] = new Monstre(gp);
            gp.monstre[0].Worldx = 23 * gp.TileSize;
            gp.monstre[0].Worldy = 36 * gp.TileSize;

            gp.monstre[1] = new Monstre(gp);
            gp.monstre[1].Worldx = 23 * gp.TileSize;
            gp.monstre[1].Worldy = 37 * gp.TileSize;

            System.out.println("Monstres initialisés avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des monstres:");
            e.printStackTrace();
        }
    }
}