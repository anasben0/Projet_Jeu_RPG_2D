package model.personnalisation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CatalogueApparence {

    private BufferedImage[] cheveux;
    private BufferedImage[] haut;
    private BufferedImage[] bas;

    // Constructeur qui charge les apparences depuis les fichiers
    public CatalogueApparence() {
        cheveux = ajouterCaseVide(chargerApparences("src/res/Assets/Cheveux/"));
        haut = ajouterCaseVide(chargerApparences("src/res/Assets/Hauts/"));
        bas = ajouterCaseVide(chargerApparences("src/res/Assets/Bas/"));
    }

    /**
     * Charge les images d'apparence depuis un dossier spécifique.
     * @param chemin Le chemin du dossier contenant les images.
     * @return Un tableau de BufferedImage contenant les images chargées.
     */
    private BufferedImage[] chargerApparences(String chemin) {
        File dossier = new File(chemin);
        // Filtre : seulement les fichiers .png contenant "down_1"
        File[] fichiers = dossier.listFiles((f, name) -> name.endsWith(".png") && name.contains("down_1"));

        if (fichiers == null) return new BufferedImage[0];

        BufferedImage[] sprites = new BufferedImage[fichiers.length];
        for (int i = 0; i < fichiers.length; i++) {
            try {
                sprites[i] = ImageIO.read(fichiers[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sprites;
    }

    /**
     * Charge une image spécifique depuis un chemin donné.
     * @param chemin Le chemin de l'image à charger.
     * @return L'image chargée sous forme de BufferedImage, ou null si l'image n'existe pas.
     */
    private BufferedImage chargerImage(String chemin) {
        File fichier = new File(chemin);
        if (!fichier.exists()) {
            System.err.println("Image manquante : " + chemin);
            return null;
        }

        try {
            return ImageIO.read(fichier);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute une case vide (null) à la fin du tableau d'apparences.
     */
    private BufferedImage[] ajouterCaseVide(BufferedImage[] originaux) {
        BufferedImage[] resultat = new BufferedImage[originaux.length + 1];
        System.arraycopy(originaux, 0, resultat, 0, originaux.length);
        resultat[originaux.length] = null; // case vide
        return resultat;
    }

    // Getters pour accéder aux tableaux d'apparences

    public BufferedImage[] getCheveux() {
        return cheveux;
    }

    public BufferedImage[] getHaut() {
        return haut;
    }

    public BufferedImage[] getBas() {
        return bas;
    }

    public BufferedImage getCorps(String direction, int frame) {
        String chemin = String.format("src/res/Assets/Corps/corps_%s_%d.png", direction, frame);
        return chargerImage(chemin);
    }

    public BufferedImage getCheveux(int index, String direction, int frame) {
        String chemin = String.format("src/res/Assets/Cheveux/cheveux%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

    public BufferedImage getHaut(int index, String direction, int frame) {
        String chemin = String.format("src/res/Assets/Hauts/haut%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

    public BufferedImage getBas(int index, String direction, int frame) {
        String chemin = String.format("src/res/Assets/Bas/bas%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

}
