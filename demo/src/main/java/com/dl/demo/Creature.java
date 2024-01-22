package com.dl.demo;

public class Creature extends Spell{
    private int power;
    private int toughness;

    public Creature() {
        this.power = 0;
        this.toughness = 0;
    }

    public Creature(String cardName, String cardType, String expansionSet, Rarity rarity, String manaCost, String cardText, int power, int toughness) {
        super(cardName, cardType, expansionSet, rarity, manaCost, cardText);
        this.power = power;
        this.toughness = toughness;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    @Override
    public String toString(){
        return  "\nCard name: " + this.getCardName() +
                "\nMana cost: " + this.getManaCost() +
                "\nType: " + this.getCardType() +
                "\nCard text: " + this.getCardText() +
                "\nP/T: " + this.power + "/" + this.toughness +
                "\nExpansions: " + this.getExpansionSet() +
                "\nRarity: " + this.getRarity();
    }
}
