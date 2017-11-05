package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.models.GameCommandModel;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommand {

    GameCommandHandler commandHandler;
    DataSnapshot gameCommandDataSnapshot;

    public AbstractCommand(DataSnapshot gameCommandDataSnapshot) {
        this.gameCommandDataSnapshot = gameCommandDataSnapshot;
    }

    public void resolve() {
//        Map<String, Object> commandUpdate = new HashMap<>();
//        commandUpdate.put("resolved", true);
//        commandHandler.updateSubresource(commandModel.);
    }

    public abstract void execute();

}
