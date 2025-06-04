package Test;

import model.entite.Entite;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void CheckTile(Entite entite) {
        int entiteLeftWorldX = entite.Worldx + entite.hitbox.x;
        int entiteRightWorldX = entite.Worldx + entite.hitbox.x + entite.hitbox.width;
        int entiteTopWorldY = entite.Worldy + entite.hitbox.y;
        int entiteBottomWorldY = entite.Worldy + entite.hitbox.y + entite.hitbox.height;

        int entiteLeftCol = entiteLeftWorldX / gp.TileSize;
        int entiteRightCol = entiteRightWorldX / gp.TileSize;
        int entiteTopRow = entiteTopWorldY / gp.TileSize;
        int entiteBottomRow = entiteBottomWorldY / gp.TileSize;

        int tileNum1, tileNum2;

        switch (entite.direction) {
            case "up":
                entiteTopRow = (entiteTopWorldY - entite.speed) / gp.TileSize;
                tileNum1 = gp.tileM.MapTileNum[entiteLeftCol][entiteTopRow];
                tileNum2 = gp.tileM.MapTileNum[entiteRightCol][entiteTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entite.collisionOn = true;
                }
                break;

            case "down":
                entiteBottomRow = (entiteBottomWorldY + entite.speed) / gp.TileSize;
                tileNum1 = gp.tileM.MapTileNum[entiteLeftCol][entiteBottomRow];
                tileNum2 = gp.tileM.MapTileNum[entiteRightCol][entiteBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entite.collisionOn = true;
                }
                break;

            case "left":
                entiteLeftCol = (entiteLeftWorldX - entite.speed) / gp.TileSize;
                tileNum1 = gp.tileM.MapTileNum[entiteLeftCol][entiteTopRow];
                tileNum2 = gp.tileM.MapTileNum[entiteLeftCol][entiteBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entite.collisionOn = true;
                }
                break;

            case "right":
                entiteRightCol = (entiteRightWorldX + entite.speed) / gp.TileSize;
                tileNum1 = gp.tileM.MapTileNum[entiteRightCol][entiteTopRow];
                tileNum2 = gp.tileM.MapTileNum[entiteRightCol][entiteBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entite.collisionOn = true;
                }
                break;
        }
    }
}

