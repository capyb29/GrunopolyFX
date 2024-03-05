package sia.enjoyers.grunopolyfx;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class GrunopolyTrade {
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

//todo add trade offer to player

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

            }
            updateUi2();
        });

        this.setMoneyOrStreet.setOnAction((event) -> updateUi2());
        updateUi2();
    }

    public void setTradePartnerBox(Player crrPlayer, Player[] players) {
        this.currentTrader = crrPlayer;
        for (Player player : players) {
            if (player != this.currentTrader) {
                this.tradePartnerBox.getItems().add(player.name);
            }
        }
        this.tradePartnerBox.getItems().add("");
    }

    private void updateUi2() {
        if (this.tradePartnerBox.getValue() != null && !this.tradePartnerBox.getValue().isEmpty()) {
            this.tradePartnerStreets.getItems().clear();
            String tradePartnerName = this.tradePartnerBox.getValue();
            Optional<Player> tradePartner = this.tradePlayers.stream().filter((player) -> player.name.equals(tradePartnerName)).findFirst();
            if (tradePartner.isPresent()) {
                for (Card card : tradePartner.get().properties) {
                    this.tradePartnerStreets.getItems().add(card.name);
                }
            }
            this.tradePartnerStreets.setDisable(false);
        } else {
            this.tradePartnerStreets.getItems().clear();
            this.tradePartnerStreets.setDisable(true);
            this.sendTradeOffer.setDisable(true);
            this.sliderMoneyAmount.setDisable(true);
            this.setMoneyOrStreet.setDisable(true);
            this.tradPartnerLabel.setText("Wähle einen Handelspartner aus!");
            this.tradeMoneyAmmount.setVisible(false);
            this.chooseOwnStreet.setDisable(true);
        }

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

}
