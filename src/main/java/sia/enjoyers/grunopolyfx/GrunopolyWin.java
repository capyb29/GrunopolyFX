package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GrunopolyWin {
    public String winner;
    public Label winLabel;
    public Button endButton;

    public void initialize() {
        endButton.setOnAction(event -> System.exit(0));
    }

    public void setWinner(String winner) {
        this.winner = winner;
        winLabel.setText(winner + " hat gewonnen!");
    }
}
