package model.item;

public class Piece extends Item {
    private int valeur;
    private int x;
    private int y;

    public Piece(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}