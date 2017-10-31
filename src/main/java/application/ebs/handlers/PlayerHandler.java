package application.ebs.handlers;

import application.ebs.builders.GameBuilder;
import application.ebs.types.PlayerStateType;
import com.google.firebase.database.DataSnapshot;
import application.ebs.models.PlayerModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerHandler extends AbstractHandler {

    public PlayerHandler() {
        init("players");
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);

        if (player.state.equals(PlayerStateType.FIND_MATCH_VS_BOT.name())) {
            System.out.println("EBS -- Creating new application.ebs VS bot for ");

            GameBuilder game = new GameBuilder(dataSnapshot.getKey());

            Map<String, Object> childUpdate = new HashMap<>();
            childUpdate.put("state", PlayerStateType.IN_GAME.name());
            childUpdate.put("game", game.getReference().getKey());

            updateSubresource(dataSnapshot.getKey(), childUpdate);
        }

    }
}
