package com.dl.demo;

public class Land extends Card{
    private String cardName;
    private String cardType;
    private String expansionSet;
    private Rarity rarity;
    public Land() {
        super();
    }
    public Land(String cardName, String cardType, String expansionSet, Rarity rarity) {
        super(cardName, cardType, expansionSet, rarity);
    }

}
