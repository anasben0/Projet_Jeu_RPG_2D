package model.item;

public class Armure extends Item {
    private int defense;
    private int niveau;

    public Armure(int defense, int niveau) {
        this.defense = defense;
        this.niveau = niveau;
    }

    public int getDefense() {
        return defense;
    }

    public int getNiveau() {
        return niveau;
    }
}