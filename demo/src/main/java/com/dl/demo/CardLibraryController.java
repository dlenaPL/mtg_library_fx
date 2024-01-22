package com.dl.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardLibraryController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}