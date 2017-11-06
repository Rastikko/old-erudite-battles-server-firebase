package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
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

    public void handle(DataSnapshot commandDataSnapshot) {
        GameCommandModel commandModel = commandDataSnapshot.getValue(GameCommandModel.class);
        if (commandModel.resolved) {
            return;
        }
        System.out.println("EBS - Executing command: " + commandModel.gameCommandType);
        AbstractCommand command;
        if (commandModel.gameCommandType.equals(GameCommandsTypes.DRAW_CARD.name())) {
            command = new DrawCardCommand(commandDataSnapshot, gameCommandHandler, gamePlayerHandler);
        } else {
            System.out.println("EBS ERROR - Commander cannot handle command: " + commandModel.gameCommandType);
            return;
        }
        command.execute();
        command.resolve();
    }
}
