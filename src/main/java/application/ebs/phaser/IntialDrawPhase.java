package application.ebs.phaser;

import application.ebs.builders.GamePhaseBuilder;
import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

public class IntialDrawPhase extends AbstractPhase implements Phase {

    public IntialDrawPhase(Phaser phaser, DataSnapshot gamePhaseDataSnapshot) {
        super(phaser, gamePhaseDataSnapshot);
    }

    @Override
    public void checkPhase() {
        System.out.println("EBS - Checking phase " + this.gamePhaseModel);

        // TODO: should be 2 players
        // TODO: also we should compare against a timestap, and if it timeout resolve commmands for the user and recheck in 100ms
        if (gamePhaseModel.phaseEndGamePlayers.size() > 0) {
            GamePhaseBuilder newGamePhase = new GamePhaseBuilder(this.gamePhaseModel.game, GamePhaseTypes.GATHER_RESOURCES.name());

            Map<String, Object> gameUpdate = new HashMap<>();
            gameUpdate.put("gamePhase", newGamePhase.getReference().getKey());

            Map<String, Object> phaseUpdate = new HashMap<>();
            phaseUpdate.put("resolved", true);

            this.phaser.gameHandler.updateSubresource(this.gamePhaseModel.game, gameUpdate);
            this.phaser.gamePhaseHandler.updateSubresource(this.gamePhaseDataSnapshot.getKey(), phaseUpdate);
        }
    }

    @Override
    public void checkBotCommands() {

    }
}
