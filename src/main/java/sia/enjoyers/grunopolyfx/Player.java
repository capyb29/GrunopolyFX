package sia.enjoyers.grunopolyfx;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player extends Pane {
    String name;
    ArrayList<Integer> cards;
    Player(String name) {
        this.name = name;
        this.cards = new ArrayList<Integer>();

        this.setPrefSize(20, 20);

        BackgroundFill purpleFill = new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY);
        Background purpleBackground = new Background(purpleFill);

        this.setBackground(purpleBackground);

        this.setVisible(false);
    }

    public void setPosition(Pane referencePane) {
        double x = referencePane.getLayoutX();
        double y = referencePane.getLayoutY();

        this.setLayoutX(x);
        this.setLayoutY(y);

        this.setVisible(true);
    }
}
