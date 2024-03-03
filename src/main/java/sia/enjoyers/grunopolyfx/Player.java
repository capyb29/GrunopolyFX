package sia.enjoyers.grunopolyfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
        this.setPrefSize(25, 25);

        // Player appearance
        String hex = String.format("#%02x%02x%02x", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));

        String textStyle = """
                    -fx-font: bold 17px 'Comic Sans MS';
                    -fx-stroke-width: 1;
                    -fx-line-spacing: -5;
                    -fx-alignment: TOP_CENTER;
                """;

        Text text = new Text("\n " + this.playerId);
        this.getChildren().add(text);

        text.setStyle(String.format(textStyle, hex));

        String style = """
                    -fx-background-radius: 15px;
                    -fx-background-color: %s;
                    -fx-text-fill: RED;

                    -fx-border-radius: 15px;
                    -fx-border-width: 3px;
                    -fx-border-color: black;
                    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 15, 0, 0, 0);
                """;

        this.setStyle(String.format(style, hex));

        this.setVisible(false);
    }

    public void setPosition(Pane referencePane, int pos) {
        // Set player object position and update metadata
        double x = referencePane.getLayoutX();
        double y = referencePane.getLayoutY();

        this.setLayoutX(x + (15 * (playerId % 2)));
        this.setLayoutY(y + 20 - (15 * (playerId % 3)));

        this.pos.set(pos);

        this.setVisible(true);
    }
}
