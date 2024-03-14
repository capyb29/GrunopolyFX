package sia.enjoyers.grunopolyfx;

import javafx.animation.TranslateTransition;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Pane {
    String name;
    boolean alive;
    int money;
    Color color;
    AtomicInteger pos;
    int id;
    int jailRounds;
    ArrayList<Card> properties = new ArrayList<>();

    public ArrayList<Card.StreetColor> hasColor;
    public ArrayList<Card> housableStreets;
    public TranslateTransition currentAnimation;

    Player(String name, int id, Color color, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.id = id;
        this.hasColor = new ArrayList<>();
        this.housableStreets = new ArrayList<>();
        this.pos = new AtomicInteger(0);
        this.setPrefSize(25, 25);
        this.color = color;
        this.alive = true;

        // Player appearance
        String hex = String.format("#%02x%02x%02x", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));

        String textStyle = """
                    -fx-font: bold 17px 'Comic Sans MS';
                    -fx-stroke-width: 1;
                    -fx-line-spacing: -5;
                    -fx-alignment: TOP_CENTER;
                """;

        Text text = new Text("\n " + this.id);
        this.getChildren().add(text);

        text.setStyle(String.format(textStyle, hex));

        String style = """
                    -fx-background-radius: 15px;
                    -fx-background-color: %s;
                    -fx-text-fill: RED;

                    -fx-border-radius: 15px;
                    -fx-border-width: 2px;
                    -fx-border-color: black;
                    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 15, 0, 0, 0);
                """;

        this.setStyle(String.format(style, hex));

        this.setVisible(false);
    }

    public void setPosition(Pane referencePane, int pos) {
        double targetX = referencePane.getLayoutX() + (15 * (id % 2));
        double targetY = referencePane.getLayoutY() + 20 - (15 * (id % 3));

        this.pos.set(pos);

        currentAnimation = new TranslateTransition();
        currentAnimation.setDuration(Duration.seconds(0.3));
        currentAnimation.setNode(this);
        currentAnimation.setToX(targetX - this.getLayoutX());
        currentAnimation.setToY(targetY - this.getLayoutY());
        currentAnimation.play();

        this.setVisible(true);
    }

    public ArrayList<Card> getEligibleStreetsForBuilding() {
        ArrayList<Card> eligibleStreets = new ArrayList<>();

        HashMap<Card.StreetColor, ArrayList<Card>> cardsByColor = new HashMap<>();
        for (Card card : properties) {
            cardsByColor.computeIfAbsent(card.cardColor, k -> new ArrayList<>()).add(card);
        }

        for (Card.StreetColor color : cardsByColor.keySet()) {
            int requiredCount = color == Card.StreetColor.BROWN || color == Card.StreetColor.BLUE ? 2 : 3;
            if (cardsByColor.get(color).size() >= requiredCount && hasColor.contains(color)) {
                eligibleStreets.addAll(cardsByColor.get(color));
            }
        }
        this.housableStreets = eligibleStreets;

        return eligibleStreets;
    }

    public void buildHouse(String card, Label eventText, List<Pane> allPanes) {
        Card currentCard = properties.stream().filter(c -> c.name.equals(card)).toList().getFirst();

        if (currentCard.houses < 5 && money >= currentCard.price / 2) {
            currentCard.houses++;
            money -= (int) (currentCard.price * 1.5);
            eventText.setText(name + " hat ein Haus auf " + currentCard.name + " gebaut!");
            currentCard.updateRent(currentCard.houses);

            Pane currentPane = allPanes.get(currentCard.id);

            Pane housePane = new Pane();

            String style = """
                        -fx-background-radius: 15px;
                        -fx-background-color: %s;
                        -fx-text-fill: RED;

                        -fx-border-radius: 3px;
                        -fx-border-width: 2px;
                        -fx-border-color: black;
                        -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 5, 0, 0, 0);
                    """;

            housePane.setPrefHeight(13.0);
            housePane.setPrefWidth(13.0);
            housePane.setStyle(String.format(style, "#FF0000"));
            housePane.setVisible(true);

            currentPane.getChildren().add(housePane);

            if (currentCard.id < 10) {
                housePane.setLayoutY(-67);
                housePane.setLayoutX(-52 + (17 * currentCard.houses));

            } else if (currentCard.id < 20) {
                housePane.setLayoutY(-48 + (15 * currentCard.houses));
                housePane.setLayoutX(73);

            } else if (currentCard.id < 30) {
                housePane.setLayoutY(67);
                housePane.setLayoutX(-52 + (17 * currentCard.houses));

            } else if (currentCard.id < 40) {

                housePane.setLayoutY(-48 + (15 * currentCard.houses));
                housePane.setLayoutX(-70);
            }
        }
    }

    public void sellHouse(String card, Label eventText, List<Pane> allPanes) {
        Card currentCard = properties.stream().filter(c -> c.name.equals(card)).toList().getFirst();
        if (currentCard.houses > 0) {
            currentCard.houses--;
            money += (int) (currentCard.price / 1.5);
            eventText.setText(name + " hat ein Haus auf " + currentCard.name + " verkauft!");
            currentCard.updateRent(currentCard.houses);

            Pane currentPane = allPanes.get(currentCard.id);
            currentPane.getChildren().removeLast();
        }
    }

    public void sellStreet(Label eventText, ChoiceBox<String> selector, List<Pane> allPanes) {
        String card = selector.getValue();
        boolean exist = !properties.stream().filter(c -> c.name.equals(card)).toList().isEmpty();
        if (exist) {
            Card currentCard = properties.stream().filter(c -> c.name.equals(card)).toList().getFirst();
            Pane currentPane = allPanes.get(currentCard.id);
            currentCard.owner = null;
            money += (int) (currentCard.price * 0.5 + ((currentCard.price / 2) * currentCard.houses));
            eventText.setText(name + " hat " + currentCard.name + " vekauft!");

            properties.remove(currentCard);
            selector.getItems().remove(card);

            currentCard.rent = currentCard.baseRent;
            currentCard.houses = 0;
            currentPane.setVisible(false);
            currentPane.getChildren().clear();
        }
    }

    public void isAliveCheck(List<Pane> allPanes) {
        if (this.money < 0 && this.alive) {
            this.alive = false;

            for (Card card : properties) {
                card.rent = card.baseRent;
                card.houses = 0;
                card.owner = null;

                Pane pane = allPanes.get(card.id);
                pane.setVisible(false);
                pane.getChildren().clear();
            }

            this.setOpacity(0.2);
            this.properties.clear();
        }
    }
}
