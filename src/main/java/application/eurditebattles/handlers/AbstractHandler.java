package application.eurditebattles.handlers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHandler {

    DatabaseReference resourceReference;
    List<DatabaseReference> subresourceReferences;
    String resource;

    void init(String resource) {
        this.resource = resource;
        this.resourceReference = FirebaseDatabase.getInstance().getReference(resource);
        this.subresourceReferences = new ArrayList<>();

        resourceReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                resourceDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    void resourceDataChange(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> subresources = dataSnapshot.getChildren();

        for (DataSnapshot subresource : subresources) {
            addSubresourceListener(subresource);
        }
    }

    void addSubresourceListener(DataSnapshot subresource) {
        DatabaseReference subresourceReference = FirebaseDatabase
                .getInstance()
                .getReference(this.resource + "/" + subresource.getKey());

        subresourceReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onSubresourceChangeHandler(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        this.subresourceReferences.add(subresourceReference);
    }

    protected abstract void onSubresourceChangeHandler(DataSnapshot dataSnapshot);
}
