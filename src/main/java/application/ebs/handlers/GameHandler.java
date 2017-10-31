package application.ebs.handlers;

import application.ebs.builders.GamePhaseBuilder;
import application.ebs.models.GameModel;
import application.ebs.types.GamePhaseTypes;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameHandler extends AbstractHandler {

    public GameHandler() { init("games"); }


    public void processBotPhase(String phaseId, String gameId) {

    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        // if no phase create INITIAL_DRAW_PHASE
        // PlayerModel player = dataSnapshot.getValue(PlayerModel.class);

        GameModel game = dataSnapshot.getValue(GameModel.class);
        if (game.gamePhase == null) {
            GamePhaseBuilder gamePhaseBuilder = new GamePhaseBuilder(dataSnapshot.getKey(), GamePhaseTypes.INITIAL_DRAW.name());

            Map<String, Object> childUpdate = new HashMap<>();
            childUpdate.put("gamePhase", gamePhaseBuilder.getReference().getKey());
            updateSubresource(dataSnapshot.getKey(), childUpdate);

            System.out.println("EBS -- Creating INITIAL_DRAW phase");
        }

    }
}