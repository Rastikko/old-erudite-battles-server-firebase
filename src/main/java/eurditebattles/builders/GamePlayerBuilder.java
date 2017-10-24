package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GamePlayerModel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GamePlayerBuilder extends FirebaseFactory {

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

        GamePlayerModel player = new GamePlayerModel();
        player.setPlayer(playerId);
        player.setDeckCards(deck);
        player.setHandCards(hand);
        player.setGameErudites(erudites);
        player.setEnergy(energy);
        player.setGame(gameId);

        save(player);
    }

}
