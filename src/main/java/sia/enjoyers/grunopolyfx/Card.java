package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    public void buyStreet(Player player, Label eventText, Pane pane) {
        if (player.money >= this.price) {
            this.owner = player;
            player.properties.add(this);
            player.hasColor.add(this.cardColor);
            player.money -= this.price;

            Color color = player.color;
            String hex = String.format("#%02x%02x%02x", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
            String style = """
                    -fx-background-radius: 15px;
                    -fx-background-color: %s;
                    -fx-text-fill: RED;

                    -fx-border-radius: 3px;
                    -fx-border-width: 2px;
                    -fx-border-color: black;
                    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 15, 0, 0, 0);
                """;

            pane.setPrefHeight(15.0);
            pane.setPrefWidth(15.0);
            pane.setStyle(String.format(style, hex));
            pane.setVisible(true);

            eventText.setText(player.name + " hat " + this.name + " gekauft!");
        }
    }

    public void payRent(Player player, Label eventText) {
        if (owner != null && player.id != owner.id) {
            eventText.setText(player.name + " muss " + this.rent + "€ Miete an " + this.owner.name + " zahlen!");
            player.money -= this.rent;
            owner.money += this.rent;
        }
    }

    public void updateRent(int houses) {
        for (int i = 0; i < houses; i++) {
            this.rent = (int) (this.rent * 1.25);
        }
    }


}

