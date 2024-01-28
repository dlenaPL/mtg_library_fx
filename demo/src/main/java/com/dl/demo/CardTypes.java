package com.dl.demo;

public enum CardTypes {
    LAND("Land"),
    SPELL("Spell"),
    CREATURE("Creature");

    private final String cardType;
    CardTypes(String cardType) {
        this.cardType = cardType;
    }
    public String getValue() {
        return cardType;
    }
    @Override
    public String toString(){
        return String.valueOf(cardType);
    }
}
