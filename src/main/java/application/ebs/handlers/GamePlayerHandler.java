package application.ebs.handlers;

import application.ebs.models.GamePlayerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Component;

@Component
public class GamePlayerHandler extends AbstractHandler {

    public GamePlayerHandler() { init("gamePlayers"); }

    public void drawCard(String gamePlayerId) {
        DatabaseReference playerReference = this.subresourceReferences.get(gamePlayerId);
        playerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    GamePlayerModel player = dataSnapshot.getValue(GamePlayerModel.class);
                    System.out.println("draw card for" + player);
                } catch (Exception e) {
                    System.out.println("ERROR!!" + e);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {
        // Check if is a bot and handle his phases somehow
    }
}
