package controller;

import Test.GamePanel;
import model.entite.Joueur;
import model.personnalisation.CatalogueApparence;
import view.ViewPersonnalisation;

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
        // Initialisation du panel d'options dès l'ouverture de la personnalisation
        this.view.initialiserPanelOptions(this);
        this.gp.revalidate();
        this.gp.repaint();
    }

    /**
     * À appeler quand un clic est détecté dans GamePanel (ou autre moteur)
     * (Zone graphique seulement, ne gère plus les options miniatures qui sont maintenant des boutons)
     */
    public void gererClic(int x, int y) {
        // Clic sur boutons de navigation
        if (y > 520 && y < 560) {
            if (x > 80 && x < 200) {
                view.partiePrecedente();
                view.initialiserPanelOptions(this);
                gp.repaint();
                return;
            } else if (x > 580 && x < 700) {
                view.partieSuivante();
                view.initialiserPanelOptions(this);
                gp.repaint();
                return;
            }
        }

        // Clic sur bouton "Retour Menu"
        if (x > 10 && x < 160 && y > 10 && y < 50) {
            System.out.println("Retour au menu principal");
            gp.gameState = gp.titleState;
            return;
        }

        // Clic sur bouton valider (remonté plus haut)
        if (y > 500 && y < 540 && x > 340 && x < 440) {
            System.out.println("Personnalisation validée !");
            view.getGamePanel().gameState = view.getGamePanel().playState;
            gp.stopMusic();
            gp.playMusic(0);
            return;
        }

        // Clic sur les options miniatures (zone des cases de choix)
        int yMini = 350;
        int caseWidth = 80;
        int caseHeight = 80;
        int nbOptions = view.getNbOptions();
        int totalWidth = nbOptions * caseWidth + (nbOptions - 1) * 20;
        int startX = (800 - totalWidth) / 2;
        if (y > yMini && y < yMini + caseHeight) {
            for (int i = 0; i < nbOptions; i++) {
                int xMini = startX + i * (caseWidth + 20);
                if (x > xMini && x < xMini + caseWidth) {
                    view.changerOption(i);
                    break;
                }
            }
        }
    }

    /**
     * À appeler quand un bouton d'option est cliqué
     */
    public void gererClicOption(int index) {
        view.changerOption(index);
        // Met à jour l'affichage si besoin
        view.initialiserPanelOptions(this);
        gp.repaint();
    }
}
