package application.ebs.handlers;

import application.ebs.models.GamePhaseModel;
import application.ebs.phaser.Phaser;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamePhaseHandler extends AbstractHandler {

    @Autowired
    Phaser phaser;

    public GamePhaseHandler() {
        init("gamePhases");
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        phaser.setPhaseModel(dataSnapshot);
    }

}
