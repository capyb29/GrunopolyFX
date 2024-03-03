package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Card {
    ArrayList<Player> playersOnCard;
    Pane pane;
    int price;

    Card(Pane pane) {
        this.playersOnCard = new ArrayList<Player>();
        this.pane = pane;
        this.price = 100;
    }
}
