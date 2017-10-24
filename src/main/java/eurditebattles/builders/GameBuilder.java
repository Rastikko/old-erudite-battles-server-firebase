package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GameModel;

import java.util.HashMap;
import java.util.Map;

/*
    GameBuilder()
        .addPlayer(playerId)
        .addBotPlayer()
        .setDefaultDecks()
        .setDefaultErudites()
        .build();

 */
public class GameBuilder extends FirebaseFactory {

    protected String gameId;
    // right now we just default a match versus bots
    public GameBuilder(String playerId) {
        this.parentResource = "games";
        this.generateKey();

        GamePlayerBuilder humanPlayer = new GamePlayerBuilder(this.reference.getKey(), playerId);
        GamePlayerBuilder botPlayer = new GamePlayerBuilder(this.reference.getKey(), "BOT");

        Map<String, Boolean> gamePlayers = new HashMap<>();

        gamePlayers.put(humanPlayer.getReference().getKey(), true);
        gamePlayers.put(botPlayer.getReference().getKey(), true);

        GameModel game = new GameModel();
        game.setGamePlayers(gamePlayers);

        save(game);
    }
}
