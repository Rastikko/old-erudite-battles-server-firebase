package application.eurditebattles.builders;

import application.eurditebattles.models.GamePlayerModel;

import java.util.HashMap;
import java.util.Map;

public class GamePlayerBuilder extends AbstractBuilder {

    public GamePlayerBuilder(String gameId, String playerId) {
        this.parentResource = "gamePlayers";
        this.generateKey();

        Map<String, Boolean> deck = new HashMap<>();
        Map<String, Boolean> hand = new HashMap<>();
        Map<String, Boolean> erudites = new HashMap<>();
        Integer energy = 1;

        // TODO: the deck should be distributing an erudie every 4 cards
        for (int i = 0; i < 10; i++) {
            deck.put("1", true);
        }

        GameEruditeBuilder erudite = new GameEruditeBuilder(this.reference.getKey());
        erudites.put(erudite.getReference().getKey(), true);

        GamePlayerModel gamePlayer = new GamePlayerModel();
        gamePlayer.player = playerId;
        gamePlayer.deckCards = deck;
        gamePlayer.handCards = hand;
        gamePlayer.gameErudites = erudites;
        gamePlayer.energy = energy;
        gamePlayer.game = gameId;

        save(gamePlayer);
    }

}
