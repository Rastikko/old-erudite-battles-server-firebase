package application.ebs.phaser;

import application.ebs.builders.GamePhaseBuilder;
import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;

import java.util.HashMap;
import java.util.Map;

public class IntialDrawPhase extends AbstractPhase implements Phase {

    public IntialDrawPhase(Phaser phaser, GamePhaseModel gamePhaseModel) {
        super(phaser, gamePhaseModel);
    }

    @Override
    public void checkPhase() {
        System.out.println("EBS - Checking phase " + gamePhaseModel);

        // TODO: should be 2 players
        // TODO: also we should compare against a timestap, and if it timeout resolve commmands for the user and recheck in 100ms
        if (gamePhaseModel.phaseEndGamePlayers.size() > 0) {
            GamePhaseBuilder newGamePhase = new GamePhaseBuilder(gamePhaseModel.game, GamePhaseTypes.GATHER_RESOURCES.name());

            Map<String, Object> gameUpdate = new HashMap<>();
            gameUpdate.put("gamePhase", newGamePhase.getReference().getKey());

            this.phaser.gameHandler.updateSubresource(this.gamePhaseModel.game, gameUpdate);
        }
    }

    @Override
    public void checkBotCommands() {

    }
}
