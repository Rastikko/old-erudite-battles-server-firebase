package application.eurditebattles.handlers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHandler {

    DatabaseReference resourceReference;
    Map<String, DatabaseReference> subresourceReferences;
    String resource;

    public void updateSubresource(String subresourceReferenceKey, Map<String, Object> update) {
        this.subresourceReferences.get(subresourceReferenceKey).updateChildren(update);
    }

    void init(String resource) {
        this.resource = resource;
        this.resourceReference = FirebaseDatabase.getInstance().getReference(resource);
        this.subresourceReferences = new HashMap<>();

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

        System.out.println("EBS - addSubresourceListener: " + this.resource + "/" + subresource.getKey());

        subresourceReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onSubresourceChangeHandler(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        this.subresourceReferences.put(subresourceReference.getKey(), subresourceReference);
    }

    protected abstract void onSubresourceChangeHandler(DataSnapshot dataSnapshot);
}
