package view;

import Test.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import model.entite.Joueur;
import model.personnalisation.CatalogueApparence;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ControllerPersonnalisation;

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
    private JPanel panelOptions;
    private JButton[] boutonsOptions;

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

        BufferedImage cheveux = null;
        BufferedImage haut = null;
        BufferedImage bas = null;
        // Si l'index sélectionné correspond à la case vide (dernière case), ne rien afficher
        if (joueur.getIndexCheveux() < catalogue.getCheveux().length - 1)
            cheveux = catalogue.getCheveux(joueur.getIndexCheveux() + 1, "down", 1);
        if (joueur.getIndexHaut() < catalogue.getHaut().length - 1)
            haut = catalogue.getHaut(joueur.getIndexHaut() + 1, "down", 1);
        if (joueur.getIndexBas() < catalogue.getBas().length - 1)
            bas = catalogue.getBas(joueur.getIndexBas() + 1, "down", 1);
        BufferedImage corps = catalogue.getCorps("down", 1);

        g.drawImage(corps, xPerso, yPerso, persoWidth, persoHeight, null);
        if (bas != null) g.drawImage(bas, xPerso, yPerso, persoWidth, persoHeight, null);
        if (haut != null) g.drawImage(haut, xPerso, yPerso, persoWidth, persoHeight, null);
        if (cheveux != null) g.drawImage(cheveux, xPerso, yPerso, persoWidth, persoHeight, null);

        // Dessin des options
        int yOptions = 350;
        BufferedImage[] options;
        if (partieSelectionnee == 0) {
            options = catalogue.getCheveux();
        } else if (partieSelectionnee == 1) {
            options = catalogue.getHaut();
        } else {
            options = catalogue.getBas();
        }

        // Paramètres pour affichage centré et agrandi
        int caseWidth = 80;
        int caseHeight = 80;
        int nbOptions = options.length;
        int totalWidth = nbOptions * caseWidth + (nbOptions - 1) * 20; // 20px d'espacement
        int startX = (800 - totalWidth) / 2;

        g.drawString("Choix disponibles :", startX, yOptions - 20);

        for (int i = 0; i < nbOptions; i++) {
            int x = startX + i * (caseWidth + 20);
            // Si l'option est null (case vide), dessiner un rectangle vide avec une croix ou un texte
            if (options[i] == null) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, yOptions, caseWidth, caseHeight);
                g.setColor(Color.RED);
                g.drawLine(x, yOptions, x + caseWidth, yOptions + caseHeight);
                g.drawLine(x + caseWidth, yOptions, x, yOptions + caseHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, yOptions, caseWidth, caseHeight);
            } else {
                g.drawImage(options[i], x, yOptions, caseWidth, caseHeight, null);
                g.setColor(Color.BLACK);
                g.drawRect(x, yOptions, caseWidth, caseHeight);
            }
        }

        // Boutons navigation
        g.setColor(Color.WHITE);
        g.drawString("< Précédent", 100, 540);
        g.drawString("Suivant >", 600, 540);
        g.drawString("Valider", 360, 520);

        // Bouton retour menu
        g.setColor(Color.GRAY);
        g.fillRoundRect(10, 10, 150, 40, 10, 10);
        g.setColor(Color.WHITE);
        g.drawRoundRect(10, 10, 150, 40, 10, 10);
        g.drawString("RETOUR MENU", 20, 37);
    }

    public void initialiserPanelOptions(ControllerPersonnalisation controller) {
        if (panelOptions != null) {
            gp.remove(panelOptions);
            panelOptions.removeAll();
        }
        panelOptions = new JPanel();
        panelOptions.setOpaque(false);
        panelOptions.setLayout(null);
        panelOptions.setBounds(0, 350, 800, 100);

        BufferedImage[] options;
        if (partieSelectionnee == 0) {
            options = catalogue.getCheveux();
        } else if (partieSelectionnee == 1) {
            options = catalogue.getHaut();
        } else {
            options = catalogue.getBas();
        }
        int caseWidth = 80;
        int caseHeight = 80;
        int nbOptions = options.length;
        int totalWidth = nbOptions * caseWidth + (nbOptions - 1) * 20;
        int startX = (800 - totalWidth) / 2;

        boutonsOptions = new JButton[nbOptions];
        for (int i = 0; i < nbOptions; i++) {
            JButton btn = new JButton();
            btn.setBounds(startX + i * (caseWidth + 20), 0, caseWidth, caseHeight);
            if (options[i] != null) {
                Image img = options[i].getScaledInstance(caseWidth, caseHeight, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(true);
            } else {
                btn.setText("X");
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(true);
            }
            final int idx = i;
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.gererClicOption(idx);
                }
            });
            boutonsOptions[i] = btn;
            panelOptions.add(btn);
        }
        gp.add(panelOptions);
        gp.setComponentZOrder(panelOptions, 0); // S'assurer que le panel est au-dessus
        gp.revalidate();
        gp.repaint();
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
        // index du tableau (0 à 3), mais on veut stocker 0 à 3 dans le joueur
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
