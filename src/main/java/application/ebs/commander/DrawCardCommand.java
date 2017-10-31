package application.ebs.commander;

import application.ebs.handlers.CommandHandler;
import application.ebs.handlers.GameHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.models.GameCommandModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrawCardCommand extends AbstractCommand {

    @Autowired
    GamePlayerHandler gamePlayerHandler;

    public DrawCardCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void execute(GameCommandModel command) {
        // GamePlayer update draw card
        gamePlayerHandler.drawCard(command.gamePlayer);
        // Update gamePhase commands to add this guy -> always
        // Resolve the command -> always
    }
}
