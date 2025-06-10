package model.entite;

import Test.GamePanel;

public class Boss extends Monstre {
    private String nom;
    private int difficulté;

    public Boss(int vie, int force, GamePanel gp) {
        super(vie, force, "Boss", gp);
    }
    
}