package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Card {
    ArrayList<Player> playersOnCard;
    int price;
    int rent;
    String name;
    Player owner;

    Card(String name, int price) {
        this.playersOnCard = new ArrayList<Player>();
        this.name = name;
        this.price = price;
        this.rent = 0;
        this.owner = null;
    }
    public void buy(Player player) {

    }
}

