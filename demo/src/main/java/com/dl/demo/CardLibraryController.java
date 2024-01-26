package com.dl.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CardLibraryController {


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


        btnAddCard.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("add button clicked");
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("addCardScene.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
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
                tvCardList.getItems().removeAll(tvCardList.getSelectionModel().getSelectedItem());
            }
        });








//        colCardName.setCellValueFactory(new PropertyValueFactory<Card, String>("cardName"));
//        colCardType.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));
//        colCardManaCost.setCellValueFactory(cellData->{
//            Card card = cellData.getValue();
//            //check the lower child in hierarchy first
//            if(cellData.getValue() instanceof Spell)
//                return new SimpleStringProperty(((Spell)card).getManaCost());
//            else
//                return new SimpleStringProperty();
//        });
//        colCardRarity.setCellValueFactory(new PropertyValueFactory<Card, Rarity>("rarity"));
//        tvCardList.setItems(tvObservableList);
//        //tvCardList.getColumns().addAll(colCardName, colCardType, colCardManaCost, colCardRarity);






        //@TODO
        //maybe there is more simple solution for this ? hm...
//        addShowBtnToTable();
//        addShowDeleteBtnToTable();
//        addEditBtnToColumn();
    }


//    private void addShowBtnToTable(){
//        TableColumn<Card, Void> colShowBtn = new TableColumn<>("Show");
//        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
//            @Override
//            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {
//
//                final TableCell<Card, Void> cell = new TableCell<>() {
//                    private final Button btnShow = new Button();
//                    {
//                        ImageView view = new ImageView(new Image("C:\\Users\\48721\\OneDrive\\Pulpit\\java_projects\\demo\\src\\main\\resources\\images\\show_btn.png"));
//                        view.setFitHeight(16);
//                        view.setPreserveRatio(true);
//                        btnShow.setGraphic(view);
//                        //btnShow.setContentDisplay(ContentDisplay.TOP);
//
//                    }
//
//                    {
//                        btnShow.setOnAction((ActionEvent event) -> {
//                            Card card = getTableView().getItems().get(getIndex());
//                            System.out.println("selected data: " + card.getCardName());
//                        });
//                    }
//
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btnShow);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        colShowBtn.setCellFactory(cellFactory);
//        tvCardList.getColumns().add(colShowBtn);
//    }
//
//    private void addEditBtnToColumn(){
//        TableColumn<Card, Void> colEditBtn = new TableColumn<>("Edit");
//        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
//            @Override
//            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {
//
//                final TableCell<Card, Void> cell = new TableCell<>() {
//                    private final Button btnEdit = new Button("Edit");
//
//                    {
//                        btnEdit.setOnAction((ActionEvent event) -> {
//                            Card card = getTableView().getItems().get(getIndex());
//                            System.out.println("Edit data: " + card.getCardName());
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btnEdit);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        colEditBtn.setCellFactory(cellFactory);
//        tvCardList.getColumns().add(colEditBtn);
//    }
//
//    private void addShowDeleteBtnToTable(){
//        TableColumn<Card, Void> colDeleteBtn = new TableColumn<>("Delete");
//        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
//            @Override
//            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {
//
//                final TableCell<Card, Void> cell = new TableCell<>() {
//                    private final Button btnDelete = new Button("Delete");
//
//                    {
//                        btnDelete.setOnAction((ActionEvent event) -> {
//                            Card card = getTableView().getItems().get(getIndex());
//                            System.out.println("Delete data: " + card.getCardName());
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btnDelete);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        colDeleteBtn.setCellFactory(cellFactory);
//        tvCardList.getColumns().add(colDeleteBtn);
    //}


}