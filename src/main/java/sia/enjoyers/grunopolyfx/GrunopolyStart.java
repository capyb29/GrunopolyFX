package sia.enjoyers.grunopolyfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GrunopolyStart {
    @FXML
    public Button startButton;
    public Button exitButton;
    @FXML
    private ChoiceBox<String> playerChoiceBox;

    public void initialize() {
        playerChoiceBox.getItems().addAll("2 Spieler", "3 Spieler", "4 Spieler");
    }

    public void onStartButtonClick(ActionEvent evt) throws IOException {
        Node source = (Node) evt.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("grunopoly-main.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Grunopoly");
        newStage.show();
    }

    public void onExitButtonClick(ActionEvent evt) {
        Node source = (Node) evt.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
    }

}
