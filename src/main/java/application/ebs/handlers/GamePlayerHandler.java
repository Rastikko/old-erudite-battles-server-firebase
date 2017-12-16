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
}
