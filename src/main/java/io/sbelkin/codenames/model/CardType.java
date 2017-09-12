package io.sbelkin.codenames.model;

import java.util.Random;

public enum CardType {
    Red,
    Blue,
    Bystander,
    Assassin;

    private CardType[] cardTypes = CardType.values();
    private Random random = new Random();
    public static final CardType random() {
        return cardTypes[random.nextInt(cardTypes.length)];
    }
}
