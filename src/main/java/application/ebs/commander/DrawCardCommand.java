package application.ebs.commander;

import application.ebs.handlers.CommandHandler;
import application.ebs.handlers.GameHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GamePlayerModel;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
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
        DataSnapshot gamePlayerDataSnapshot = gamePlayerHandler.getSubresourceDataSnapshot(command.gamePlayer);
        GamePlayerModel player = gamePlayerDataSnapshot.getValue(GamePlayerModel.class);
        System.out.println(player);

        Map.Entry<String,Boolean> newCard = player.deckCards.entrySet().iterator().next();

        player.deckCards.remove(newCard.getKey());
        player.handCards.put(newCard.getKey(), true);


        Map<String, Object> playerUpdate = new HashMap<>();
        playerUpdate.put("deckCards", player.deckCards);
        playerUpdate.put("handCards", player.handCards);

        gamePlayerHandler.updateSubresource(gamePlayerDataSnapshot.getKey(), playerUpdate);

        // TODO
        // Update gamePhase commands to add this guy -> always
        // Resolve the command -> always
    }
}
