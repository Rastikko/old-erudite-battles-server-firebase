package application.ebs.phaser;

import application.ebs.models.GamePhaseModel;

public abstract class AbstractPhase {
    Phaser phaser;
    GamePhaseModel gamePhaseModel;

    public AbstractPhase(Phaser phaser, GamePhaseModel gamePhaseModel) {
        this.phaser = phaser;
        this.gamePhaseModel = gamePhaseModel;
    }

    void setNewPhase(String gamePhaseType) {
        // build a new phase
        // change game phase
    }
}
