package io.sbelkin.codenames.engine;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.sbelkin.codenames.model.Game;
import io.sbelkin.codenames.model.WordCardType;
import org.springframework.stereotype.Component;

@Component
public class AccessRound {

    private Multimap<Integer, Game> gameHistory = ArrayListMultimap.create();

    public Game put(Integer id, String wordsGuess)  {
        Game game = getLatest(id);
        WordCardType[][] layout = game.getLayout();
        for (int i = 0;  i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                WordCardType wordCardType = layout[i][j];
                if (wordCardType.getWord() == wordsGuess) {
                    wordCardType.setGuessed(true);
                    layout[i][j] = wordCardType;
                    break;
                }
            }
        }
        gameHistory.put(id, new Game.Builder().withId(id).withLayout(layout).build());
        return game;
    }

    public Game getGame(Integer id)  {
        return getLatest(id);
    }

    public Game getLatest(Integer id) {
        int count = gameHistory.get(id).size();
        return gameHistory.get(id).stream().skip(count-1).findFirst().get();
    }
}
