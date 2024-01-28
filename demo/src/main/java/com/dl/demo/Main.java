package com.dl.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        stage.setOnHidden(e -> Platform.exit());
        stage.show();
    }

    public static void main(String[] args) {

        Land plains = new Land("Plains",
                "Land",
                "The Lost Caverns of Ixalan",
                Rarity.COMMON);

        Spell absAwk = new Spell("Abuelo's Awakening",
                "Sorcery",
                "The Lost Caverns of Ixalan",
                Rarity.RARE,
                "X 3 White",
                "Return target artifact or non-Aura enchantment card from your graveyard to the battlefield with X additional +1/+1 counters on it.\n" +
                        "It's a 1/1 Spirit creature with flying in addition to its other types.");

        Creature absGora = new Creature("Abyssal Gorestalker",
                "Creature — Horror",
                "The Lost Caverns of Ixalan",
                Rarity.UNCOMMON,
                "4 Black Black",
                "When Abyssal Gorestalker enters the battlefield, each player sacrifices two creatures.",
                6,
                6);
        //test
        CardLibraryEnum library = CardLibraryEnum.INSTANCE;
        library.add(absAwk);
        library.add(absGora);
        library.add(plains);

        library.show();
        library.saveToJson();
        library.clear();
        library.show();
        library.loadFromJason();
        System.out.println("size :D : " + library.getTvObservableList().size());
        System.out.println("size of list: " + library.getLibrary().size());
        library.showFullView();
        System.out.println("========================================== " + library.size());

        launch();


    }

}
