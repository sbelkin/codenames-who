package io.sbelkin.codenames.model;

import java.util.Arrays;
import java.util.List;

public class Game {

    private Integer id;
    private WordCardType[][] layout;

    public Game(Builder builder) {
        this.id = builder.id;
        this.layout = builder.layout;
    }

    public Integer getId() {
        return id;
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
        private Integer id;
        private WordCardType[][] layout;

        public Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLayout(WordCardType[][] layout) {
            this.layout = layout;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

}
