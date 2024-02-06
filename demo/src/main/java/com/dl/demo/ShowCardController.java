package com.dl.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShowCardController {

    @FXML
    private HBox hbName = new HBox();
    @FXML
    private Label labelName = new Label();
    @FXML
    private HBox hbType = new HBox();
    @FXML
    private Label labelType = new Label();
    @FXML
    private HBox hbExpansion = new HBox();
    @FXML
    private Label labelExpansion = new Label();
    @FXML
    private HBox hbRarity = new HBox();
    @FXML
    private Label labelRarity = new Label();
    @FXML
    private HBox hbManaCost = new HBox();
    @FXML
    private Label labelManaCost = new Label();
    @FXML
    private HBox hbRules = new HBox();
    @FXML
    private Label labelRules = new Label();
    @FXML
    private HBox hbPower = new HBox();
    @FXML
    private Label labelPower = new Label();
    @FXML
    private HBox hbToughness = new HBox();
    @FXML
    private Label labelToughness = new Label();

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

    public void initData(Card selectedCard){
        hideFields();
        showBasicFields();
        labelName.setText(selectedCard.getCardName());
        labelType.setText(selectedCard.getCardType());
        labelExpansion.setText(selectedCard.getExpansionSet());
        labelRarity.setText(selectedCard.getRarity().getValue());

        if(selectedCard instanceof Creature){
            System.out.println("creat");
            labelManaCost.setText(((Spell) selectedCard).getManaCost());
            labelRules.setText(((Spell) selectedCard).getCardText());
            labelPower.setText(Integer.toString(((Creature) selectedCard).getPower()));
            labelToughness.setText(Integer.toString(((Creature) selectedCard).getToughness()));

            hbManaCost.setVisible(true);
            hbRules.setVisible(true);
            hbPower.setVisible(true);
            hbToughness.setVisible(true);

        }else if(selectedCard instanceof Spell){
            System.out.println("spell");
            labelManaCost.setText(((Spell) selectedCard).getManaCost());
            labelRules.setText(((Spell) selectedCard).getCardText());

            hbManaCost.setVisible(true);
            hbRules.setVisible(true);

        }else if(selectedCard instanceof Land){
            System.out.println("land");
        }
    }
}
