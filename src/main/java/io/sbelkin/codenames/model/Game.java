package io.sbelkin.codenames.model;

import java.util.Arrays;
import java.util.List;

public class Game {

    private Integer id;
    private WordCardType[][] layout;

    public Game() {
    }

    public Game(Builder builder) {
        this.id = builder.id;
        this.layout = builder.layout;
    }

    public Integer getId() {
        return id;
    }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLayout(WordCardType[][] layout) {
    this.layout = layout;
  }

  public WordCardType[][] getLayout() {
        return layout;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", layout=" + Arrays.toString(layout) +
                '}';
    }

    public static class Builder {
        private Integer defaultPlay = 5;
        private Integer id;
        private WordCardType[][] layout = new WordCardType[defaultPlay][defaultPlay];

        public Builder() {
        }

        public Builder(Integer row, Integer column) {
            layout = new WordCardType[row][column];;
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLayout(WordCardType[][] layout) {
            this.layout = layout;
            return this;
        }

        public Builder addWordCardType(WordCardType type, Integer column, Integer row) {
            this.layout[row][column] = type;
            return this;
        }
        public Game build() {
            return new Game(this);
        }
    }

}
