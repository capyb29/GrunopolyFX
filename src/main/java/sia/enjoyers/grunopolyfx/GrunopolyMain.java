package sia.enjoyers.grunopolyfx;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.*;
import java.util.stream.Collectors;

public class GrunopolyMain {

    @FXML
    private Pane board;
    @FXML
    private Pane boardimg;

    // Get anchor points
    @FXML
    private Pane x0;
    @FXML
    private Pane x1;
    @FXML
    private Pane x2;
    @FXML
    private Pane x3;
    @FXML
    private Pane x4;
    @FXML
    private Pane x5;
    @FXML
    private Pane x6;
    @FXML
    private Pane x7;
    @FXML
    private Pane x8;
    @FXML
    private Pane x9;
    @FXML
    private Pane x10;
    @FXML
    private Pane x11;
    @FXML
    private Pane x12;
    @FXML
    private Pane x13;
    @FXML
    private Pane x14;
    @FXML
    private Pane x15;
    @FXML
    private Pane x16;
    @FXML
    private Pane x17;
    @FXML
    private Pane x18;
    @FXML
    private Pane x19;
    @FXML
    private Pane x20;
    @FXML
    private Pane x21;
    @FXML
    private Pane x22;
    @FXML
    private Pane x23;
    @FXML
    private Pane x24;
    @FXML
    private Pane x25;
    @FXML
    private Pane x26;
    @FXML
    private Pane x27;
    @FXML
    private Pane x28;
    @FXML
    private Pane x29;
    @FXML
    private Pane x30;
    @FXML
    private Pane x31;
    @FXML
    private Pane x32;
    @FXML
    private Pane x33;
    @FXML
    private Pane x34;
    @FXML
    private Pane x35;
    @FXML
    private Pane x36;
    @FXML
    private Pane x37;
    @FXML
    private Pane x38;
    @FXML
    private Pane x39;

    // Get interactive elements
    @FXML
    private Button stepButton;

    // Declare data
    AtomicInteger pos = new AtomicInteger();
    List<Pane> allPanes;
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Player> players = new ArrayList<Player>();
    int activePlayer = 0;
    int playerCount = 0;
    int buttonCountClicked = 0;

    @FXML
    public void initialize() {
        // get background image
        String imagePath = "src\\main\\resources\\sia\\enjoyers\\grunopolyfx\\Board.png";
        try {
            Image image = new Image("file:" + imagePath);

            // Create an ImageView for flexible sizing
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(boardimg.widthProperty());
            imageView.fitHeightProperty().bind(boardimg.heightProperty());

            // Set the ImagePattern as the pane's background
            ImagePattern imagePattern = new ImagePattern(image);
            boardimg.setBackground(new Background(new BackgroundFill(imagePattern, CornerRadii.EMPTY, Insets.EMPTY)));
        } catch (Exception e) {
            System.exit(1);
        }


        // Initialization code here, if needed
        // Example: Collecting panes in a list for easier manipulation
        allPanes = Arrays.asList(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31, x32, x33, x34, x35, x36, x37, x38, x39);
        // Now you can loop through allPanes to manipulate them

        allPanes.forEach((pane) -> {
            cards.add(new Card(pane));
            pane.setVisible(false);
        });



        stepButton.setOnAction(event -> {
            if (buttonCountClicked == 0) {
                // Create temporary players
                for (int i = 0; i < this.playerCount; i++) {
                    Color color = Color.color(Math.random(), Math.random(), Math.random());
                    Player player = new Player("GRU", color, 1000);

                    player.setPosition(allPanes.getFirst());
                    board.getChildren().add(player);
                    players.add(player);
                }

                activePlayer = (int) (Math.random() * players.size());
            }

            this.buttonCountClicked++;
            int randNum = (int) Math.max(2, 1 + (Math.random() * 12));
            step(randNum, players.get(activePlayer));

            if (activePlayer >= players.size() - 1) {
                activePlayer = 0;
            } else {
                activePlayer++;
            }
        });
    }
    public void step(int stepCount, Player player) {
        if (pos.get() + stepCount <= 39) {
            pos.set(pos.get() + stepCount);
        } else {
            pos.set((pos.get() + stepCount) % 40);
        }

        this.cards = (ArrayList<Card>) this.cards.stream()
                .peek(card -> card.playersOnCard.remove(player))
                .collect(Collectors.toList());

        Pane newDesiredPane = allPanes.get(pos.get());
        Card newDesiredCard = cards.stream()
                .filter(card -> card.pane.equals(newDesiredPane))
                .findFirst()
                .orElse(null);

        assert newDesiredCard != null;
        newDesiredCard.playersOnCard.add(player);

        double xNew = newDesiredPane.getLayoutX();
        double yNew = newDesiredPane.getLayoutY();

        player.setLayoutX(xNew);
        player.setLayoutY(yNew);
    }

    public void setPlayerCount (int playerCount) {
        this.playerCount = playerCount;
    }
}
