package application.ebs.phaser;

import application.ebs.models.GamePhaseModel;
import com.google.firebase.database.DataSnapshot;

public abstract class AbstractPhase {
    Phaser phaser;
    GamePhaseModel gamePhaseModel;
    DataSnapshot gamePhaseDataSnapshot;

    public AbstractPhase(Phaser phaser, DataSnapshot gamePhaseDataSnapshot) {
        this.phaser = phaser;
        this.gamePhaseDataSnapshot = gamePhaseDataSnapshot;
        this.gamePhaseModel = gamePhaseDataSnapshot.getValue(GamePhaseModel.class);
    }

    void setNewPhase(String gamePhaseType) {
        // build a new phase
        // change game phase
    }
}
