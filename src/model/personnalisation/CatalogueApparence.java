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
        cheveux = chargerApparences("res/Assets/Cheveux/");
        haut = chargerApparences("res/Assets/Haut/");
        bas = chargerApparences("res/Assets/Bas/");
    }

    /**
     * Charge les images d'apparence depuis un dossier spécifique.
     * @param chemin Le chemin du dossier contenant les images.
     * @return Un tableau de BufferedImage contenant les images chargées.
     */
    private BufferedImage[] chargerApparences(String chemin) {
        File dossier = new File(chemin);
        File[] fichiers = dossier.listFiles((f, name) -> name.endsWith(".png"));

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
        System.out.println("Chemin absolu testé : " + new File(chemin).getAbsolutePath());
        return chargerImage(chemin);
    }


    public BufferedImage getCheveux(int index, String direction, int frame) {
        String chemin = String.format("res/Assets/Cheveux/cheveux%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

    public BufferedImage getHaut(int index, String direction, int frame) {
        String chemin = String.format("res/Assets/Haut/haut%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

    public BufferedImage getBas(int index, String direction, int frame) {
        String chemin = String.format("res/Assets/Bas/bas%d_%s_%d.png", index, direction, frame);
        return chargerImage(chemin);
    }

}
