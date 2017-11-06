package application.ebs.commander;

import application.ebs.handlers.GameCommandHandler;
import application.ebs.models.GameCommandModel;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public abstract class AbstractCommand {

    GameCommandHandler gameCommandHandler;
    DataSnapshot gameCommandDataSnapshot;

    public AbstractCommand(DataSnapshot gameCommandDataSnapshot, GameCommandHandler gameCommandHandler) {
        this.gameCommandDataSnapshot = gameCommandDataSnapshot;
        this.gameCommandHandler = gameCommandHandler;
    }

    public void resolve() {
        Map<String, Object> commandUpdate = new HashMap<>();
        commandUpdate.put("resolved", true);
        gameCommandHandler.updateSubresource(gameCommandDataSnapshot.getKey(), commandUpdate);
    }

    public abstract void execute();

}
