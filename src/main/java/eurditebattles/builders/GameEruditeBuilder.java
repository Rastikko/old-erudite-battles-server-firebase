package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GameEruditeModel;

public class GameEruditeBuilder extends FirebaseFactory {

    public GameEruditeBuilder(String gamePlayer) {
        this.parentResource = "gameErudites";
        this.generateKey();

        GameEruditeModel erudite = new GameEruditeModel();
        erudite.setIsMainErudite(true);
        erudite.setHealth(1000);
        erudite.setHealthRemaining(1000);
        erudite.setAttack(300);
        erudite.setType("Logic");
        erudite.setGamePlayer(gamePlayer);

        save(erudite);
    }
}
