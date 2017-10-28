package application.eurditebattles.handlers;

import application.eurditebattles.types.PlayerStateType;
import com.google.firebase.database.DataSnapshot;
import application.eurditebattles.match.MatchCreator;
import application.eurditebattles.models.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerHandler extends AbstractHandler {

    @Autowired
    MatchCreator matchCreator;

    public PlayerHandler() {
        init("players");
    }

    @Override
    protected void onSubresourceChangeHandler(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);
        if (player.state.equals(PlayerStateType.FIND_MATCH_VS_BOT.name())) {
            matchCreator.createNewMatchVsBot(dataSnapshot.getKey());
        }
    }
}
