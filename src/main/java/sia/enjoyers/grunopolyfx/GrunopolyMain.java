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

    // Get interactive elements
    @FXML
    private Button stepButton;


    // Declare data

    List<Pane> allPanes;
    ArrayList<Player> players = new ArrayList<>();
    public HashMap<Pane, Card> cards;
    int activePlayer = 0;
    int rounds = 0;

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

        allPanes = Arrays.asList(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30, x31, x32, x33, x34, x35, x36, x37, x38, x39);

        allPanes.forEach((pane) -> pane.setVisible(false));

        // Set properties
        cards = new HashMap<>();
        cards.put(x0, new Card("Los", -1, Card.StreetColor.None));
        cards.put(x1, new Card("Athenaeum", 0, Card.StreetColor.BROWN));
        cards.put(x2, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x3, new Card("VLG", 0, Card.StreetColor.BROWN));
        cards.put(x4, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x5, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x6, new Card("Rossmann", 0, Card.StreetColor.LIGHTBLUE));
        cards.put(x7, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x8, new Card("H&M", 0, Card.StreetColor.LIGHTBLUE));
        cards.put(x9, new Card("Thalia", 0, Card.StreetColor.LIGHTBLUE));
        cards.put(x10, new Card("Gefängnis", -1, Card.StreetColor.None));
        cards.put(x11, new Card("Parkhotel Stade", 0, Card.StreetColor.PINK));
        cards.put(x12, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x13, new Card("Stadissimo", 0, Card.StreetColor.PINK));
        cards.put(x14, new Card("Stadeum", 0, Card.StreetColor.PINK));
        cards.put(x15, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x16, new Card("Al Porto", 0, Card.StreetColor.ORANGE));
        cards.put(x17, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x18, new Card("Mister Vu", 0, Card.StreetColor.ORANGE));
        cards.put(x19, new Card("Tacos", 0, Card.StreetColor.ORANGE));
        cards.put(x20, new Card("Nichts", -1, Card.StreetColor.None));
        cards.put(x21, new Card("Commerzbank", 0, Card.StreetColor.RED));
        cards.put(x22, new Card("Unbesetzt", 0, Card.StreetColor.None));
        cards.put(x23, new Card("Sparkasse", 0, Card.StreetColor.RED));
        cards.put(x24, new Card("Postbank", 0, Card.StreetColor.RED));
        cards.put(x25, new Card("Unbesetzt", 0, Card.StreetColor.None));
        cards.put(x26, new Card("Lidl", 0, Card.StreetColor.YELLOW));
        cards.put(x27, new Card("Netto", 0, Card.StreetColor.YELLOW));
        cards.put(x28, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x29, new Card("REWE", 0, Card.StreetColor.YELLOW));
        cards.put(x30, new Card("Gehe ins Gefängnis", -1, Card.StreetColor.None));
        cards.put(x31, new Card("Orient Express", 0, Card.StreetColor.GREEN));
        cards.put(x32, new Card("Rena's Grill", 0, Card.StreetColor.GREEN));
        cards.put(x33, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x34, new Card("Köz", 0, Card.StreetColor.GREEN));
        cards.put(x35, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x36, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x37, new Card("Pferdemarkt", 0, Card.StreetColor.BLUE));
        cards.put(x38, new Card("Unbesetzt", -1, Card.StreetColor.None));
        cards.put(x39, new Card("Jobelmann-Schule", 0, Card.StreetColor.BLUE));

        stepButton.setOnAction(event -> {
            int randNum = (int) Math.max(2, 1 + (Math.random() * 12));

            step(randNum, players.get(activePlayer));
            if (activePlayer >= players.size() - 1) {
                activePlayer = 0;
                rounds++;
            } else {
                activePlayer++;
            }

        });
    }


    public void step(int stepCount, Player player) {

        int initial = player.pos.get();
        int newPos = initial + stepCount <= 39 ? initial + stepCount : (initial + stepCount) % 40;

        this.cards.forEach((pane, cards) -> cards.playersOnCard.removeIf(p -> p.id == player.id));

        Pane newDesiredPane = allPanes.get(newPos);
        Card newDesiredCard = this.cards.get(newDesiredPane);

        assert newDesiredCard != null;
        newDesiredCard.playersOnCard.add(player);


        player.setPosition(newDesiredPane, newPos);

        System.out.println("Player " + player.id + " rolled " + stepCount + " | " + " Old Pos: " + initial + " New Pos: " + newPos);

        updateUi(newDesiredPane, stepCount);
        System.out.println(player.pos.get());
    }

    public void initPlayers (int playerCount, String[] playerNames) {
        for (int i = 0; i < playerCount; i++) {
            Color hsb = Color.hsb((((double) 360 / playerCount) * (i + 1)), .5, .7);

            Player player = new Player(playerNames[i], i + 1, hsb, 1000);
            player.setPosition(allPanes.getFirst(), 0);

            board.getChildren().add(player);
            players.add(player);
        }

        hidePlayerStats(playerCount);
        activePlayer = (int) (Math.random() * players.size());
        header.setText("Spieler "+ activePlayer + " am Zug!");
        updateUi(x0, 0);
    }

    private record SceneSizeChangeListener(double ratio, double initHeight, double initWidth, Pane contentPane) implements ChangeListener<Number> {
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
            //TODO remove brackets
            if (propsLabel != null) {
                propsLabel.setText(player.properties.toString());
            }

            header.setText("Spieler "+ (activePlayer + 1) + " am Zug!");

            youAreAt.setText("Sie befinden sich auf: " + cards.get(currentPane).name);

            if (rolled != 0) {
                youGotA.setText("Sie haben eine " + rolled + " gewürfelt!");

            } else {
                youGotA.setText("Viel Glück!");
            }


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


}
