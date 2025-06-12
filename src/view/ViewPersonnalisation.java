package view;

import model.entite.Joueur;
import model.personnalisation.CatalogueApparence;

import java.awt.*;
import java.awt.image.BufferedImage;

import Test.GamePanel;

/**
 * Affiche l'écran de personnalisation du personnage.
 * Permet de choisir les cheveux, le haut et le bas du personnage.
 */
public class ViewPersonnalisation {

    private Joueur joueur;
    private CatalogueApparence catalogue;

    private int partieSelectionnee = 0; // 0 = cheveux, 1 = haut, 2 = bas
    private String[] parties = {"Cheveux", "Haut", "Bas"};

    private GamePanel gp;

    public ViewPersonnalisation(GamePanel gp, Joueur joueur, CatalogueApparence catalogue) {
        this.gp = gp;
        this.joueur = joueur;
        this.catalogue = catalogue;
    }

    /**
     * Dessine l'écran de personnalisation.
     * @param g Le Graphics2D utilisé pour dessiner.
     */
    public void draw(Graphics2D g) {
        // Fond
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 600);

        // Titre
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Personnalisation du personnage", 200, 40);

        // Partie en cours
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Partie : " + parties[partieSelectionnee], 330, 80);

        // Taille d'affichage du personnage
        int persoWidth = 128;
        int persoHeight = 128;

        // Coordonnées pour centrer
        int xPerso = (800 - persoWidth) / 2;
        int yPerso = (600 - persoHeight) / 2 - 40; // -40 pour laisser la place au titre

        BufferedImage cheveux = catalogue.getCheveux(joueur.getIndexCheveux(), "down", 1);
        BufferedImage haut = catalogue.getHaut(joueur.getIndexHaut(), "down", 1);
        BufferedImage bas = catalogue.getBas(joueur.getIndexBas(), "down", 1);
        BufferedImage corps = catalogue.getCorps("down", 1);

        g.drawImage(corps, xPerso, yPerso, persoWidth, persoHeight, null);
        if (bas != null) g.drawImage(bas, xPerso, yPerso, persoWidth, persoHeight, null);
        if (haut != null) g.drawImage(haut, xPerso, yPerso, persoWidth, persoHeight, null);
        if (cheveux != null) g.drawImage(cheveux, xPerso, yPerso, persoWidth, persoHeight, null);

        // Dessin des options
        int yOptions = 350;
        g.drawString("Choix disponibles :", 50, yOptions - 20);

        BufferedImage[] options = (partieSelectionnee == 0) ?
                catalogue.getCheveux() : catalogue.getHaut();

        for (int i = 0; i < options.length; i++) {
            g.drawImage(options[i], 50 + i * 70, yOptions, null);
            g.drawRect(50 + i * 70, yOptions, 64, 64);
        }

        // Boutons navigation
        g.setColor(Color.WHITE);
        g.drawString("< Précédent", 100, 540);
        g.drawString("Suivant >", 600, 540);
        g.drawString("Valider", 360, 580);

        // Bouton retour menu
        g.setColor(Color.GRAY);
        g.fillRoundRect(10, 10, 150, 40, 10, 10);
        g.setColor(Color.WHITE);
        g.drawRoundRect(10, 10, 150, 40, 10, 10);
        g.drawString("RETOUR MENU", 20, 37);
    }

    

    /**
     * Change la partie sélectionnée (cheveux, haut, bas).
     * Incrémente l'index de la partie sélectionnée.
     */
    public void partieSuivante() {
        partieSelectionnee = (partieSelectionnee + 1) % parties.length;
    }

    /**
     * Change la partie sélectionnée (cheveux, haut, bas).
     * Décrémente l'index de la partie sélectionnée.
     */
    public void partiePrecedente() {
        partieSelectionnee = (partieSelectionnee - 1 + parties.length) % parties.length;
    }

    // À appeler depuis un gestionnaire d'événement si clic sur miniature
    public void changerOption(int index) {
        if (partieSelectionnee == 0) joueur.setIndexCheveux(index);
        else if (partieSelectionnee == 1) joueur.setIndexHaut(index);
        else if (partieSelectionnee == 2) joueur.setIndexBas(index);

    }

    public int getNbOptions() {
        return switch (partieSelectionnee) {
            case 0 -> catalogue.getCheveux().length;
            case 1 -> catalogue.getHaut().length;
            case 2 -> catalogue.getBas().length;
            default -> 0;
        };
    }

    public int getPartieSelectionnee() {
        return partieSelectionnee;
    }

    public String getNomPartie() {
        return parties[partieSelectionnee];
    }

    public GamePanel getGamePanel() {
        return gp;
    }

}
