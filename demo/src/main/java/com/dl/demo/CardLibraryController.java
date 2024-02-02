package com.dl.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class CardLibraryController {


    public TableView<Card> getTvCardList() {
        return tvCardList;
    }

    @FXML
    private TableView<Card> tvCardList = new TableView<>();
    @FXML
    private TableColumn<Card, String> colCardName = new TableColumn<>();
    @FXML
    private TableColumn<Card, String> colCardType = new TableColumn<>();
    @FXML
    private TableColumn<Card, String> colCardManaCost = new TableColumn<>();
    @FXML
    private TableColumn<Card, Rarity> colCardRarity = new TableColumn<>();
    @FXML
    private Button btnAddCard = new Button("Add");
    @FXML
    private Button btnDeleteCard = new Button("Delete");
    @FXML
    private Button btnEditCard = new Button("Edit");
    @FXML
    private Button btnImport = new Button("Import");
    @FXML
    private Button btnExport = new Button("Export");


    private final CardLibraryEnum cardLibraryEnum = CardLibraryEnum.INSTANCE;


    public void populateLibrary(){
        System.out.println("fired populate");
        colCardName.setCellValueFactory(new PropertyValueFactory<Card, String>("cardName"));
        colCardType.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));
        colCardManaCost.setCellValueFactory(cellData->{
            Card card = cellData.getValue();
            //check the lower child in hierarchy first
            if(cellData.getValue() instanceof Spell)
                return new SimpleStringProperty(((Spell)card).getManaCost());
            else
                return new SimpleStringProperty();
        });
        colCardRarity.setCellValueFactory(new PropertyValueFactory<Card, Rarity>("rarity"));
        tvCardList.setItems(cardLibraryEnum.getTvObservableList());
    }

    @FXML
    public void initialize(){

        populateLibrary();
        tvCardList.getStylesheets().add(String.valueOf(getClass().getResource("tableview.css")));

        tvCardList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && tvCardList.getSelectionModel().getSelectedItem() != null){
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("showCard.fxml"));
                        Parent parent = loader.load();

                        ShowCardController ctrl = loader.getController();

                        ctrl.initData(tvCardList.getSelectionModel().getSelectedItem());
                        System.out.println(tvCardList.getSelectionModel().getSelectedItem());

                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException e) {
                        System.out.println("file not found");
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        btnAddCard.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("add button clicked");
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("addCardScene.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("file not found");
                    throw new RuntimeException(e);
                }
            }
        });

        btnDeleteCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("delete button clicked");
                Card card = tvCardList.getSelectionModel().getSelectedItem();
                cardLibraryEnum.remove(card.getCardName());
                tvCardList.getItems().removeAll(tvCardList.getSelectionModel().getSelectedItem());
                System.out.println(cardLibraryEnum.size());
            }
        });



        btnImport.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose file: ");

                FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("JSON Files", "*.json");
                FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("All Files", "*.*");

                fileChooser.getExtensionFilters().addAll(ex1, ex2);
                fileChooser.setInitialDirectory(new File("C:/Users/48721/OneDrive/Pulpit"));

                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();

                File selectedFile = fileChooser.showOpenDialog(stage);
                if(selectedFile != null){
                    System.out.println("Opened file");
                    String path = selectedFile.getPath();
                    System.out.println(path);
                    cardLibraryEnum.loadFromJason(path);
                }
            }
        });
        btnExport.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save file: ");

                FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("JSON Files", "*.json");
                FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("All Files", "*.*");
                fileChooser.getExtensionFilters().addAll(ex1, ex2);

                fileChooser.setInitialDirectory(new File("C:/Users/48721/OneDrive/Pulpit"));

                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();

                File selectedFile = fileChooser.showSaveDialog(stage);
                if(selectedFile != null){
                    System.out.println("Save file");
                    String path = selectedFile.getPath();
                    System.out.println(path);
                    cardLibraryEnum.saveToJson(path);
                }
            }
        });

        btnEditCard.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("editCard.fxml"));
                    Parent parent = loader.load();

                    EditCardController ctrl = loader.getController();

                    ctrl.initData(tvCardList.getSelectionModel().getSelectedItem());

                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("file not found");
                    throw new RuntimeException(e);
                }
            }

        });


    }

}