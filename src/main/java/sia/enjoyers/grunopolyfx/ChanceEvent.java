package sia.enjoyers.grunopolyfx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChanceEvent {

    ChanceEvent(ArrayList<Player> players, Player player, Label eventText, List<Pane> allPanes) {
        int chosen = (int) (Math.random() * 7) + 1;

        switch (chosen) {
            case 1:
                // Ins Gefängnis

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

                player.money -= cost;
                eventText.setText("Chance Karte: " + player.name + " zahlt 25€ Renovierungskosten für jedes besitzte Haus! (" + cost + "€)");

                GrunopolyStart.controller.updateUi(allPanes.get(player.pos.get()), 0);

                break;
            case 5:
                // 3 Schritte nach hinten

                GrunopolyStart.controller.step(-3, player);
                eventText.setText("Chance Karte: " + player.name + " rückt 3 Felder zurück!");

                break;
            case 6:
                // Geld an spieler zahlen

                for (int i = 0; i < players.size(); i++) {
                    Player plr = players.get(i);
                    if (!plr.alive || plr.id == player.id) {continue;}

                    player.money -= 50;
                    plr.money += 50;
                }
                eventText.setText("Chance Karte: " + player.name + " zahlt jedem Spieler 50€!");

                GrunopolyStart.controller.updateUi(allPanes.get(player.pos.get()), 0);

                break;
            case 7:
                // Zufallsfeld

                GrunopolyStart.controller.step((int) (Math.random() * 40) + 1, player);
                eventText.setText("Chance Karte: " + player.name + " rückt auf ein zufälliges Feld!");

                break;
        }
    }
}
