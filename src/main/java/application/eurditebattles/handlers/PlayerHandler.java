package application.eurditebattles.handlers;

import application.eurditebattles.builders.GameBuilder;
import application.eurditebattles.types.PlayerStateType;
import com.google.firebase.database.DataSnapshot;
import application.eurditebattles.models.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerHandler extends AbstractHandler {

    public PlayerHandler() {
        init("players");
    }

    @Override
    protected void onSubresourceChangeHandler(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);

        if (player.state.equals(PlayerStateType.FIND_MATCH_VS_BOT.name())) {
            System.out.println("EBS -- Creating new application.eurditebattles VS bot for ");

            GameBuilder game = new GameBuilder(dataSnapshot.getKey());

            Map<String, String> gameReference = new HashMap<>();
            gameReference.put("id", game.getReference().getKey());
            gameReference.put("type", "game");

            Map<String, Object> childUpdate = new HashMap<>();
            childUpdate.put("state", PlayerStateType.IN_GAME.name());
            childUpdate.put("game", gameReference);

            updateSubresource(dataSnapshot.getKey(), childUpdate);
        }

    }
}
