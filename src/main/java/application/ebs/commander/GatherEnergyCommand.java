package application.ebs.commander;

import application.ebs.models.GameCommandModel;
import application.ebs.models.GamePlayerModel;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

public class GatherEnergyCommand extends AbstractCommand {

    public GatherEnergyCommand(Commander commander, DataSnapshot gameCommandDataSnapshot) {
        super(commander, gameCommandDataSnapshot);
    }

    @Override
    public void execute() {
        GameCommandModel command = this.gameCommandDataSnapshot.getValue(GameCommandModel.class);
        DataSnapshot gamePlayerDataSnapshot = this.commander.gamePlayerHandler.getSubresourceDataSnapshot(command.gamePlayer);
        GamePlayerModel player = gamePlayerDataSnapshot.getValue(GamePlayerModel.class);

        Map<String, Object> playerUpdate = new HashMap<>();
        playerUpdate.put("energy", player.energy + 1);

        this.commander.gamePlayerHandler.updateSubresource(gamePlayerDataSnapshot.getKey(), playerUpdate);

    }
}