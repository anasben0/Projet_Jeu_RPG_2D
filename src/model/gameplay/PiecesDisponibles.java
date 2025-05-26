package model.gameplay;
import java.util.ArrayList;
import model.item.Item;
// Classe pieces disponibles
    public class PiecesDisponibles extends Equipements {

        public PiecesDisponibles() {
            super.items = new ArrayList<>();
        }

        public void ajouterPiece(Item piece) {
            super.items.add(piece);
        }

        public void supprimerPiece(Item piece) {
            super.items.remove(piece);
        }
    }