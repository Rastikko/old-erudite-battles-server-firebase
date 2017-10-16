package game.handlers;

import com.google.firebase.database.*;
import game.models.PlayerModel;

import javax.annotation.PostConstruct;

public class PlayerHandler {

    public static DatabaseReference playerRef;

    public PlayerHandler() {
        init();
    }

    @PostConstruct
    public void init() {
        playerRef = FirebaseDatabase
                .getInstance()
                .getReference("players");

        playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void playerDataChange(DataSnapshot dataSnapshot) {
        PlayerModel player = dataSnapshot.getValue(PlayerModel.class);
        System.out.println(player);
    }
}
