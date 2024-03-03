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
    String name;
    int houses;


    Card(String name, int price, StreetColor color ) {
        this.playersOnCard = new ArrayList<Player>();
        this.name = name;
        this.price = price;
        this.houses = 0;
    }
}
