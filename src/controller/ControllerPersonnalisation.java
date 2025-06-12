package controller;

import model.entite.Joueur;
import model.personnalisation.CatalogueApparence;
import view.ViewPersonnalisation;

import java.awt.event.MouseEvent;

import Test.GamePanel;

public class ControllerPersonnalisation {

    private ViewPersonnalisation view;
    private Joueur joueur;
    private CatalogueApparence catalogue;
    private GamePanel gp;

    public ControllerPersonnalisation(GamePanel gp, Joueur joueur, CatalogueApparence catalogue, ViewPersonnalisation view) {
        this.joueur = joueur;
        this.catalogue = catalogue;
        this.view = view;
        this.gp = gp;
    }

    /**
     * À appeler quand un clic est détecté dans GamePanel (ou autre moteur)
     */
    public void gererClic(int x, int y) {
        // Clic sur boutons de navigation
        if (y > 520 && y < 560) {
            if (x > 80 && x < 200) {
                view.partiePrecedente();
                return;
            } else if (x > 580 && x < 700) {
                view.partieSuivante();
                return;
            }
        }

        // Clic sur bouton "Retour Menu"
        if (x > 10 && x < 160 && y > 10 && y < 50) {
            System.out.println("Retour au menu principal");
            gp.gameState = gp.titleState;
            return;
        }


        // Clic sur bouton valider
        if (y > 560 && y < 600 && x > 340 && x < 440) {
            System.out.println("Personnalisation validée !");
            view.getGamePanel().gameState = view.getGamePanel().playState;
            return;
        }


        // Clic sur les options miniatures (à partir de x = 50, 70px entre chaque)
        int yMini = 350;
        if (y > yMini && y < yMini + 64) {
            int nbOptions = view.getNbOptions();
            for (int i = 0; i < nbOptions; i++) {
                int xMini = 50 + i * 70;
                if (x > xMini && x < xMini + 64) {
                    view.changerOption(i);
                    break;
                }
            }
        }
    }
}
