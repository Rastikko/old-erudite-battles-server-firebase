package application.ebs.phaser;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.handlers.GameHandler;
import application.ebs.handlers.GamePhaseHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.handlers.PlayerHandler;
import application.ebs.models.GamePhaseModel;
import application.ebs.types.GamePhaseTypes;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class Phaser {

    @Autowired
    GameHandler gameHandler;
    @Autowired
    GamePhaseHandler gamePhaseHandler;
    @Autowired
    GameCommandHandler gameCommandHandler;
    @Autowired
    GamePlayerHandler gamePlayerHandler;
    @Autowired
    PlayerHandler playerHandler;

    void checkPhase(String phaseKey) {
        Phase state;

        DataSnapshot phaseDataSnapshot = gamePhaseHandler.getSubresourceDataSnapshot(phaseKey);
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
//        state.checkBotCommands();
    }

    public void checkBot(String phaseKey) {
        Phase state;

        DataSnapshot phaseDataSnapshot = gamePhaseHandler.getSubresourceDataSnapshot(phaseKey);
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

        state.checkBotCommands();
    }

    public void processPhase(String phaseKey) {
        // TODO: fix the race condition
        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                checkPhase(phaseKey);
            }
        }, 1000);
    }
}
