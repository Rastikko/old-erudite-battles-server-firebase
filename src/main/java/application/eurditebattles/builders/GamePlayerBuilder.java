package application.eurditebattles.builders;

import application.eurditebattles.models.GamePlayerModel;
import application.eurditebattles.types.BattleTypes;

import java.util.HashMap;
import java.util.Map;

public class GamePlayerBuilder extends AbstractBuilder {

    public GamePlayerBuilder(String gameId, String playerId) {
        this.parentResource = "gamePlayers";
        this.generateKey();

        Map<String, Boolean> deckCards = new HashMap<>();

        String eruditeName = "Peter Shor";
        Integer baseAttack = 200;
        Integer baseBarrier = 100;
        Integer baseHealth = 500;
        Integer damageTaken = 0;
        Integer energy = 1;
        String battleType = BattleTypes.FACTORIZATION.name();

        deckCards.put("1", true);
        deckCards.put("2", true);

        Map<String, String> gameReference = new HashMap<>();
        gameReference.put("id", gameId);
        gameReference.put("type", "game");


        Map<String, String> playerReference = new HashMap<>();
        gameReference.put("id", playerId);
        gameReference.put("type", "player");

        GamePlayerModel gamePlayer = new GamePlayerModel();
        gamePlayer.eruditeName = eruditeName;
        gamePlayer.baseAttack = baseAttack;
        gamePlayer.baseBarrier = baseBarrier;
        gamePlayer.baseHealth = baseHealth;
        gamePlayer.damageTaken = damageTaken;
        gamePlayer.energy = energy;
        gamePlayer.battleType = battleType;
        gamePlayer.deckCards = deckCards;
        gamePlayer.player = gameReference;
        gamePlayer.game = playerReference;

        save(gamePlayer);
    }

}
