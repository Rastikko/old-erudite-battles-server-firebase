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
import java.util.concurrent.Future;

public abstract class AbstractHandler {

    DatabaseReference resourceReference;
    Map<String, DatabaseReference> subresourceReferences;
    String resource;

    public void updateSubresource(String subresourceReferenceKey, Map<String, Object> update) {
        this.subresourceReferences.get(subresourceReferenceKey).updateChildren(update);
    }

    public CompletableFuture<DataSnapshot> getSubresourceValue(String subresourceReferenceKey) {
        CompletableFuture<DataSnapshot> futureSnapShot = new CompletableFuture<>();
        DatabaseReference reference = this.subresourceReferences.get(subresourceReferenceKey);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                futureSnapShot.complete(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("CANCELED!!!");
            }
        });
        return futureSnapShot;
    }

    void init(String resource) {
        this.resource = resource;
        this.resourceReference = FirebaseDatabase.getInstance().getReference(resource);
        this.subresourceReferences = new HashMap<>();

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        subresourceReference.addValueEventListener(listener);

        this.subresourceReferences.put(subresourceReference.getKey(), subresourceReference);
    }

    // TODO: this could be a lamda
    protected abstract void onSubresourceValueChangeHandler(DataSnapshot dataSnapshot);
}
