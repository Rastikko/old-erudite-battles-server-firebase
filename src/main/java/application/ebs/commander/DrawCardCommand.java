package application.ebs.commander;

import application.ebs.handlers.CommandHandler;
import application.ebs.handlers.GameHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GamePlayerModel;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class DrawCardCommand extends AbstractCommand {

    @Autowired
    GamePlayerHandler gamePlayerHandler;

    public DrawCardCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void execute(GameCommandModel command) {
        GamePlayerModel player = gamePlayerHandler.getSubresourceDataSnapshot(command.gamePlayer).getValue(GamePlayerModel.class);
        System.out.println(player);
        // Update gamePhase commands to add this guy -> always
        // Resolve the command -> always
    }
}
