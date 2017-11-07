package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.handlers.GamePhaseHandler;
import application.ebs.models.GameCommandModel;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

public class EndPhaseCommand extends AbstractCommand {

    public EndPhaseCommand(Commander commander, DataSnapshot gameCommandDataSnapshot) {
        super(commander, gameCommandDataSnapshot);
    }

    @Override
    public void execute() {
        GameCommandModel command = this.gameCommandDataSnapshot.getValue(GameCommandModel.class);

        Map<String, Object> phaseUpdate = new HashMap<>();
        Map<String, Boolean> phaseEndGamePlayersUpdate = new HashMap<>();
        phaseEndGamePlayersUpdate.put(command.gamePlayer, true);
        phaseUpdate.put("phaseEndGamePlayers", phaseEndGamePlayersUpdate);

        this.commander.gamePhaseHandler.updateSubresource(command.gamePhase, phaseUpdate);
    }
}
