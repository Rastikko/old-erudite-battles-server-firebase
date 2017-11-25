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
    public void execute() {}
}
