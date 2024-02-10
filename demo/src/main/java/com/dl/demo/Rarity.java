package com.dl.demo;

public enum Rarity {
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    MYTHIC_RARE("Mythic rare"),
    DEFAULT("Default");
    private final String value;
    Rarity(String rarity) {
        this.value = rarity;
    }
    public String getValue() {
        return value;
    }
    @Override
    public String toString(){
        return String.valueOf(value);
    }

}
