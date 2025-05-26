package model.item;

public class Potion extends Item {
    private int soins;

    public Potion(int soins) {
        this.soins = soins;
    }

    public int getSoins() {
        return soins;
    }
}