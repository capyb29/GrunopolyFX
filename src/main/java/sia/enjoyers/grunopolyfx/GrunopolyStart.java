package sia.enjoyers.grunopolyfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class GrunopolyStart {
    @FXML
    public Button startButton;
    public Button exitButton;
    @FXML
    private ChoiceBox<String> playerChoiceBox;
    public TextField playerName1;
    public TextField playerName2;
    public TextField playerName3;
    public TextField playerName4;

    public void initialize() {
        playerChoiceBox.getItems().addAll("2 Spieler", "3 Spieler", "4 Spieler");
        playerChoiceBox.setValue("2 Spieler");
        playerName3.setDisable(true);
        playerName4.setDisable(true);
        playerChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("2 Spieler")) {
                playerName3.setDisable(true);
                playerName4.setDisable(true);
            } else if (newValue.equals("3 Spieler")) {
                playerName3.setDisable(false);
                playerName4.setDisable(true);
            } else {
                playerName3.setDisable(false);
                playerName4.setDisable(false);
            }
        });
    }

    public void onStartButtonClick(ActionEvent evt) throws IOException {
        Node source = (Node) evt.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("grunopoly-main.fxml"));
        Parent root = loader.load();

        GrunopolyMain controller = loader.getController();
        controller.initPlayers(playerChoiceBox.getSelectionModel().getSelectedIndex() + 2);


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
