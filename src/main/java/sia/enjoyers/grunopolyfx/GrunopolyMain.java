package sia.enjoyers.grunopolyfx;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Scale;

import java.io.File;
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

    List<Pane> allPanes;
    ArrayList<Player> players = new ArrayList<>();
    public HashMap<Pane, Card> cards;
    int activePlayer = 0;
    int rounds = 0;
    int diceNumber = 0;

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

        buyButton.setOnAction(e -> {
            Player player = players.get(activePlayer);
            Pane cPane = allPanes.get(player.pos.intValue());
            Card cCard = cards.get(cPane);
            cCard.buyStreet(player, eventText, cPane);
            // Update UI
            updateUi(cPane, diceNumber);
        });

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

        stepButton.setOnAction(event -> {
            if (activePlayer >= players.size() - 1) {
                activePlayer = 0;
                rounds++;
            } else {
                activePlayer++;
            }

            this.diceNumber = (int) Math.max(2, 1 + (Math.random() * 12));
            step(this.diceNumber, players.get(activePlayer));
        });

        buyHouses.setOnAction(event -> {
            Player player = players.get(activePlayer);
            if (streetChoiceHouses.getValue() == null) {
                eventText.setText("Bitte wählen Sie eine Straße aus!");
                return;
            }
            String streetName = streetChoiceHouses.getValue();

            player.buildHouse(streetName, eventText, allPanes);
            updateUi(allPanes.get(player.pos.intValue()), 0);
            streetChoiceHouses.setValue(streetName);

        });

        streetSellButton.setOnAction(event -> {
            Player player = players.get(activePlayer);
            Pane pane = allPanes.get(player.pos.get());

            player.sellStreet(eventText, streetSelector, allPanes);
            updateUi(pane,0);
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
            player.setPosition(x40, player.pos.intValue());
            player.jailRounds = 3;
            updateUi(x40, 10);

            eventText.setText(player.name + " ist ins Gefängnis geraten!");
            player.pos = new AtomicInteger(10);
            return;
        } else if (newDesiredPane == x20) {
            // Steuern
            int moolah = (int) (player.money * 0.1);
            player.money -= Math.abs(moolah);

            eventText.setText(player.name + " hat die 10%-Steuer bezahlt!\n-"+ moolah + "€");
        }

        assert newDesiredCard != null;
        newDesiredCard.payRent(player, eventText);
        newDesiredCard.playersOnCard.add(player);

        player.setPosition(newDesiredPane, newPos);
        updateUi(newDesiredPane, stepCount);

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

        dieterCheck(players);
        hidePlayerStats(playerCount);
        activePlayer = (int) (Math.random() * players.size());
        updateUi(x0, 0);
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

    private void updateUi(Pane currentPane, int rolled) {
        // Update player labels
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
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
            }
            if (moneyLabel != null) {
                moneyLabel.setText(player.money + "€");
            }

            if (propsLabel != null) {
                final String[] props = {""};
                player.properties.forEach((card) -> props[0] += card.name + "[" + card.houses + "]" + ", ");
                propsLabel.setText(props[0]);
            }

            // Player death
            if (players.get(activePlayer).money <= 0) {
                    return;
            }

            Card card = cards.get(currentPane);

            header.setText(players.get(activePlayer).name + " am Zug!");
            youAreAt.setText("Sie befinden sich auf: " + card.name);

            buyButton.setDisable(true);
            streetOwner.setVisible(false);
            streetCost.setVisible(false);
            stepButton.setText("Würfeln");

            // Gefängnis
            if (players.get(activePlayer).jailRounds > 0) {
                streetOwner.setVisible(false);
                streetCost.setVisible(false);
                buyButton.setDisable(true);
                stepButton.setText("Skip");
                youGotA.setText(players.get(activePlayer).jailRounds > 1 ? "Warte " + players.get(activePlayer).jailRounds + " Runden." : "Warte 1 Runde.");
                players.get(activePlayer).jailRounds--;
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
                youGotA.setText(players.get(activePlayer).name + " hat eine " + rolled + " gewürfelt!");
            }

            streetChoiceHouses.getItems().clear();
            for (Card street : players.get(activePlayer).getEligibleStreetsForBuilding()) {
                streetChoiceHouses.getItems().add(street.name);
            }
            buyHouses.setDisable(streetChoiceHouses.getItems().isEmpty());

            streetSelector.getItems().clear();
            players.get(activePlayer).properties.forEach(c -> streetSelector.getItems().add(c.name));
            streetSellButton.setDisable(streetSelector.getItems().isEmpty());
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
        File imageFile = recursiveSearchImage(new File("."), "Board.png");
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

    public File recursiveSearchImage(File rootDirectory, String filename) {
        if (rootDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(rootDirectory.listFiles())) {
                if (file.getName().equals(filename)) {
                    return file;
                }
                File foundFile = recursiveSearchImage(file, filename);
                if (foundFile != null) {
                    return foundFile;
                }
            }
        }
        return null;
    }

    public void devBuild(Player name) {
        if (Objects.equals(name.name, "dev")) {
            Button cashButton = new Button("Cash Money");
            Button oneStepButton = new Button("One Step");
            oneStepButton.setLayoutX(100);
            Button changePlayerButton = new Button("Change Player");
            changePlayerButton.setLayoutX(200);
            cashButton.setOnAction(e -> {
                players.get(activePlayer).money += 1000;
                updateUi(allPanes.get(players.get(activePlayer).pos.intValue()), 0);
            });
            oneStepButton.setOnAction(e -> {
                int initial = players.get(activePlayer).pos.get();
                int newPos = initial + 1 <= 39 ? initial + 1 : (initial + 1) % 40;

                this.cards.forEach((pane, cards) -> cards.playersOnCard.removeIf(p -> p.id == players.get(activePlayer).id));

                Pane newDesiredPane = allPanes.get(newPos);
                Card newDesiredCard = this.cards.get(newDesiredPane);

                assert newDesiredCard != null;
                newDesiredCard.playersOnCard.add(players.get(activePlayer));

                players.get(activePlayer).setPosition(newDesiredPane, newPos);

                updateUi(newDesiredPane, 1);
            });

            changePlayerButton.setOnAction(e -> {
                if (activePlayer >= players.size() - 1) {
                    activePlayer = 0;
                    rounds++;
                } else {
                    activePlayer++;
                }
                header.setText(players.get(activePlayer).toString() + " am Zug!");
                updateUi(allPanes.get(players.get(activePlayer).pos.intValue()), 0);
            });

            board.getChildren().add(cashButton);
            board.getChildren().add(oneStepButton);
            board.getChildren().add(changePlayerButton);

        }
    }

    public void dieterCheck(ArrayList<Player> playersN) {
        for (Player player : playersN) {
            if (player.name.equals("Dieter Janzen")) {
                player.properties.add(cards.get(x39));
                cards.get(x39).owner = player;
                break;
            }
        }
    }
}
