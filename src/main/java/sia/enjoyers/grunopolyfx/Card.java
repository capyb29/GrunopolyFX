package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Card {
    ArrayList<Player> playersOnCard;
    int price;
    String name;

    Card(String name, int price) {
        this.playersOnCard = new ArrayList<Player>();
        this.name = name;
        this.price = price;
    }
}
