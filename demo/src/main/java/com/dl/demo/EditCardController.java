package com.dl.demo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCardController {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private HBox hbActionButtons;
    @FXML
    private HBox hbExpansion;
    @FXML
    private HBox hbManaCost;
    @FXML
    private HBox hbName;
    @FXML
    private HBox hbPower;
    @FXML
    private HBox hbRarity;
    @FXML
    private HBox hbRules;
    @FXML
    private HBox hbToughness;
    @FXML
    private HBox hbType;
    @FXML
    private TextField tfExpansion;
    @FXML
    private TextField tfManaCost;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPower;
    @FXML
    private TextField tfRules;
    @FXML
    private TextField tfToughness;
    @FXML
    private TextField tfType;
    @FXML
    private VBox vbEditCard;
    @FXML
    private ComboBox<Rarity> cbRarity;

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
    private void refreshTable(){
        CardLibraryEnum.INSTANCE.getTvObservableList().clear();
        CardLibraryEnum.INSTANCE.getTvObservableList().setAll(CardLibraryEnum.INSTANCE.getLibrary());
    }
    private String type;
    private Card cardToEdit;
    public void initData(Card selectedCard) {
        cardToEdit = selectedCard;
        cbRarity.getItems().setAll(Rarity.values());
        cbRarity.setValue(selectedCard.getRarity());
        hideFields();
        showBasicFields();
        tfName.setText(selectedCard.getCardName());
        tfType.setText(selectedCard.getCardType());
        tfExpansion.setText(selectedCard.getExpansionSet());

        if (selectedCard instanceof Creature) {
            System.out.println("creat");
            type = "creature";
            tfManaCost.setText(((Spell) selectedCard).getManaCost());
            tfRules.setText(((Spell) selectedCard).getCardText());
            tfPower.setText(Integer.toString(((Creature) selectedCard).getPower()));
            tfToughness.setText(Integer.toString(((Creature) selectedCard).getToughness()));

            hbManaCost.setVisible(true);
            hbRules.setVisible(true);
            hbPower.setVisible(true);
            hbToughness.setVisible(true);

        } else if (selectedCard instanceof Spell) {
            System.out.println("spell");
            type = "spell";
            tfManaCost.setText(((Spell) selectedCard).getManaCost());
            tfRules.setText(((Spell) selectedCard).getCardText());

            hbManaCost.setVisible(true);
            hbRules.setVisible(true);

        } else if (selectedCard instanceof Land) {
            type = "land";
            System.out.println("land");
        }
    }

    @FXML
    public void initialize(){

        btnSave.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("save btn clicked");
                cardToEdit.setCardName(tfName.getText());
                cardToEdit.setCardType(tfType.getText());
                cardToEdit.setExpansionSet(tfExpansion.getText());
                cardToEdit.setRarity(cbRarity.getValue());

                switch (type){
                    case "creature":
                        ((Creature)cardToEdit).setManaCost(tfManaCost.getText());
                        ((Creature)cardToEdit).setCardText(tfRules.getText());
                        ((Creature)cardToEdit).setPower(Integer.parseInt(tfPower.getText()));
                        ((Creature)cardToEdit).setToughness(Integer.parseInt(tfToughness.getText()));
                        break;

                    case "spell":
                        ((Spell)cardToEdit).setManaCost(tfManaCost.getText());
                        ((Spell)cardToEdit).setCardText(tfRules.getText());
                        break;
                    case "land":
                        break;
                }
                refreshTable();
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();
            }
        });

        btnCancel.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("cancel btn clicked");
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            }
        });
    }
}