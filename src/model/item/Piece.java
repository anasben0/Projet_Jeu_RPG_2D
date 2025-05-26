package model.item;

public class Piece extends Item {
    private int valeur = 1;
    private int x;
    private int y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getValeur() {
        return valeur;
    }
}