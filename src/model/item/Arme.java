package model.item;

public class Arme extends Item {
    private int degats;
    private int niveau;

    public Arme(int degats, int niveau) {
        this.degats = degats;
        this.niveau = niveau;
    }

    public int getDegats() {
        return degats;
    }

    public int getNiveau() {
        return niveau;
    }

}