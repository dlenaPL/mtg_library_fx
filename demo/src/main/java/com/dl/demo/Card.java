package com.dl.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({@JsonSubTypes.Type(value = Land.class, name = "Land"),
        @JsonSubTypes.Type(value = Spell.class, name = "Spell"),
        @JsonSubTypes.Type(value = Creature.class, name = "Creature")})

public abstract class Card {
    private String cardName;
    private String cardType;
    private String expansionSet;
    private Rarity rarity;

    public Card() {
        this.cardName = "";
        this.cardType = "";
        this.expansionSet = "";
        this.rarity = Rarity.DEFAULT;
    }

    public Card(String cardName, String cardType, String expansionSet, Rarity rarity) {
        this.cardName = cardName;
        this.cardType = cardType;
        this.expansionSet = expansionSet;
        this.rarity = rarity;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpansionSet() {
        return expansionSet;
    }

    public void setExpansionSet(String expansionSet) {
        this.expansionSet = expansionSet;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return  "\nCard name: " + this.cardName +
                "\nType: " + this.cardType +
                "\nExpansions: " + this.expansionSet +
                "\nRarity: " + this.rarity;
    }
}
