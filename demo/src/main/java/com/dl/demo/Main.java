package com.dl.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends  Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cardLibrary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MTG LIBRARY");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnHidden(e -> Platform.exit());
        stage.show();
    }

    public static void main(String[] args) {
        CardLibraryEnum lb = CardLibraryEnum.INSTANCE;
        lb.loadFromJason();

        launch();
    }

}
