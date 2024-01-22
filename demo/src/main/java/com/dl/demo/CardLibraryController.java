package com.dl.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CardLibraryController {

    @FXML
    private TableView<Card> tvCardList;
    @FXML
    private TableColumn<Card, String> colCardName;
    @FXML
    private TableColumn<Card, String> colCardType;
    @FXML
    private TableColumn<Card, String> colCardManaCost;
    @FXML
    private TableColumn<Card, Rarity> colCardRarity;


    private CardLibraryEnum cardLibraryEnum = CardLibraryEnum.INSTANCE;

    @FXML
    public void initialize(){
        colCardName.setCellValueFactory(new PropertyValueFactory<Card, String>("cardName"));
        colCardType.setCellValueFactory(new PropertyValueFactory<Card, String>("cardType"));
        colCardManaCost.setCellValueFactory(new PropertyValueFactory<Card, String>("manaCost"));
        colCardRarity.setCellValueFactory(new PropertyValueFactory<Card, Rarity>("rarity"));
        tvCardList.setItems(FXCollections.observableList(cardLibraryEnum.getLibrary()));
    }
}