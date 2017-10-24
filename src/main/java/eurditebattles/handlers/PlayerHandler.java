package eurditebattles.handlers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import eurditebattles.abstracts.FirebaseHandler;
import eurditebattles.match.MatchCreator;
import eurditebattles.models.PlayerModel;

import java.util.List;

public class PlayerHandler extends FirebaseHandler {

    public static List<DatabaseReference> players;

    public PlayerHandler() {
        init("players");
    }

    @Override
    protected void onSubresourceChangeHandler(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);
        if (player.getState().equals("FIND_MATCH_VS_BOT")) {
            MatchCreator.createNewMatchVsBot(dataSnapshot.getKey());
        }
    }
}
