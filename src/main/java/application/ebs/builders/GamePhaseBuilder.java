package application.ebs.builders;

import application.ebs.models.GamePhaseModel;

import java.util.HashMap;
import java.util.Map;

public class GamePhaseBuilder extends AbstractBuilder {

    protected String gameId;
    // right now we just default a match versus bots
    public GamePhaseBuilder(String gameId, String gamePhaseType) {
        this.parentResource = "gamePhases";
        generateKey();

        GamePhaseModel phase = new GamePhaseModel();
        phase.gamePhaseType = gamePhaseType;
        phase.game = gameId;
        phase.resolved = false;

        save(phase);
    }
}
