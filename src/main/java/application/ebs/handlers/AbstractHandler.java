package application.ebs.handlers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractHandler {

    DatabaseReference resourceReference;
    Map<String, DatabaseReference> subresourceReferencesMap = new HashMap<>();
    Map<String, DataSnapshot> subresourceDataSnapshotMap = new HashMap<>();
    String resource;

    public void updateSubresource(String subresourceKey, Map<String, Object> update) {
        this.subresourceReferencesMap.get(subresourceKey).updateChildren(update);
    }

    public DataSnapshot getSubresourceDataSnapshot(String subresourceKey) {
        if (this.subresourceDataSnapshotMap.containsKey(subresourceKey)) {
            return this.subresourceDataSnapshotMap.get(subresourceKey);
        }

        final CountDownLatch retrieve = new CountDownLatch(1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(this.resource ).child(subresourceKey);

        class AwaitedValueListener implements ValueEventListener {
            public DataSnapshot snapshot;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshot = dataSnapshot;
                retrieve.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                retrieve.countDown();
            }
        }

        AwaitedValueListener listener = new AwaitedValueListener();
        ref.addListenerForSingleValueEvent(listener);

        try {
            retrieve.await();
            return listener.snapshot;
        } catch (Exception e) {
            return null;
        }
    }

    void init(String resource) {
        this.resource = resource;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.resourceReference = database.getReference(resource);

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
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("EBS ERROR - onCancelled resource listener: " + databaseError);
            }
        };

        System.out.println("EBS - Adding resourceReference: " + this.resourceReference.getKey());

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

    protected void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot) {};
}
