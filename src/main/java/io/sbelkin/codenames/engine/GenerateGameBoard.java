package io.sbelkin.codenames.engine;

import io.sbelkin.codenames.exception.InvalidBoardGameInput;
import io.sbelkin.codenames.model.CardType;
import io.sbelkin.codenames.model.Game;
import io.sbelkin.codenames.model.WordCardType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static io.sbelkin.codenames.model.CardType.Red;
import static io.sbelkin.codenames.model.CardType.Blue;
import static io.sbelkin.codenames.model.CardType.Bystander;
import static io.sbelkin.codenames.model.CardType.Assassin;

@Component
public class GenerateGameBoard {

    @Value("${file.words}")
    private String fileWords;

    @Value("${file.layout}")
    private String fileLayout;

    private List<String> words;

    public void init() throws IOException {
        words = getWords(fileWords);
        System.out.println("Ready!");
    }

    public Game generateDefault() throws InvalidBoardGameInput {
        // using default of +1 for redSpies for now.
        return generate(5, 5 , 7, 8, 9, 1);
    }

    public Game generate(int row, int column, int bystanders, int blueSpy, int redSpy, int assassin) throws InvalidBoardGameInput {
        int boardSize = row * column;
        int cardCount = bystanders + blueSpy + redSpy + assassin;
        if (boardSize != cardCount) {
            // cannot support.
            throw new InvalidBoardGameInput(String.format("Board row: %d column: %d doesn't mathc card count: %d", row, column, cardCount));
        }
        Game.Builder game = new Game.Builder()
                .withId(1);

        List<String> wordList = getListWords(cardCount);
        CardType[][] cardTypes = generateLayoutDefault();
        int count = 0;
        for (int cardRow = 0; cardRow < cardTypes.length; cardRow++) {
            for (int cardColumn = 0; cardColumn < cardTypes[cardRow].length; cardColumn++) {
                game.addWordCardType(new WordCardType(wordList.get(count),cardTypes[cardRow][cardColumn]), cardRow,cardColumn);
                count++;
            }
        }
        return  game.build();
    }


    public List<String> getListWords(int boardSize) {
        // For now just take the top words in list.
        // List<Integer> index = new ArrayList<>();
        List<String> selectedWords = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            selectedWords.add(words.get(i));
        }
        return selectedWords;
    }

    public CardType[][] generateLayoutDefault() {
        CardType[][] array = {
            {Red, Red, Bystander, Blue, Red},
            {Blue, Red, Assassin, Bystander, Blue},
            {Bystander, Bystander, Bystander, Blue, Blue},
            {Red, Red, Blue, Red, Red},
            {Blue, Bystander, Blue, Blue, Bystander}
        };
        return array;
    }

    public CardType[][] generateLayout(int row, int column, int bystanders, int blueSpy, int redSpy, int assassin) {
        CardType[][] array = new CardType[row][column];
        int count = row*column;
        while (0 < count) {
            CardType cardType = CardType.random();

            count--;
        }
        return array;
    }

    private CardType getCardTypeWithinRequirements(int bystanders, int blueSpy, int redSpy, int assassin){
        CardType cardType = CardType.random();
        // decide a better way to generate random with in the range?
        if (bystanders == 0 && cardType.equals(Bystander)){
            return getCardTypeWithinRequirements(bystanders,blueSpy,redSpy,assassin);
        }
        return cardType;
    }

    private List<String> getWords(String file) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        return list;
    }
}

