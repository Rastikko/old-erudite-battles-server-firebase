package application.ebs.handlers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractHandler {

    DatabaseReference resourceReference;
    Map<String, DatabaseReference> subresourceReferencesMap = new HashMap<>();
    Map<String, DataSnapshot> subresourceDataSnapshotMap = new HashMap<>();
    String resource;

    public void updateSubresource(String subresourceKey, Map<String, Object> update) {
        this.subresourceReferencesMap.get(subresourceKey).updateChildren(update);
    }

    public DataSnapshot getSubresourceDataSnapshot(String subresourceKey) {
        return this.subresourceDataSnapshotMap.get(subresourceKey);
    }

    void init(String resource) {
        this.resource = resource;
        this.resourceReference = FirebaseDatabase.getInstance().getReference(resource);

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                addSubresourceListener(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        this.resourceReference.addChildEventListener(listener);
    }

    void addSubresourceListener(DataSnapshot subresource) {
        DatabaseReference subresourceReference = FirebaseDatabase
                .getInstance()
                .getReference(this.resource + "/" + subresource.getKey());

        System.out.println("EBS - addSubresourceListener: " + this.resource + "/" + subresource.getKey());

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onSubresourceValueChangeHandler(dataSnapshot);
                subresourceDataSnapshotMap.put(subresource.getKey(), dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        subresourceReference.addValueEventListener(listener);

        this.subresourceReferencesMap.put(subresourceReference.getKey(), subresourceReference);
    }

    protected abstract void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot);
}
