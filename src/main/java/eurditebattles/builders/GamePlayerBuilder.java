package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GamePlayerModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GamePlayerBuilder extends FirebaseFactory {

    public GamePlayerBuilder(String gameId, String playerId) {
        this.parentResource = "gamePlayers/" + gameId;
        this.generateKey();

        List<String> deck = new LinkedList<>();
        List<String> hand = new LinkedList<>();
        List<String> erudites = new LinkedList<>();
        Integer energy = 1;

        // TODO: the deck should be distributing an erudie every 4 cards
        for (int i = 0; i < 10; i++) {
            deck.add("1");
        }

        GameEruditeBuilder erudite = new GameEruditeBuilder(gameId);
        erudites.add(erudite.getReference().getKey());

        GamePlayerModel player = new GamePlayerModel();
        player.setPlayerId(playerId);
        player.setDeckCardIds(deck);
        player.setHandCardIds(hand);
        player.setGameEruditeIds(erudites);
        player.setEnergy(energy);

        save(player);
    }

}
