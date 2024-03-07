package sia.enjoyers.grunopolyfx;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class GrunopolyTrade {

    public interface TradingCompleteListener {
        void onTradingComplete();
    }

    public AnchorPane tradingBoardMain;
    public ChoiceBox<String> tradePartnerBox;
    public ToggleButton setMoneyOrStreet;
    public Slider sliderMoneyAmount;
    public Label tradeMoneyAmmount;
    public Label tradPartnerLabel;
    public ChoiceBox<String> tradePartnerStreets;
    public Button sendTradeOffer;
    public Button cancleTradeBtn;
    public ChoiceBox<String> chooseOwnStreet;
    public Label ownStreetLabel;

    private ArrayList<Player> tradePlayers;
    private Player currentTrader;
    private HashMap<Pane, Card> holyHashMap;
    private Label eventText;
    private TradingCompleteListener listener;

    public void initialize() {
        this.tradePartnerBox.setOnAction((event) -> {
            this.tradPartnerLabel.setText("Handel mit: " + this.tradePartnerBox.getValue());
            this.updateUi2();
        });

        this.cancleTradeBtn.setOnAction((event) -> {
            Stage thisStage = (Stage) this.tradingBoardMain.getScene().getWindow();
            thisStage.close();
        });

        this.tradePartnerStreets.setOnAction((event) -> {
            if (!Objects.equals(tradePartnerStreets.getValue(), "")) {
                String tradePartnerStreet = this.tradePartnerStreets.getValue();
                Optional<Player> tradePartner = this.tradePlayers.stream().filter((player) -> player.name.equals(this.tradePartnerBox.getValue())).findFirst();
                if (tradePartner.isPresent()) {
                    Optional<Card> tradeProperty = tradePartner.get().properties.stream().filter((card) -> card.name.equals(tradePartnerStreet)).findFirst();
                    tradeProperty.ifPresent(this::setMoneySlider);
                    this.tradeMoneyAmmount.setText((int) (sliderMoneyAmount.getValue()) + "€");
                    this.sendTradeOffer.setDisable(false);
                    this.sliderMoneyAmount.setDisable(false);
                    this.setMoneyOrStreet.setDisable(false);

                }
                this.tradeMoneyAmmount.setVisible(true);

            } else {
                this.sendTradeOffer.setDisable(true);
                this.sliderMoneyAmount.setDisable(true);
                this.setMoneyOrStreet.setDisable(true);
                this.tradPartnerLabel.setText("Wähle eine Straße zum Handeln aus!");
                this.tradeMoneyAmmount.setVisible(false);
            }
        });

        this.setMoneyOrStreet.setOnAction((event) -> {
            if (setMoneyOrStreet.isSelected()) {
                this.sliderMoneyAmount.setDisable(true);
                this.sliderMoneyAmount.setVisible(false);
                this.tradeMoneyAmmount.setVisible(false);
                this.ownStreetLabel.setVisible(true);
                this.chooseOwnStreet.setVisible(true);
                this.chooseOwnStreet.setDisable(false);

                this.setMoneyOrStreet.setText("Straße");

                this.chooseOwnStreet.getItems().clear();
                for (Card card : this.currentTrader.properties) {
                    this.chooseOwnStreet.getItems().add(card.name);
                }
            } else {
                this.setMoneyOrStreet.setText("Geld");

                this.sliderMoneyAmount.setDisable(false);
                this.sliderMoneyAmount.setVisible(true);
                this.tradeMoneyAmmount.setVisible(true);
                this.ownStreetLabel.setVisible(false);
                this.chooseOwnStreet.setVisible(false);
                this.chooseOwnStreet.setDisable(true);
            }
        });

        this.sendTradeOffer.setOnAction((event) -> {
                sendTradeOffer();
                listener.onTradingComplete();
        });

        updateUi2();
    }

    public void setTradePartnerBox(Player crrPlayer, Player[] players) {
        this.currentTrader = crrPlayer;
        for (Player player : players) {
            if (player != this.currentTrader && player.alive && !player.properties.isEmpty() ){
                this.tradePartnerBox.getItems().add(player.name);
            }
        }
        this.tradePartnerBox.getItems().add("");
    }

    private void updateUi2() {
        this.tradePartnerStreets.getItems().clear();
        if (this.tradePartnerBox.getValue() != null && !this.tradePartnerBox.getValue().isEmpty()) {
            String tradePartnerName = this.tradePartnerBox.getValue();
            Optional<Player> tradePartner = this.tradePlayers.stream().filter((player) -> player.name.equals(tradePartnerName)).findFirst();
            if (tradePartner.isPresent()) {
                for (Card card : tradePartner.get().properties) {
                    this.tradePartnerStreets.getItems().add(card.name);
                }
            }
            this.tradePartnerStreets.setDisable(false);
        } else {

            this.tradePartnerStreets.setDisable(true);
            this.sendTradeOffer.setDisable(true);
            this.sliderMoneyAmount.setDisable(true);
            this.setMoneyOrStreet.setDisable(true);
            this.tradPartnerLabel.setText("Wähle einen Handelspartner aus!");
            this.tradeMoneyAmmount.setVisible(false);
            this.chooseOwnStreet.setDisable(true);
        }
        if (!this.setMoneyOrStreet.isSelected()) {
            this.setMoneyOrStreet.setText("Geld");

            this.sliderMoneyAmount.setDisable(false);
            this.sliderMoneyAmount.setVisible(true);
            this.tradeMoneyAmmount.setVisible(true);
            this.ownStreetLabel.setVisible(false);
            this.chooseOwnStreet.setVisible(false);
            this.chooseOwnStreet.setDisable(true);
        }

    }

    public void setTradePlayers(ArrayList<Player> players) {
        this.tradePlayers = players;
    }

    public void setMoneySlider(Card tradeProperty) {
        this.sliderMoneyAmount.setMin(tradeProperty.price);
        this.sliderMoneyAmount.setMax(this.currentTrader.money);
        this.sliderMoneyAmount.setValue(tradeProperty.price);
        this.sliderMoneyAmount.valueProperty().addListener((observable, oldValue, newValue) -> this.tradeMoneyAmmount.setText(newValue.intValue() + "€"));
    }

    public void sendTradeOffer() {
        String tradePartnerName = this.tradePartnerBox.getValue();
        Optional<Player> tradePartner = this.tradePlayers.stream().filter((player) -> player.name.equals(tradePartnerName)).findFirst();
        if (tradePartner.isPresent()) {
            String tradePartnerStreet = this.tradePartnerStreets.getValue();
            Optional<Card> tradeProperty = tradePartner.get().properties.stream().filter((card) -> card.name.equals(tradePartnerStreet)).findFirst();
            if (tradeProperty.isPresent()) {
                if (tradeProperty.get().houses > 0) {
                    this.tradPartnerLabel.setText("Du kannst keine Straße mit Häusern handeln!");
                    return;
                }
                if (setMoneyOrStreet.isSelected()) {
                    Optional<Card> ownProperty = this.currentTrader.properties.stream().filter((card) -> card.name.equals(this.chooseOwnStreet.getValue())).findFirst();
                    if (ownProperty.isPresent()) {
                        if (ownProperty.get().houses > 0) {
                            this.tradPartnerLabel.setText("Du kannst keine Straße mit Häusern handeln!");
                            return;
                        }
                        for (Map.Entry<Pane, Card> entry : holyHashMap.entrySet()) {
                            if (entry.getValue().equals(ownProperty.get())) {
                                ownProperty.get().buyStreet(tradePartner.get(),eventText,entry.getKey(),true);
                                tradePartner.get().properties.remove(tradeProperty.get());
                            }
                            if (entry.getValue().equals(tradeProperty.get())) {
                                tradeProperty.get().buyStreet(this.currentTrader,eventText,entry.getKey(),true);
                                currentTrader.properties.remove(ownProperty.get());
                            }
                        }
                    }
                } else {
                    for (Map.Entry<Pane, Card> entry : holyHashMap.entrySet()) {
                        if (entry.getValue().equals(tradeProperty.get())) {
                            tradeProperty.get().buyStreet(this.currentTrader,eventText,entry.getKey(),true);
                            tradePartner.get().properties.remove(tradeProperty.get());
                        }
                    }
                    this.currentTrader.money -= (int) this.sliderMoneyAmount.getValue();
                    tradePartner.get().money += (int) this.sliderMoneyAmount.getValue();
                }
            }
        }
        Stage thisStage = (Stage) this.tradingBoardMain.getScene().getWindow();
        thisStage.close();
    }

    public void portHolyHashmap(HashMap<Pane, Card> hhmap) {
        this.holyHashMap = hhmap;
    }

    public void getEventText(Label eventText) {
        this.eventText = eventText;
    }

    public void setTradingCompleteListener(TradingCompleteListener listener) {
        this.listener = listener;
    }

}
