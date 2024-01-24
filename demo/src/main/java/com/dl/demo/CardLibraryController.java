package com.dl.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
//    @FXML
//    private TableColumn colActions;


    private CardLibraryEnum cardLibraryEnum = CardLibraryEnum.INSTANCE;
    private ObservableList<Card> tvObservableList = FXCollections.observableList(cardLibraryEnum.getLibrary());

    @FXML
    public void initialize(){
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
        tvCardList.setItems(tvObservableList);
        //tvCardList.getColumns().addAll(colCardName, colCardType, colCardManaCost, colCardRarity);

        addShowBtnToTable();
        addShowDeleteBtnToTable();
        addEditBtnToColumn();
    }

    private void addShowBtnToTable(){
        TableColumn<Card, Void> colShowBtn = new TableColumn<>("Show");
        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
            @Override
            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {

                final TableCell<Card, Void> cell = new TableCell<>() {
                    private final Button btnShow = new Button("Show");

                    {
                        btnShow.setOnAction((ActionEvent event) -> {
                            Card card = getTableView().getItems().get(getIndex());
                            System.out.println("selected data: " + card.getCardName());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnShow);
                        }
                    }
                };
                return cell;
            }
        };
        colShowBtn.setCellFactory(cellFactory);
        tvCardList.getColumns().add(colShowBtn);
    }

    private void addEditBtnToColumn(){
        TableColumn<Card, Void> colEditBtn = new TableColumn<>("Edit");
        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
            @Override
            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {

                final TableCell<Card, Void> cell = new TableCell<>() {
                    private final Button btnEdit = new Button("Edit");

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            Card card = getTableView().getItems().get(getIndex());
                            System.out.println("Edit data: " + card.getCardName());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnEdit);
                        }
                    }
                };
                return cell;
            }
        };
        colEditBtn.setCellFactory(cellFactory);
        tvCardList.getColumns().add(colEditBtn);
    }

    private void addShowDeleteBtnToTable(){
        TableColumn<Card, Void> colDeleteBtn = new TableColumn<>("Delete");
        Callback<TableColumn<Card, Void>, TableCell<Card, Void>> cellFactory = new Callback<TableColumn<Card, Void>, TableCell<Card, Void>>() {
            @Override
            public TableCell<Card, Void> call(final TableColumn<Card, Void> param) {

                final TableCell<Card, Void> cell = new TableCell<>() {
                    private final Button btnDelete = new Button("Delete");

                    {
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Card card = getTableView().getItems().get(getIndex());
                            System.out.println("Delete data: " + card.getCardName());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnDelete);
                        }
                    }
                };
                return cell;
            }
        };
        colDeleteBtn.setCellFactory(cellFactory);
        tvCardList.getColumns().add(colDeleteBtn);
    }


}