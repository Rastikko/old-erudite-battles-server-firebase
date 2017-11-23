package application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class Firebase {

    public Firebase() {
        init();
    }

    public void init() {
        try {
            String fileLocationInClasspath = "erudite-battles-0de6d72b4d6d.json";
            ClassPathResource resource = new ClassPathResource(fileLocationInClasspath);
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://erudite-battles.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            database.setLogLevel(Logger.Level.DEBUG);

            final DatabaseReference refAuthenticated = database.getReference(".info/authenticated");
            refAuthenticated.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean connected = dataSnapshot.getValue(Boolean.class);
                    if (connected) {
                        System.out.println("EBS - Firebase Authenticated");
                    } else {
                        System.out.println("EBS - Firebase Not Authenticated: " + dataSnapshot);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("EBS - Firebase Listener was cancelled at .info/authenticated");
                }
            });

        } catch (Exception e) {
            System.out.println("EBS ERROR - Exception in Firebase init: " + e);
        }
    }

}
