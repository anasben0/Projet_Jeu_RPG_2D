package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerJoueur implements KeyListener {

    // Initialiser explicitement à false
    public boolean UpPressed = false, 
                   DownPressed = false, 
                   LeftPressed = false, 
                   RightPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // Pas besoin d'implémentation pour ce jeu
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

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