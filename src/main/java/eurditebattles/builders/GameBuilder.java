package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GameModel;

import java.util.LinkedList;
import java.util.List;

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
    // just one param means that is versus bots
    public GameBuilder(String playerId) {
        this.parentResource = "games";
        this.generateKey();

        GamePlayerBuilder humanPlayer = new GamePlayerBuilder(this.reference.getKey(), playerId);
        GamePlayerBuilder botPlayer = new GamePlayerBuilder(this.reference.getKey(), "BOT");

        List<String> gamePlayersIds = new LinkedList<>();

        gamePlayersIds.add(humanPlayer.getReference().getKey());
        gamePlayersIds.add(botPlayer.getReference().getKey());

        GameModel game = new GameModel();
        game.setGamePlayersIds(gamePlayersIds);

        save(game);
    }
}
