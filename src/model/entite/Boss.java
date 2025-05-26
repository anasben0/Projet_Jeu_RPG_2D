package model.entite;


public class Boss extends Monstre {
    private String nom;
    private int difficulté;
    
    public Boss(int vie, int force) {
        super(vie, force, "Boss");
    }
    
}