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
        Integer attack = 200;
        Integer barrier = 100;
        Integer health = 500;
        Integer totalHealth = 500;
        Integer energy = 0;
        String battleType = BattleTypes.FACTORIZATION.name();

        deckCards.put("Aristoteles", true);
        deckCards.put("Socrates", true);

        GamePlayerModel gamePlayer = new GamePlayerModel();
        gamePlayer.eruditeName = eruditeName;
        gamePlayer.attack = attack;
        gamePlayer.barrier = barrier;
        gamePlayer.health = health;
        gamePlayer.totalHealth = totalHealth;
        gamePlayer.energy = energy;
        gamePlayer.battleType = battleType;
        gamePlayer.deckCards = deckCards;
        gamePlayer.player = playerId;
        gamePlayer.game = gameId;

        save(gamePlayer);
    }

}
