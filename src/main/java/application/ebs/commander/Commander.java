package application.ebs.commander;

import application.ebs.handlers.CommandHandler;
import application.ebs.models.GameCommandModel;
import application.ebs.types.GameCommandsTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Commander {

    @Autowired
    DrawCardCommand drawCardCommand;

    public void exectuteCommand(GameCommandModel command) {
        // TODO
        System.out.println("EBS - Executing command: " + command.gameCommandType);
        if (command.gameCommandType.equals(GameCommandsTypes.DRAW_CARD.name())) {
            drawCardCommand.execute(command);
        }
    }
}
