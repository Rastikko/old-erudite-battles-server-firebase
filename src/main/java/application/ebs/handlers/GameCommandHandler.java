package application.ebs.handlers;

import application.ebs.commander.Commander;
import application.ebs.models.GameCommandModel;
import application.ebs.models.GameModel;
import application.ebs.phaser.Phaser;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameCommandHandler extends AbstractHandler {

    @Autowired
    Commander commander;
    @Autowired
    Phaser phaser;

    public GameCommandHandler() {
        init("gameCommands");
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        GameCommandModel gameCommand = dataSnapshot.getValue(GameCommandModel.class);
        commander.handle(dataSnapshot);

        if (gameCommand.resolved) {
            phaser.processPhase(gameCommand.gamePhase);
        }
    }
}