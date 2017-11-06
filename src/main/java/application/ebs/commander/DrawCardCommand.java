package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.handlers.GamePlayerHandler;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GamePlayerModel;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class DrawCardCommand extends AbstractCommand {

    GamePlayerHandler gamePlayerHandler;

    public DrawCardCommand(DataSnapshot gameCommandDataSnapshot, GameCommandHandler  gameCommandHandler, GamePlayerHandler gamePlayerHandler) {
        super(gameCommandDataSnapshot, gameCommandHandler);
        this.gamePlayerHandler = gamePlayerHandler;
    }

    @Override
    public void execute() {
        GameCommandModel command = gameCommandDataSnapshot.getValue(GameCommandModel.class);
        DataSnapshot gamePlayerDataSnapshot = gamePlayerHandler.getSubresourceDataSnapshot(command.gamePlayer);
        GamePlayerModel player = gamePlayerDataSnapshot.getValue(GamePlayerModel.class);

        if (player.deckCards.entrySet().size() == 0) {
            System.out.println("EBS INFO - No more card left in deck returning from DrawCardCommand");
            return;
        }

        Map.Entry<String,Boolean> newCard = player.deckCards.entrySet().iterator().next();

        player.deckCards.remove(newCard.getKey());
        player.handCards.put(newCard.getKey(), true);

        Map<String, Object> playerUpdate = new HashMap<>();
        playerUpdate.put("deckCards", player.deckCards);
        playerUpdate.put("handCards", player.handCards);

        gamePlayerHandler.updateSubresource(gamePlayerDataSnapshot.getKey(), playerUpdate);
    }
}
