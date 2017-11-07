package application.ebs.phaser;

import application.ebs.handlers.GameHandler;
import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Phaser {

    @Autowired
    GameHandler gameHandler;

    public void setPhaseModel(GamePhaseModel phaseModel) {
        Phase state;

        GamePhaseTypes phaseType = GamePhaseTypes.valueOf(phaseModel.gamePhaseType);

        switch (phaseType) {
            case INITIAL_DRAW:
                state = new IntialDrawPhase(this, phaseModel);
                break;
            default:
                System.out.println("EBS ERROR - gamePhaseType not found in Phaser: " + phaseType);
                return;
        }
        state.checkPhase();
        state.checkBotCommands();
    }
}
