package com.dl.demo;

public class Spell extends Land{
    private String manaCost;
    private String cardText;

    public Spell() {
        this.manaCost = "";
        this.cardText = "";
    }

    public Spell(String cardName, String cardType, String expansionSet, Rarity rarity, String manaCost, String cardText) {
        super(cardName, cardType, expansionSet, rarity);
        this.manaCost = manaCost;
        this.cardText = cardText;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    @Override
    public String toString(){
        return  "\nCard name: " + this.getCardName() +
                "\nMana cost: " + this.manaCost +
                "\nType: " + this.getCardType() +
                "\nCard text: " + this.cardText +
                "\nExpansions: " + this.getExpansionSet() +
                "\nRarity: " + this.getRarity();
    }
}
