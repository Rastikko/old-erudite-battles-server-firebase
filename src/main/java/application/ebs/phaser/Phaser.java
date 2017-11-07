package application.ebs.phaser;

import application.ebs.handlers.GameHandler;
import application.ebs.handlers.GamePhaseHandler;
import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Phaser {

    @Autowired
    GameHandler gameHandler;
//    @Autowired
//    GamePhaseHandler gamePhaseHandler;

    public void setPhaseModel(DataSnapshot phaseDataSnapshot) {
        Phase state;

        GamePhaseModel phaseModel = phaseDataSnapshot.getValue(GamePhaseModel.class);
        GamePhaseTypes phaseType = GamePhaseTypes.valueOf(phaseModel.gamePhaseType);

        if (phaseModel.resolved) {
            System.out.println("EBS - PHASE ALREADY RESOLVED: " + phaseType);
            return;
        }

        switch (phaseType) {
            case INITIAL_DRAW:
                state = new IntialDrawPhase(this, phaseDataSnapshot);
                break;
            default:
                System.out.println("EBS ERROR - gamePhaseType not found in Phaser: " + phaseType);
                return;
        }
        state.checkPhase();
        state.checkBotCommands();
    }
}
