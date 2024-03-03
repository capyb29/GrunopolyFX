package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Card {

    public enum StreetColor {
        BROWN,
        LIGHTBLUE,
        PINK,
        ORANGE,
        RED,
        YELLOW,
        GREEN,
        BLUE,
        None
    }
    ArrayList<Player> playersOnCard;
    int price;
    int rent;
    String name;
    Player owner;
    int houses;

    Card(String name, int price, StreetColor color) {
        this.playersOnCard = new ArrayList<Player>();
        this.name = name;
        this.price = price;
        this.rent = 0;
        this.owner = null;
        this.houses = 0;
    }
    public void buyStreet(Player player) {
        if (player.money >= this.price) {
            this.owner = player;
            player.properties.add(this.name);
            player.money -= this.price;
        }
    }
}

