package application.eurditebattles.handlers;

import application.eurditebattles.builders.GamePhaseBuilder;
import application.eurditebattles.models.GameModel;
import application.eurditebattles.types.GamePhaseTypes;
import application.eurditebattles.types.PlayerStateType;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameHandler extends AbstractHandler {

    public GameHandler() { init("games"); }

    @Override
    protected void onSubresourceChangeHandler(DataSnapshot dataSnapshot) {
        // if no phase create INITIAL_DRAW_PHASE
        // PlayerModel player = dataSnapshot.getValue(PlayerModel.class);

        GameModel game = dataSnapshot.getValue(GameModel.class);
        if (game.gamePhase == null) {
            GamePhaseBuilder gamePhaseBuilder = new GamePhaseBuilder(dataSnapshot.getKey(), GamePhaseTypes.INITIAL_DRAW.name());

            Map<String, String> phaseReference = new HashMap<>();
            phaseReference.put("id", gamePhaseBuilder.getReference().getKey());
            phaseReference.put("type", "gamePhase");

            Map<String, Object> childUpdate = new HashMap<>();
            childUpdate.put("gamePhase", phaseReference);

            updateSubresource(dataSnapshot.getKey(), childUpdate);
            System.out.println("EBS -- Creating INITIAL_DRAW phase");
        }

    }
}