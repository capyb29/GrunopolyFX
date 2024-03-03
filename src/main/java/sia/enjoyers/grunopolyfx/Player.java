package sia.enjoyers.grunopolyfx;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Pane {
    String name;
    int money;

    AtomicInteger pos;
    int playerId;
    boolean jail;
    ArrayList<String> properties = new ArrayList<>();
    Player(String name, int playerId, Color color, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.playerId = playerId;
        this.pos = new AtomicInteger(0);

        this.setPrefSize(20, 20);

        BackgroundFill colorFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(colorFill);

        this.setBackground(background);
        this.setVisible(false);
    }

    public void setPosition(Pane referencePane, int pos) {
        double x = referencePane.getLayoutX();
        double y = referencePane.getLayoutY();

        this.setLayoutX(x);
        this.setLayoutY(y);

        this.pos.set(pos);

        this.setVisible(true);
    }
}
