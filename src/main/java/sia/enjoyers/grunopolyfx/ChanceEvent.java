package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChanceEvent {

    ChanceEvent(ArrayList<Player> players, Player player, Label eventText, List<Pane> allPanes) {
        int chosen = (int) (Math.random() * 5) + 1;
        System.out.println(player);
        switch (chosen) {
            case 1:
                // Ins Gefängnis

                System.out.println("bruh");
                player.setPosition(allPanes.get(40), player.pos.intValue());
                player.jailRounds = 3;

                GrunopolyStart.controller.updateUi(allPanes.get(40), 0);

                eventText.setText("Chance Karte: " + player.name + " kommt ins Gefängnis!");
                player.pos = new AtomicInteger(10);

                break;
            case 2:
                // Auf Los ziehen.

                eventText.setText("Chance Karte: " + player.name + " rückt auf Los!");
                int diff = (40 - player.pos.get());

                GrunopolyStart.controller.step(diff, player);

                break;
            case 3:
                // Dividenden

                eventText.setText("Chance Karte: " + "die Bank zahlt " + player.name + " 100€ in Dividenden!");
                player.money += 100;

                GrunopolyStart.controller.updateUi(allPanes.get(player.pos.get()), 0);

                break;
            case 4:
                // Renovierung

                int cost = 0;
                for (Card card : player.properties) {
                    cost += (card.houses * 25);
                }

                eventText.setText("Chance Karte: " + player.name + " zahlt 25€ Renovierungskosten für jedes besitzte Haus! (" + cost + "€)");

                break;
            case 5:
                // 3 Schritte nach hinten

                GrunopolyStart.controller.step(-3, player);
                eventText.setText("Chance Karte: " + player.name + " rückt 3 Felder zurück!");

                break;
        }
    }
}
