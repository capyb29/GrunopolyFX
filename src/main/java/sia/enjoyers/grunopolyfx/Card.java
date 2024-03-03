package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Card {
    ArrayList<Player> playersOnCard;
    Pane pane;

    Card(Pane pane) {
        this.playersOnCard = new ArrayList<Player>();
        this.pane = pane;
    }
}
