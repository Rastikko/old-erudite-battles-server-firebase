package eurditebattles.abstracts;


// all the factories does lives under an specific id
// some will be the only children of that id like the game
// other like gamePlayers will have 2 or more
// parentRe

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseFactory {
    // gamePlayers/{gameId}
    // games
    protected String parentResource;
    protected DatabaseReference reference;

    public DatabaseReference getReference() {
        return reference;
    }

    protected void generateKey() {
        DatabaseReference parentReference = FirebaseDatabase
                .getInstance()
                .getReference(parentResource);

        this.reference = parentReference.push();
    }

    protected void save(Object model) {
        this.reference.setValue(model);
    }


}
