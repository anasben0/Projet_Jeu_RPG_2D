package controller;

import Test.GamePanel;

public class EventHandler {
    // Cette classe gère les événements du jeu, comme les entrées utilisateur,
    // les collisions, et d'autres interactions dans le jeu.

    GamePanel gp;
    EventRect eventRect[][]; // Rectangle pour détecter les événements

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while(col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col =0;
                row++;
            }
        }


    }

    public void checkEvent(){ 

        // on verifie que le joueur est au moins à une tile de distance du dernier event
        int xDistance = Math.abs(gp.joueur.Worldx - previousEventX);
        int yDistance = Math.abs(gp.joueur.Worldy - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.TileSize){
            canTouchEvent = true;
        }
        if (canTouchEvent == true){
            if( hit(19,16,"left")){ damagepit(gp.dialogueState,19,16);}
            if( hit(23,12,"up")){ Fontaine(gp.dialogueState,23,12);}
            if( hit(27,16,"right")){ Teleportation(gp.dialogueState);}
            if( hit(23,19,"any")){ damagepit(gp.dialogueState,23,19);}
        }
        
    
    }
    
    public boolean hit ( int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gp.joueur.hitbox.x = gp.joueur.Worldx + gp.joueur.hitbox.x;
        gp.joueur.hitbox.y = gp.joueur.Worldy + gp.joueur.hitbox.y;
        eventRect[eventCol][eventRow].x = eventCol * gp.TileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow * gp.TileSize + eventRect[eventCol][eventRow].y;

        if (gp.joueur.hitbox.intersects(eventRect[eventCol][eventRow])  && eventRect[eventCol][eventRow].eventDone == false) {
            if (gp.joueur.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;

                previousEventX = gp.joueur.Worldx;
                previousEventY = gp.joueur.Worldy;
            }
        }
        // Réinitialiser la position de l'eventRect
        gp.joueur.hitbox.x = gp.joueur.solidAreaDefaultX;
        gp.joueur.hitbox.y = gp.joueur.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;

        return hit;
    }

    public void damagepit(int gameState, int col, int row){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "La malédiction de Tung Tung Tung \nSahuur s'abat sur toi !";
        gp.joueur.life -= 1;
        eventRect[col][row].eventDone = true; // fais en sorte que l'evenement ne se produise qu'une seule fois
        canTouchEvent = false;
    }

    public void Fontaine(int gameState, int col, int row){
        if(gp.KeyH.enterPressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "La fontaine de Jouvence te redonnes \nde la vie !";
            gp.joueur.life += 1;
            if(gp.joueur.life > gp.joueur.maxLife){
                gp.joueur.life = gp.joueur.maxLife;
            }
        }
    }

    public void Teleportation(int gameState){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Tu es téléporté contre ta volonté !";
            gp.joueur.Worldx = gp.TileSize * 38;
            gp.joueur.Worldy = gp.TileSize * 10;
            gp.joueur.direction = "down";
            
        
    }


}
