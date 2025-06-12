package controller;

import Test.GamePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandlerPersonnalisation extends MouseAdapter {

    private GamePanel gp;

    public MouseHandlerPersonnalisation(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gp.gameState == gp.personnalisationState && gp.ui.viewPersonnalisation != null) {
            ControllerPersonnalisation controller = new ControllerPersonnalisation(
                gp,
                gp.joueur,
                gp.joueur.getCatalogue(),
                gp.ui.viewPersonnalisation
            );

            controller.gererClic(e.getX(), e.getY());
        }
    }
}
