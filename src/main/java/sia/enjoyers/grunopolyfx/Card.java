package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.awt.*;
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
    StreetColor cardColor;

    Card(String name, int price, StreetColor color, int rent) {
        this.playersOnCard = new ArrayList<Player>();
        this.name = name;
        this.price = price;
        this.owner = null;
        this.houses = 0;
        this.cardColor = color;
        this.rent = rent;
    }
    public void buyStreet(Player player) {
        if (player.money >= this.price) {
            this.owner = player;
            player.properties.add(this);
            player.hasColor.add(this.cardColor);
            player.money -= this.price;
        }
    }

    public void payRent(Player player, Label eventText) {
        if (owner != null && player.id != owner.id) {
            eventText.setText(player.name + " muss " + this.rent + "€ Miete an " + this.owner.name + " zahlen!");
            player.money -= this.rent;
            owner.money += this.rent;
        }
    }
}

