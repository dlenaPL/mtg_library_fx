package com.dl.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public enum CardLibraryEnum implements CardCollectionControl {
    INSTANCE;
    private ArrayList<Card> library;
    private ObservableList<Card> tvObservableList;
    CardLibraryEnum(){
        this.library = new ArrayList<>();
        tvObservableList = FXCollections.observableList(this.library);
    }

    public ArrayList<Card> getLibrary() {
        return library;
    }
    public ObservableList<Card> getTvObservableList() {
        return tvObservableList;
    }

    // INTERFACE METHODS
    public int size(){
        return library.size();
    }
    public void add(Card card){
        this.library.add(card);
    }
    public void remove(String cardName){
        this.library.removeIf(card -> Objects.equals(card.getCardName().strip().toLowerCase(), cardName.strip().toLowerCase()));
    }
    public void clear(){
        this.library.clear();
    }

    //TODO
    //public void editCard(Card card){}

    public void show(){
        this.library.forEach((card) -> System.out.println(card.getCardName()));
    }
    public void showFullView(){
        System.out.println(this.library);
    }
    public void saveToJson(){

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // due to type erasure I need to transform arraylist to array of object not to lose JsonSubTypes property from Json String
            Card[] tempLib = this.library.toArray(new Card[0]);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/Json/card_library.json"), tempLib);
            System.out.println("Saved to JSON");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToJson(String path){

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // due to type erasure I need to transform arraylist to array of object not to lose JsonSubTypes property from Json String
            Card[] tempLib = this.library.toArray(new Card[0]);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), tempLib);
            System.out.println("Saved to JSON");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJason(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.library = mapper.readValue(new File("src/main/java/Json/card_library.json"), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Card.class));
            System.out.println("Loaded from JSON");
            this.tvObservableList.setAll(this.library);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJason(String path){
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.library = mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Card.class));
            System.out.println("Loaded from JSON");
            this.tvObservableList.setAll(this.library);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
