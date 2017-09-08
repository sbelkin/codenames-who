package io.sbelkin.codenames.model;

public class WordCardType {

    private String word;
    private CardType cardType;
    private boolean guessed = false;

    public WordCardType(String word, CardType cardType) {
        this.word = word;
        this.cardType = cardType;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    @Override
    public String toString() {
        return "WordCardType{" +
                "word='" + word + '\'' +
                ", cardType=" + cardType +
                ", guessed=" + guessed +
                '}';
    }
}
