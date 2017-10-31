package application.ebs.builders;

import application.ebs.models.GamePlayerModel;
import application.ebs.types.BattleTypes;

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

        GamePlayerModel gamePlayer = new GamePlayerModel();
        gamePlayer.eruditeName = eruditeName;
        gamePlayer.baseAttack = baseAttack;
        gamePlayer.baseBarrier = baseBarrier;
        gamePlayer.baseHealth = baseHealth;
        gamePlayer.damageTaken = damageTaken;
        gamePlayer.energy = energy;
        gamePlayer.battleType = battleType;
        gamePlayer.deckCards = deckCards;
        gamePlayer.player = playerId;
        gamePlayer.game = gameId;

        save(gamePlayer);
    }

}
