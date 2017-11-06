package application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.io.FileInputStream;

public class Firebase {

    public Firebase() {
        init();
    }

    public void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("erudite-battles-0de6d72b4d6d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://erudite-battles.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            database.setLogLevel(Logger.Level.DEBUG);


        } catch (Exception e) {
            System.out.println("EBS ERROR - Exception in Firebase init: " + e);
        }
    }

}
