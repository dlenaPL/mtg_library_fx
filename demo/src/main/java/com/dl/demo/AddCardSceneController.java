package com.dl.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddCardSceneController {

    CardLibraryEnum cardLibraryEnum = CardLibraryEnum.INSTANCE;

    @FXML
    private VBox vbAddCardScene = new VBox();
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
    private HBox hbActionButtons = new HBox();
    @FXML
    private Button btnAdd = new Button();

    @FXML
    private void addCardToLib(ActionEvent event){
        String name = tfName.getText();
        String type = tfType.getText();
        String expansion = tfExpansion.getText();
        Rarity rarity = cbRarity.getValue();
        String manaCost = tfName.getText();
        String rules = tfName.getText();
        int power = Integer.parseInt(tfPower.getText());
        int toughness = Integer.parseInt(tfToughness.getText());

        Creature temp = new Creature(name, type, expansion, rarity, manaCost, rules, power, toughness);

        cardLibraryEnum.add(temp);
        cardLibraryEnum.getTvObservableList().setAll(cardLibraryEnum.getLibrary());
        System.out.println("list size: " + cardLibraryEnum.size());
        //cardLibraryEnum.add(temp);


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

        cbRarity.getItems().setAll(Rarity.values());
        cbRarity.setPromptText("Choose rarity");
        btnAdd.setOnAction(new EventHandler<>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("inside add button");
                addCardToLib(actionEvent);
                clear(actionEvent);


                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();

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
