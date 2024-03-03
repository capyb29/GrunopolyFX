package sia.enjoyers.grunopolyfx;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Player extends Pane {
    String name;
    int money;
    Player(String name, Color color, int initialMoney) {
        this.name = name;
        this.money = initialMoney;

        this.setPrefSize(20, 20);

        BackgroundFill colorFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(colorFill);

        this.setBackground(background);
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
