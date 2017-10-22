package eurditebattles.match;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import eurditebattles.builders.GameBuilder;

import java.util.HashMap;
import java.util.Map;

public class GameCreator {
    public static void createNewMatch(String type, String playerId) {
        if (type.equals("FIND_MATCH_VS_BOT")) {
            System.out.println("EBS -- Creating new eurditebattles VS bot for ");
            GameBuilder game = new GameBuilder(playerId);

            DatabaseReference playersRef = FirebaseDatabase
                    .getInstance()
                    .getReference("players/" + playerId);

            Map<String, Object> childUpdate = new HashMap<>();
            childUpdate.put("state", "IN_GAME");
            childUpdate.put("gameId", game.getReference().getKey());
            playersRef.updateChildren(childUpdate);
        }
    }
}
