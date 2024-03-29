package sia.enjoyers.grunopolyfx;


import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GrunopolyMain {

    // Get UI components
    @FXML
    public Label player1;
    public Label player2;
    public Label player3;
    public Label player4;
    public Label player1_money;
    public Label player2_money;
    public Label player3_money;
    public Label player4_money;
    public Label player1_props;
    public Label player2_props;
    public Label player3_props;
    public Label player4_props;
    public Label youAreAt;
    public Label streetOwner;
    public Label streetCost;
    public Button buyButton;
    public ChoiceBox<String> streetChoiceHouses;
    public Label houseBuyLabel;
    public Button buyHouses;
    public Label youGotA;
    public Label header;
    public Button streetSellButton;
    public ChoiceBox<String> streetSelector;
    public Button tradeButton;
    public Button sellHouse;


    //Get Board and Background
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
    @FXML
    private Pane x40;

    // Get interactive elements
    @FXML
    public Button stepButton;
    @FXML
    public Label eventText;


    // Declare data

    int winScreenCounter = 0;
    List<Pane> allPanes;
    ArrayList<Player> players = new ArrayList<>();
    public HashMap<Pane, Card> cards;
    int activePlayer = 0;
    int rounds = 0;
    int diceNumber = 0;
    int playerCount;
    Player currentPlayer;

    @FXML
    public void initialize() {
        // apply scaling transforms
        double startWidth = 1700;
        double startHeight = 1150;

        double ratio = (startWidth / startHeight);

        Scale scale = new Scale(ratio, ratio);
        scale.setPivotX(0);
        scale.setPivotY(0);
        board.getTransforms().setAll(scale);
        board.setPrefWidth(startWidth);
        board.setPrefHeight(startHeight);

        // Window size change listener
        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(ratio, startHeight, startWidth, board);
        board.widthProperty().addListener(sizeListener);
        board.heightProperty().addListener(sizeListener);

        // Auf 1080p anpassen
        board.setPrefWidth(startWidth / (startHeight / 1080));
        board.setPrefHeight(startHeight / (startHeight / 1080));

        // get background image
        setBackgroundImage();


        allPanes = Arrays.asList(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31, x32, x33, x34, x35, x36, x37, x38, x39, x40);

        allPanes.forEach((pane) -> pane.setVisible(false));

        // Set properties
        cards = new HashMap<>();
        cards.put(x0, new Card("Los", -1, Card.StreetColor.None, 0, 0));
        cards.put(x1, new Card("Athenaeum", 20, Card.StreetColor.BROWN, 5, 1));
        cards.put(x2, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 2));
        cards.put(x3, new Card("VLG", 10, Card.StreetColor.BROWN, 2, 3));
        cards.put(x4, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 4));
        cards.put(x5, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 5));
        cards.put(x6, new Card("Rossmann", 100, Card.StreetColor.LIGHTBLUE, 10, 6));
        cards.put(x7, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 7));
        cards.put(x8, new Card("H&M", 100, Card.StreetColor.LIGHTBLUE, 15, 8));
        cards.put(x9, new Card("Thalia", 110, Card.StreetColor.LIGHTBLUE, 20, 9));
        cards.put(x10, new Card("Gefängnis (Besucher)", -1, Card.StreetColor.None, 0, 10));
        cards.put(x11, new Card("Parkhotel Stade", 120, Card.StreetColor.PINK, 40, 11));
        cards.put(x12, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 12));
        cards.put(x13, new Card("Stadissimo", 120, Card.StreetColor.PINK, 60, 13));
        cards.put(x14, new Card("Stadeum", 130, Card.StreetColor.PINK, 70, 14));
        cards.put(x15, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 15));
        cards.put(x16, new Card("Al Porto", 150, Card.StreetColor.ORANGE, 80, 16));
        cards.put(x17, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 17));
        cards.put(x18, new Card("Mister Vu", 150, Card.StreetColor.ORANGE, 80, 18));
        cards.put(x19, new Card("Tacos", 140, Card.StreetColor.ORANGE, 80, 19));
        cards.put(x20, new Card("Nichts", -1, Card.StreetColor.None, 0, 20));
        cards.put(x21, new Card("Commerzbank", 200, Card.StreetColor.RED, 100, 21));
        cards.put(x22, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 22));
        cards.put(x23, new Card("Sparkasse", 200, Card.StreetColor.RED, 100, 23));
        cards.put(x24, new Card("Postbank", 200, Card.StreetColor.RED, 100, 24));
        cards.put(x25, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 25));
        cards.put(x26, new Card("Lidl", 210, Card.StreetColor.YELLOW, 110, 26));
        cards.put(x27, new Card("Netto", 210, Card.StreetColor.YELLOW, 110, 27));
        cards.put(x28, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 28));
        cards.put(x29, new Card("REWE", 220, Card.StreetColor.YELLOW, 120, 29));
        cards.put(x30, new Card("Gehe ins Gefängnis", -1, Card.StreetColor.None, 0, 30));
        cards.put(x31, new Card("Orient Express", 240, Card.StreetColor.GREEN, 220, 31));
        cards.put(x32, new Card("Rena's Grill", 240, Card.StreetColor.GREEN, 220, 32));
        cards.put(x33, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 33));
        cards.put(x34, new Card("Köz", 250, Card.StreetColor.GREEN, 250, 34));
        cards.put(x35, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 35));
        cards.put(x36, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 36));
        cards.put(x37, new Card("Pferdemarkt", 300, Card.StreetColor.BLUE, 350, 37));
        cards.put(x38, new Card("Unbesetzt", -1, Card.StreetColor.None, 0, 38));
        cards.put(x39, new Card("Jobelmann-Schule", 500, Card.StreetColor.BLUE, 400, 39));
        cards.put(x40, new Card("Gefängnis", -1, Card.StreetColor.None, 0, 40));

        buyButton.setOnAction(e -> {
            Pane cPane = allPanes.get(this.currentPlayer.pos.intValue());
            Card cCard = cards.get(cPane);
            cCard.buyStreet(this.currentPlayer, eventText, cPane, false);
            // Update UI
            updateUi(cPane, diceNumber);
        });

        stepButton.setOnAction(event -> {
            this.currentPlayer = players.get(activePlayer);
            // Keep counting until live player found
            activePlayer = (activePlayer + 1) % playerCount;
            while (!players.get(activePlayer).alive) {
                activePlayer = (activePlayer + 1) % playerCount;
            }

            int dice1 = (int) (1 + (Math.random() * 6));
            int dice2 = (int) (1 + (Math.random() * 6));
            this.diceNumber = dice1 + dice2;
            step(this.diceNumber, this.currentPlayer);
        });

        buyHouses.setOnAction(event -> {
            if (streetChoiceHouses.getValue() == null) {
                eventText.setText("Bitte wählen Sie eine Straße aus!");
                return;
            }
            String streetName = streetChoiceHouses.getValue();

            this.currentPlayer.buildHouse(streetName, eventText, allPanes);
            updateUi(allPanes.get(this.currentPlayer.pos.intValue()), 0);
            streetChoiceHouses.setValue(streetName);

        });

        sellHouse.setOnAction(event -> {
            if (streetChoiceHouses.getValue() == null) {
                eventText.setText("Bitte wählen Sie eine Straße aus!");
                return;
            }

            String streetName = streetChoiceHouses.getValue();
            this.currentPlayer.sellHouse(streetName, eventText, allPanes);
            updateUi(allPanes.get(this.currentPlayer.pos.get()), 0);
            streetChoiceHouses.setValue(streetName);
        });

        streetSellButton.setOnAction(event -> {
            Pane pane = allPanes.get(this.currentPlayer.pos.get());

            this.currentPlayer.sellStreet(eventText, streetSelector, allPanes);
            updateUi(pane, 0);
            streetSelector.setValue("");
        });

        tradeButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("grunopoly-trade.fxml"));
                Parent root = loader.load();

                GrunopolyTrade controller = loader.getController();
                controller.setTradePartnerBox(this.currentPlayer, players.toArray(new Player[0]));
                controller.setTradePlayers(players);
                controller.portHolyHashmap(cards);
                controller.getEventText(eventText);
                controller.setTradingCompleteListener(() -> updateUi(allPanes.get(this.currentPlayer.pos.get()), 0));

                Stage stage = new Stage();
                stage.initOwner(board.getScene().getWindow());
                stage.setScene(new Scene(root));
                stage.setTitle("Grunopoly Trade");
                stage.show();

                this.board.getScene().getWindow().setOnCloseRequest(closeEvent -> stage.close());
            } catch (IOException e) {
                System.out.println("Problem while opening trade window: " + e);
            }
        });
    }

    public void step(int stepCount, Player player) {
        if (!player.alive) {
            return;
        }

        if (player.jailRounds > 0) {
            updateUi(x40, 0);
            return;
        }

        int initial = player.pos.get();
        int newPos = initial + stepCount <= 39 ? initial + stepCount : (initial + stepCount) % 40;

        eventText.setText("...");

        this.cards.forEach((pane, cards) -> cards.playersOnCard.removeIf(p -> p.id == player.id));

        Pane newDesiredPane = allPanes.get(newPos);
        Card newDesiredCard = this.cards.get(newDesiredPane);

        // Los
        if (initial + stepCount > 40) {
            player.money += 200;
            eventText.setText(player.name + " über los!\n+200€");
        } else if (newPos == 0) {
            player.money += 300;
            eventText.setText(player.name + " auf los!\n+300€");
        }

        this.cards.forEach((pane, cards) -> cards.playersOnCard.removeIf(p -> p.id == player.id));

        //  Gehe ins Gefängnis
        if (newDesiredPane == x30) {
            player.setPosition(x30, newPos);
            updateUi(x30, stepCount);

            eventText.setText(player.name + " ist ins Gefängnis geraten!");

            // Event listener for jail movement
            player.currentAnimation.setOnFinished(event -> {
                player.setPosition(x40, 10); // Actual jail position
                player.jailRounds = 3;
                updateUi(x40, 10);
                player.pos = new AtomicInteger(10);
            });
        } else if (newDesiredPane == x20) {
            // Steuern
            int moolah = (int) (player.money * 0.1);
            player.money -= Math.abs(moolah);

            eventText.setText(player.name + " hat die 10%-Steuer bezahlt!\n-" + moolah + "€");
        } else if (newDesiredPane == x15 || newDesiredPane == x35) {
            player.setPosition(newDesiredPane, newPos);
            updateUi(newDesiredPane, stepCount);

            // Event ausführen
            new ChanceEvent(players, player, eventText, allPanes);

            // Event-Karte endet den Spielzug.
            while (!players.get(activePlayer).alive) {
                activePlayer = (activePlayer + 1) % playerCount;
            }

            return;

        }

        assert newDesiredCard != null;
        player.setPosition(newDesiredPane, newPos);
        newDesiredCard.payRent(player, eventText);
        newDesiredCard.playersOnCard.add(player);

        updateUi(newDesiredPane, stepCount);
        player.currentAnimation.setOnFinished(event -> {
            try {
                countAlivePlayers();
            } catch (IOException | InterruptedException e) {
                System.out.println(e + " Niemand kann gewinnen");
            }
        });
    }

    public void initPlayers(int playerCount, String[] playerNames) {
        for (int i = 0; i < playerCount; i++) {
            Color hsb = Color.hsb((((double) 360 / playerCount) * (i + 1)), .5, .7);

            Player player = new Player(playerNames[i], i + 1, hsb, 1000);
            devBuild(player);
            player.setPosition(allPanes.getFirst(), 0);

            board.getChildren().add(player);
            players.add(player);
        }

        this.playerCount = playerCount;

        dieterCheck(players);
        hidePlayerStats(playerCount);
        activePlayer = (int) (Math.random() * players.size());
        this.currentPlayer = players.get(activePlayer);
        updateUi(x0, 0);
        header.setText(this.currentPlayer.name + " fängt an!");
    }

    private record SceneSizeChangeListener(double ratio, double initHeight, double initWidth,
                                           Pane contentPane) implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            final double newWidth = contentPane.getWidth();
            final double newHeight = contentPane.getHeight();

            double scaleFactor =
                    newWidth / newHeight > ratio
                            ? newHeight / initHeight
                            : newWidth / initWidth;

            Scale scale = new Scale(scaleFactor, scaleFactor);
            scale.setPivotX(0);
            scale.setPivotY(0);

            contentPane.getTransforms().setAll(scale);

            contentPane.setPrefWidth(newWidth / scaleFactor);
            contentPane.setPrefHeight(newHeight / scaleFactor);
        }
    }

    public void updateUi(Pane currentPane, int rolled) {
        // Update player labels
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            // Check if player alive (naturally would be done first, but I am working with real gourmet chefs here)
            // Says the guy who messed up the dice roll mechanism
            player.isAliveCheck(allPanes);

            Label playerLabel = switch (i) {
                case 0 -> player1;
                case 1 -> player2;
                case 2 -> player3;
                case 3 -> player4;
                default -> null;
            };
            Label moneyLabel = switch (i) {
                case 0 -> player1_money;
                case 1 -> player2_money;
                case 2 -> player3_money;
                case 3 -> player4_money;
                default -> null;
            };
            Label propsLabel = switch (i) {
                case 0 -> player1_props;
                case 1 -> player2_props;
                case 2 -> player3_props;
                case 3 -> player4_props;
                default -> null;
            };
            if (playerLabel != null) {
                playerLabel.setText(player.name);
                if (player.alive) {
                    playerLabel.setTextFill(player.color);
                } else {
                    playerLabel.setTextFill(Color.GRAY);
                }
            }
            if (moneyLabel != null) {
                moneyLabel.setText(player.money + "€");
            }

            if (propsLabel != null) {
                final String[] props = {""};
                player.properties.forEach((card) -> props[0] += card.name + "[" + card.houses + "]" + ", ");
                propsLabel.setText(props[0]);
            }

            Card card = cards.get(currentPane);

            header.setText(this.currentPlayer.name + " am Zug!");
            youAreAt.setText("Sie befinden sich auf: " + card.name);

            buyButton.setDisable(true);
            streetOwner.setVisible(false);
            streetCost.setVisible(false);
            stepButton.setText("Würfeln");

            // Gefängnis
            if (this.currentPlayer.jailRounds > 0) {
                streetOwner.setVisible(false);
                streetCost.setVisible(false);
                buyButton.setDisable(true);
                stepButton.setText("Skip");
                youGotA.setText(this.currentPlayer.jailRounds > 1 ? "Warte " + this.currentPlayer.jailRounds + " Runden." : "Warte 1 Runde.");
                this.currentPlayer.jailRounds--;
                return;
            }

            if (card.price != -1) {
                buyButton.setDisable(false);
                streetOwner.setVisible(true);
                streetCost.setVisible(true);

                if (card.owner == null) {
                    streetCost.setText("Preis: " + card.price + "€");
                    streetOwner.setText("Kein Besitzer");
                } else {
                    buyButton.setDisable(true);
                    streetCost.setText("Miete: " + card.rent + "€");
                    streetOwner.setText("Besitzer: " + card.owner.name);
                }
            }

            if (rolled != 0) {
                youGotA.setText(this.currentPlayer.name + " hat eine " + rolled + " gewürfelt!");
            }

            streetChoiceHouses.getItems().clear();
            for (Card street : this.currentPlayer.getEligibleStreetsForBuilding()) {
                streetChoiceHouses.getItems().add(street.name);
            }
            buyHouses.setDisable(streetChoiceHouses.getItems().isEmpty());
            streetChoiceHouses.setDisable(streetChoiceHouses.getItems().isEmpty());
            sellHouse.setDisable(this.currentPlayer.properties.stream().map(c -> c.houses).reduce(0, Integer::sum) == 0);

            streetSelector.getItems().clear();
            this.currentPlayer.properties.forEach(c -> streetSelector.getItems().add(c.name));
            streetSellButton.setDisable(streetSelector.getItems().isEmpty());
            streetSelector.setDisable(streetSelector.getItems().isEmpty());
            tradeButton.setDisable(streetSelector.getItems().isEmpty());

        }
    }

    public void hidePlayerStats(int numberPlayers) {
        for (int i = numberPlayers; i < 4; i++) {
            Label playerLabel = switch (i) {
                case 0 -> player1;
                case 1 -> player2;
                case 2 -> player3;
                case 3 -> player4;
                default -> null;
            };
            Label moneyLabel = switch (i) {
                case 0 -> player1_money;
                case 1 -> player2_money;
                case 2 -> player3_money;
                case 3 -> player4_money;
                default -> null;
            };
            Label propsLabel = switch (i) {
                case 0 -> player1_props;
                case 1 -> player2_props;
                case 2 -> player3_props;
                case 3 -> player4_props;
                default -> null;
            };
            if (playerLabel != null) {
                playerLabel.setVisible(false);
            }
            if (moneyLabel != null) {
                moneyLabel.setVisible(false);
            }
            if (propsLabel != null) {
                propsLabel.setVisible(false);
            }
        }

    }

    public void setBackgroundImage() {
        File imageFile = AssetFiles.recursiveFileSearch(new File("."), "Board.png");
        assert imageFile != null;
        String imagePath = imageFile.getAbsolutePath();
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
    }

    public void devBuild(Player name) {
        if (Objects.equals(name.name, "dev")) {
            Button cashButton = new Button("Cash Money");
            TextField stepField = new TextField();
            stepField.setLayoutX(100);
            Button changePlayerButton = new Button("Change Player");
            changePlayerButton.setLayoutX(200);
            Button bankruptButton = new Button("Bankrupt Player");
            bankruptButton.setLayoutX(300);
            Button playAnimationButton = new Button("Play Animation");
            playAnimationButton.setLayoutX(400);
            Button getPlayerPos = new Button("Get Player Pos");
            getPlayerPos.setLayoutX(500);
            Button giveAllStreets = new Button("Give all Streets");
            giveAllStreets.setLayoutX(600);

            cashButton.setOnAction(e -> {
                this.currentPlayer.money += 1000;
                updateUi(allPanes.get(this.currentPlayer.pos.intValue()), 0);
            });
            stepField.setOnAction(e -> {
                try {

                    step(Integer.parseInt(stepField.getText()), this.currentPlayer);
                } catch (Exception ex) {
                    System.err.println("Error " + ex);
                }
            });

            changePlayerButton.setOnAction(e -> {
                this.currentPlayer = players.get(activePlayer);
                if (activePlayer >= players.size() - 1) {
                    activePlayer = 0;
                    rounds++;
                } else {
                    activePlayer++;
                }
                header.setText(this.currentPlayer.toString() + " am Zug!");
                updateUi(allPanes.get(this.currentPlayer.pos.intValue()), 0);
            });

            bankruptButton.setOnAction(e -> {
                this.currentPlayer.money = -9999;
                updateUi(allPanes.get(this.currentPlayer.pos.intValue()), 0);
            });

            playAnimationButton.setOnAction(e -> {
                TranslateTransition animation = new TranslateTransition();
                animation.setDuration(Duration.seconds(1.0));
                animation.setNode(this.currentPlayer);
                animation.setToX(x5.getLayoutX() - this.currentPlayer.getLayoutX());
                animation.setToY(x5.getLayoutY() - this.currentPlayer.getLayoutY());
                animation.play();
            });

            giveAllStreets.setOnAction(e -> {
                for (Card card : cards.values()) {
                    if (card.cardColor != Card.StreetColor.None) {
                        card.buyStreet(this.currentPlayer, eventText, allPanes.get(card.id), true);
                    }
                }
                updateUi(allPanes.get(this.currentPlayer.pos.intValue()), 0);
            });

            getPlayerPos.setOnAction(e -> eventText.setText("Player " + this.currentPlayer.name + " is at " + this.currentPlayer.pos.get()));

            board.getChildren().add(cashButton);
            board.getChildren().add(stepField);
            board.getChildren().add(changePlayerButton);
            board.getChildren().add(bankruptButton);
            board.getChildren().add(playAnimationButton);
            board.getChildren().add(getPlayerPos);
            board.getChildren().add(giveAllStreets);

        }
    }

    public void dieterCheck(ArrayList<Player> playersN) {
        for (Player player : playersN) {
            if (player.name.equals("Dieter Janzen")) {
                cards.get(x39).buyStreet(player, eventText, allPanes.get(39), true);
                break;
            }
        }
    }

    public void countAlivePlayers() throws IOException, InterruptedException {
        int alivePlayers = 0;
        for (Player player : players) {
            if (player.alive) {
                alivePlayers++;
            }
        }

        if (winScreenCounter == 0) {
            if (alivePlayers == 1) {
                this.winScreenCounter++;
                Thread.sleep(1000);
                Stage currentStage = (Stage) board.getScene().getWindow();
                currentStage.close();

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("grunopoly-win.fxml"));
                Parent root = loader.load();

                GrunopolyWin controller = loader.getController();
                Optional<Player> maybeWinner = players.stream().filter(p -> p.alive).findFirst();

                if (maybeWinner.isPresent()) {
                    controller.setWinner(maybeWinner.get().name);
                } else {
                    System.out.println("Bruh");
                    System.exit(1);
                }


                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Grunopoly Win");
                newStage.show();
            }
        }
    }
}
