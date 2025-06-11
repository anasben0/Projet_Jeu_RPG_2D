package controller;

import Test.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerJoueur implements KeyListener {
    GamePanel gp;
    // Initialiser explicitement à false
    public boolean UpPressed = false, 
                   DownPressed = false, 
                   LeftPressed = false, 
                   RightPressed = false,
                   enterPressed = false;

    public boolean checkDrawTime = false;

    public KeyHandlerJoueur(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Pas besoin d'implémentation pour ce jeu
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            
            if ( gp.ui.titleScreenState == 0){
                
                if (code == KeyEvent.VK_Z) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2; // Boucle entre les options
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0; // Boucle entre les options
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        //gp.stopMusic();
                        gp.ui.titleScreenState = 1; // Passe au menu de personnalisation
                        gp.ui.commandNum = 0;
                        //gp.repaint(); // Redessine l'écran
                    }
                    if(gp.ui.commandNum == 1) {
                        // Logique pour les options
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0); // Quitter le jeu
                    }
                }

            }
            
            else if ( gp.ui.titleScreenState == 1){
                if (code == KeyEvent.VK_Z) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3; // Boucle entre les options
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0; // Boucle entre les options
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.stopMusic();
                        gp.playMusic(0);
                        System.out.println("Devient un crackhead");
                        gp.gameState=gp.playState;
                        //gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1) {
                        // Logique pour les options
                        gp.stopMusic();
                        gp.playMusic(0);
                        System.out.println("force a toi");
                        gp.gameState=gp.playState;

                    }
                    if(gp.ui.commandNum == 2) {
                        gp.stopMusic();
                        gp.playMusic(0);
                        System.out.println("soit loong commme un lundi");
                        gp.gameState=gp.playState;
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum =0;
                    }
                }

            }
            
            
        }

        // Vérifier l'état du jeu avant de traiter les entrées
        if (gp.gameState == gp.playState){
            if(code == KeyEvent.VK_Q) {
                LeftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                RightPressed = true;
            }
            if(code == KeyEvent.VK_Z) {
                UpPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                DownPressed = true;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if(code == KeyEvent.VK_P) {
                if(gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                    gp.joueur.direction = "down"; // Réinitialiser la direction du joueur
                }else if(gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }
        }
        //DEBUG

        if(code == KeyEvent.VK_T) {
            if( checkDrawTime == false){
                checkDrawTime = true;
            }
            else if (checkDrawTime == true){
                checkDrawTime = false;
            }
        }
        // pause state
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState; // Reprendre le jeu
            }
        }
        // dialogue state
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.ui.currentDialogue = ""; // Réinitialiser le dialogue
                gp.gameState = gp.playState; // Revenir au jeu
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Q) {
            LeftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            RightPressed = false;
        }
        if(code == KeyEvent.VK_Z) {
            UpPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            DownPressed = false;
        }
    }
}