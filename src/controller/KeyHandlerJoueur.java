package controller;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

// Ce controller gère interactions portant sur le joueur (mouvement, actions, etc.)
public class KeyHandlerJoueur implements KeyListener {

    public Boolean UpMoving, 
                   DownMoving, 
                   LeftMoving, 
                   RightMoving;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Get key code from the event
        int code = e.getKeyCode();

        // verify which key was pressed
        if(code == KeyEvent.VK_Q) {
            LeftMoving = true; // Moving left
        }
        if(code == KeyEvent.VK_D) {
            RightMoving = true; // Moving right
        }
        if(code == KeyEvent.VK_Z) {
            UpMoving = true; // Moving up
        }
        if(code == KeyEvent.VK_S) {
            DownMoving = true; // Moving down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // verify which key was released
        if(code == KeyEvent.VK_Q) {
            LeftMoving = false; // Stop moving left
        }
        if(code == KeyEvent.VK_D) {
            RightMoving = false; // Stop moving right
        }
        if(code == KeyEvent.VK_Z) {
            UpMoving = false; // Stop moving up
        }
        if(code == KeyEvent.VK_S) {
            DownMoving = false; // Stop moving down
        }
    }
    
}
