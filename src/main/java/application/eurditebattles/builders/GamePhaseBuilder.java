package application.eurditebattles.builders;

import application.eurditebattles.models.GameModel;
import application.eurditebattles.models.GamePhaseModel;

import java.util.HashMap;
import java.util.Map;

public class GamePhaseBuilder extends AbstractBuilder {

    protected String gameId;
    // right now we just default a match versus bots
    public GamePhaseBuilder(String gameId, String gamePhaseType) {
        this.parentResource = "phases";
        this.generateKey();

        GamePhaseModel phase = new GamePhaseModel();
        phase.gamePhaseType = gamePhaseType;

        save(phase);
    }
}
