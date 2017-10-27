package application.eurditebattles.match;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import application.eurditebattles.builders.GameBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MatchCreator {
    public void createNewMatchVsBot(String playerId) {
        System.out.println("EBS -- Creating new application.eurditebattles VS bot for ");
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
