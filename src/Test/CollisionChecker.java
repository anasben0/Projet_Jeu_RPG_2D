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

    public int CheckObject(Entite entite, boolean Player) {
        int index = 999;

        for (int i = 0; i < gp.item.length; i++) {
            if (gp.item[i] != null) {
                // on a besoin de mettre à jour la hitbox de l'entité
                entite.hitbox.x = entite.Worldx + entite.hitbox.x;
                entite.hitbox.y = entite.Worldy + entite.hitbox.y;

                gp.item[i].hitbox.x = gp.item[i].Worldx + gp.item[i].hitbox.x;
                gp.item[i].hitbox.y = gp.item[i].Worldy + gp.item[i].hitbox.y;

                switch(entite.direction) {
                    case "up":
                        entite.hitbox.y -= entite.speed;
                        break;
                    case "down":
                        entite.hitbox.y += entite.speed;
                        break;
                    case "left":
                        entite.hitbox.x -= entite.speed;
                        break;
                    case "right":
                        entite.hitbox.x += entite.speed;
                        break;
                }

                if ( entite.hitbox.intersects(gp.item[i].hitbox) ) {
                    if(gp.item[i].collision == true) {
                        entite.collisionOn = true;
                    }
                    if ( Player == true) {
                        index = i; // on retourne l'index de l'item
                    }
                }                
                entite.hitbox.x = entite.solidAreaDefaultX;
                entite.hitbox.y = entite.solidAreaDefaultY;
                gp.item[i].hitbox.x = gp.item[i].solidAreaDefaultX;
                gp.item[i].hitbox.y = gp.item[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    //collision entre entité
    public int checkEntite(Entite entite, Entite[] target) {
        int index = 999;

        for (int i = 0; i <target.length; i++) {

            if (target[i] != null) {
                // on a besoin de mettre à jour la hitbox de l'entité
                entite.hitbox.x = entite.Worldx + entite.hitbox.x;
                entite.hitbox.y = entite.Worldy + entite.hitbox.y;

                target[i].hitbox.x = target[i].Worldx + target[i].hitbox.x;
                target[i].hitbox.y = target[i].Worldy + target[i].hitbox.y;

                switch(entite.direction) {
                    case "up":
                        entite.hitbox.y -= entite.speed;
                        break;
                    case "down":
                        entite.hitbox.y += entite.speed;
                        break;
                    case "left":
                        entite.hitbox.x -= entite.speed;
                        break;
                    case "right":
                        entite.hitbox.x += entite.speed;
                        break;
                }

                if ( entite.hitbox.intersects(target[i].hitbox) ) {
                    if(target[i] != entite) { // on ne veut pas que l'entité se collisionne avec elle-même
                        entite.collisionOn = true;
                        index = i; // on retourne l'index de l'entité
                    }
                }
                entite.hitbox.x = entite.solidAreaDefaultX;
                entite.hitbox.y = entite.solidAreaDefaultY;
                target[i].hitbox.x = target[i].solidAreaDefaultX;
                target[i].hitbox.y = target[i].solidAreaDefaultY;
            }

        } return index;
    }
    public boolean checkJoueur(Entite entite) {
        boolean contactJoueur = false;
    
        // on a besoin de mettre à jour la hitbox de l'entité
        entite.hitbox.x = entite.Worldx + entite.hitbox.x;
        entite.hitbox.y = entite.Worldy + entite.hitbox.y;

        gp.joueur.hitbox.x = gp.joueur.Worldx + gp.joueur.hitbox.x;
        gp.joueur.hitbox.y = gp.joueur.Worldy + gp.joueur.hitbox.y;

        switch(entite.direction) {
            case "up":
                entite.hitbox.y -= entite.speed;
                break;
            case "down":
                entite.hitbox.y += entite.speed;
                break;
            case "left":
                entite.hitbox.x -= entite.speed;
                break;
            case "right":
                entite.hitbox.x += entite.speed;
                break;
        }
        if ( entite.hitbox.intersects(gp.joueur.hitbox) ) {
            entite.collisionOn = true; // on retourne true si l'entité est en contact avec le joueur
            contactJoueur = true;
        }
        entite.hitbox.x = entite.solidAreaDefaultX;
        entite.hitbox.y = entite.solidAreaDefaultY;
        gp.joueur.hitbox.x = gp.joueur.solidAreaDefaultX;
        gp.joueur.hitbox.y = gp.joueur.solidAreaDefaultY;
        return contactJoueur;
    }
}

