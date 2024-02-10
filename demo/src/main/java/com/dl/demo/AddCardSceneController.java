package com.dl.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCardSceneController {

    private CardLibraryEnum cardLibraryEnum = CardLibraryEnum.INSTANCE;

    @FXML
    private HBox hbName = new HBox();
    @FXML
    private TextField tfName = new TextField();
    @FXML
    private HBox hbType = new HBox();
    @FXML
    private TextField tfType = new TextField();
    @FXML
    private HBox hbExpansion = new HBox();
    @FXML
    private TextField tfExpansion = new TextField();
    @FXML
    private HBox hbRarity = new HBox();
    @FXML
    private ComboBox<Rarity> cbRarity = new ComboBox<>();
    @FXML
    private HBox hbManaCost = new HBox();
    @FXML
    private TextField tfManaCost = new TextField();
    @FXML
    private HBox hbRules = new HBox();
    @FXML
    private TextField tfRules = new TextField();
    @FXML
    private HBox hbPower = new HBox();
    @FXML
    private TextField tfPower = new TextField();
    @FXML
    private HBox hbToughness = new HBox();
    @FXML
    private TextField tfToughness = new TextField();
    @FXML
    private Button btnAdd = new Button();
    @FXML
    private ComboBox<CardTypes> cbCardType = new ComboBox<>();


    private void hideFields(){
        hbName.setVisible(false);
        hbType.setVisible(false);
        hbExpansion.setVisible(false);
        hbRarity.setVisible(false);
        hbManaCost.setVisible(false);
        hbRules.setVisible(false);
        hbPower.setVisible(false);
        hbToughness.setVisible(false);
    }
    private void showBasicFields(){
        hbName.setVisible(true);
        hbType.setVisible(true);
        hbExpansion.setVisible(true);
        hbRarity.setVisible(true);
    }

    private boolean isInt(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    private CardTypes cardType;
    private EventHandler<ActionEvent> cardTypeEvent = new EventHandler<>() {
        public void handle(ActionEvent e) {
            switch(cbCardType.getValue()) {
                case LAND:
                    showBasicFields();
                    hbManaCost.setVisible(false);
                    hbRules.setVisible(false);
                    hbPower.setVisible(false);
                    hbToughness.setVisible(false);
                    cardType = CardTypes.LAND;
                    break;
                case SPELL:
                    showBasicFields();
                    hbManaCost.setVisible(true);
                    hbRules.setVisible(true);
                    hbPower.setVisible(false);
                    hbToughness.setVisible(false);
                    cardType = CardTypes.SPELL;
                    break;
                case CREATURE:
                    showBasicFields();
                    hbManaCost.setVisible(true);
                    hbRules.setVisible(true);
                    hbPower.setVisible(true);
                    hbToughness.setVisible(true);
                    cardType = CardTypes.CREATURE;
                    break;
            }
        }
    };
    @FXML
    private void addCardToLib(ActionEvent event){
        switch (cardType){
            case LAND:
                Land tempLand = new Land(tfName.getText(), tfType.getText(), tfExpansion.getText(), cbRarity.getValue());
                cardLibraryEnum.add(tempLand);
                break;
            case SPELL:
                Spell tempSpell = new Spell(tfName.getText(), tfType.getText(), tfExpansion.getText(), cbRarity.getValue(), tfManaCost.getText(), tfRules.getText());
                cardLibraryEnum.add(tempSpell);
                break;
            case CREATURE:
                Creature tempCreature = new Creature(tfName.getText(), tfType.getText(), tfExpansion.getText(), cbRarity.getValue(), tfManaCost.getText(), tfRules.getText(), Integer.parseInt(tfPower.getText()), Integer.parseInt(tfToughness.getText()));
                cardLibraryEnum.add(tempCreature);
                break;
        }
        cardLibraryEnum.getTvObservableList().setAll(cardLibraryEnum.getLibrary());
        System.out.println("list size: " + cardLibraryEnum.size());
        System.out.println("card created");
    }

    @FXML
    private Button btnClear = new Button();
    @FXML
    private void clear(ActionEvent event){
        tfName.clear();
        tfType.clear();
        tfExpansion.clear();
        cbRarity.getSelectionModel().clearSelection();
        tfManaCost.clear();
        tfRules.clear();
        tfPower.clear();
        tfToughness.clear();
        System.out.println("fields cleared");
    };

    @FXML
    public void initialize(){

        cbCardType.getItems().setAll(CardTypes.values());
        cbCardType.setPromptText("Choose card type");
        cbCardType.setOnAction(cardTypeEvent);
        hideFields();

        cbRarity.getItems().setAll(Rarity.values());
        cbRarity.setPromptText("Choose rarity");
        btnAdd.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("inside add button");
                if(tfName.getText().isBlank()) {
                    PopUp.display("Missing value", "Card needs to at least have a name.");
                } else if(cbCardType.getValue() == CardTypes.CREATURE && !isInt(tfPower.getText())){
                    PopUp.display("Not a natural number", "Power must be a natural number.");
                } else if (cbCardType.getValue() == CardTypes.CREATURE && !isInt(tfToughness.getText())) {
                        PopUp.display("Not a natural number", "Toughness must be a natural number.");
                } else {
                    addCardToLib(actionEvent);
                    clear(actionEvent);
                    Node node = (Node)actionEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.close();
                }
            }
        });

        btnClear.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("inside clear button");
                clear(actionEvent);
            }
        });
    }
}
