package application.ebs.handlers;

import application.ebs.commander.Commander;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GameModel;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameCommandHandler extends AbstractHandler {

    @Autowired
    Commander commander;

    public GameCommandHandler() {
        init("gameCommands");
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        commander.handle(dataSnapshot);
    }
}