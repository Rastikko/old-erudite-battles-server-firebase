package application.eurditebattles.handlers;

import com.google.firebase.database.DataSnapshot;
import application.eurditebattles.match.MatchCreator;
import application.eurditebattles.models.PlayerModel;
import org.springframework.stereotype.Component;

@Component
public class PlayerHandler extends AbstractHandler {

    public PlayerHandler() {
        init("players");
    }

    @Override
    protected void onSubresourceChangeHandler(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);
        if (player.state.equals("FIND_MATCH_VS_BOT")) {
            MatchCreator.createNewMatchVsBot(dataSnapshot.getKey());
        }
    }
}