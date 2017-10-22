package eurditebattles.builders;

import eurditebattles.abstracts.FirebaseFactory;
import eurditebattles.models.GameEruditeModel;

public class GameEruditeBuilder extends FirebaseFactory {

    public GameEruditeBuilder(String gameId) {
        this.parentResource = "gameErudites/" + gameId;
        this.generateKey();

        GameEruditeModel erudite = new GameEruditeModel();
        erudite.setIsPlayer(true);
        erudite.setHealth(1000);
        erudite.setHealthRemaining(1000);
        erudite.setAttack(300);
        erudite.setType("Logic");

        save(erudite);
    }
}
