package com.dl.demo;

public interface CardCollectionControl {
    int size();

    void add(Card card);

    void remove(String elmName);
    void clear();

    //TODO
    //public void editElement()

    void show();
    void showFullView();

    void saveToJson();

    void loadFromJason();

}