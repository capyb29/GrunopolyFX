package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Pane {
    String name;
    int money;
    Color color;
    AtomicInteger pos;
    int id;
    int jailRounds;
    ArrayList<Card> properties = new ArrayList<>();

    public ArrayList<Card.StreetColor> hasColor;
    public ArrayList<Card> housableStreets;

    Player(String name, int id, Color color, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.id = id;
        this.hasColor = new ArrayList<>();
        this.housableStreets = new ArrayList<>();
        this.pos = new AtomicInteger(0);
        this.setPrefSize(25, 25);
        this.color = color;

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

        this.setLayoutX(x + (15 * (id % 2)));
        this.setLayoutY(y + 20 - (15 * (id % 3)));

        this.pos.set(pos);

        this.setVisible(true);
    }

    public ArrayList<Card> getEligibleStreetsForBuilding() {
        ArrayList<Card> eligibleStreets = new ArrayList<>();

        HashMap<Card.StreetColor, ArrayList<Card>> cardsByColor = new HashMap<>();
        for (Card card : properties) {
            cardsByColor.computeIfAbsent(card.cardColor, k -> new ArrayList<>()).add(card);
        }

        for (Card.StreetColor color : cardsByColor.keySet()) {
            int requiredCount = color == Card.StreetColor.BROWN || color == Card.StreetColor.BLUE ? 2: 3;
            if (cardsByColor.get(color).size() >= requiredCount && hasColor.contains(color)) {
                eligibleStreets.addAll(cardsByColor.get(color));
            }
        }
        this.housableStreets = eligibleStreets;

        return eligibleStreets;
    }



    public void buildHouse(String card, Label eventText) {
        Card currentCard = properties.stream().filter(c -> c.name.equals(card)).toList().getFirst();

        if (currentCard.houses < 5 && money >= currentCard.price / 2) {
            currentCard.houses++;
            money -= currentCard.price / 2;

            eventText.setText(name + " hat ein Haus auf " + currentCard.name + " gebaut!");
        }
    }
}
