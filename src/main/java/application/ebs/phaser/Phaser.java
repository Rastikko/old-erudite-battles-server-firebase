package application.ebs.phaser;

import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;

public class Phaser {
    Phase state;
    public Phaser(GamePhaseModel phase) {
        if (phase.gamePhaseType.equals(GamePhaseTypes.INITIAL_DRAW.name())) {
            state = new IntialDrawPhase(this);
        }
    }

    public void checkPhase() {
        state.checkPhase();
    }

    public void checkBotCommands() {
        state.checkBotCommands();
    }
}
