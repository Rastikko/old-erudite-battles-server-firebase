package application.ebs.handlers;

import application.ebs.models.GamePhaseModel;
import application.ebs.phaser.Phaser;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Component;

@Component
public class GamePhaseHandler extends AbstractHandler {
    public GamePhaseHandler() {
        init("gamePhases");
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
//        GamePhaseModel gamePhase = dataSnapshot.getValue(GamePhaseModel.class);
//        Phaser phaser = new Phaser(gamePhase);
//        phaser.checkBotCommands();
//        phaser.checkPhase();
    }

}
