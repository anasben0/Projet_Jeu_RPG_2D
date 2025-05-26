package model.gameplay;
import java.util.ArrayList;
import model.item.Item;

    // Classe inventaire
public class Inventaire extends Equipements {

    public Inventaire() {
        super.items = new ArrayList<>();
    }

    public void ajouterItem(Item item) {
        super.items.add(item);
    }

    public void supprimerItem(Item item) {
        super.items.remove(item);
    }
}