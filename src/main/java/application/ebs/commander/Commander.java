package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.handlers.GamePhaseHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.models.GameCommandModel;
import application.ebs.types.GameCommandsTypes;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Commander {

    @Autowired
    GamePlayerHandler gamePlayerHandler;
    @Autowired
    GameCommandHandler gameCommandHandler;
    @Autowired
    GamePhaseHandler gamePhaseHandler;

    public void handle(DataSnapshot commandDataSnapshot) {
        GameCommandModel commandModel = commandDataSnapshot.getValue(GameCommandModel.class);
        if (commandModel.resolved) {
            return;
        }
        System.out.println("EBS - Executing command: " + commandModel.gameCommandType);
        AbstractCommand command;
        GameCommandsTypes commmandType = GameCommandsTypes.valueOf(commandModel.gameCommandType);
        switch (commmandType) {
            case DRAW_CARD:
                command = new DrawCardCommand(this, commandDataSnapshot);
                break;
            case END_PHASE:
                command = new EndPhaseCommand(this, commandDataSnapshot);
                break;
            case GATHER_ENERGY:
                command = new GatherEnergyCommand(this, commandDataSnapshot);
                break;
            default:
                System.out.println("EBS ERROR - Commander cannot handle command: " + commandModel.gameCommandType);
                return;
        }
        command.execute();
        command.resolve();
    }
}
