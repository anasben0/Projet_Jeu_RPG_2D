package model.entite;

import Test.GamePanel;

public class Boss extends Monstre {
    private String nom;
    private int difficulté;

    public Boss(GamePanel gp) {
        super(gp);
        nom = "Boss";
        difficulté = 5;
    }

}