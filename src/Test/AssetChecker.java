package Test;

import model.entite.Monstre;
import model.entite.PNJ;

public class AssetChecker {
    GamePanel gp;

    public AssetChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void createItem() {    

        try {
            gp.item[0] = new model.item.Porte(gp);
            gp.item[0].Worldx = 10 * gp.TileSize;
            gp.item[0].Worldy = 12 * gp.TileSize;

            gp.item[1] = new model.item.Clef(gp);
            gp.item[1].Worldx = 9 * gp.TileSize;
            gp.item[1].Worldy = 25 * gp.TileSize;

            gp.item[2] = new model.item.Clef(gp);
            gp.item[2].Worldx = 10 * gp.TileSize;
            gp.item[2].Worldy = 9 * gp.TileSize;

            gp.item[3] = new model.item.Porte(gp);
            gp.item[3].Worldx = 12 * gp.TileSize;
            gp.item[3].Worldy = 23 * gp.TileSize;

            System.out.println("Items initialisés avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des items:");
            e.printStackTrace();
        }
    }
    public void setPNJ() {
        gp.pnj[0] = new PNJ(gp);
        gp.pnj[0].Worldx = 21 * gp.TileSize;
        gp.pnj[0].Worldy = 21 * gp.TileSize;

        gp.pnj[1] = new PNJ(gp);
        gp.pnj[1].Worldx = 38 * gp.TileSize;
        gp.pnj[1].Worldy = 12 * gp.TileSize;

    }

    public void setMonstre() {
        try {
            gp.monstre[0] = new Monstre(gp);
            gp.monstre[0].Worldx = 23 * gp.TileSize;
            gp.monstre[0].Worldy = 36 * gp.TileSize;

            gp.monstre[1] = new Monstre(gp);
            gp.monstre[1].Worldx = 23 * gp.TileSize;
            gp.monstre[1].Worldy = 37 * gp.TileSize;

            gp.monstre[2] = new Monstre(gp);
            gp.monstre[2].Worldx = 38 * gp.TileSize;
            gp.monstre[2].Worldy = 13 * gp.TileSize;

            System.out.println("Monstres initialisés avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation des monstres:");
            e.printStackTrace();
        }
    }
}