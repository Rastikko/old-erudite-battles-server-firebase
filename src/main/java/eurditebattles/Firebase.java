package eurditebattles;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import eurditebattles.handlers.PlayerHandler;

import java.io.FileInputStream;

public class Firebase {
    public static FirebaseApp firebaseApp;
    public static PlayerHandler playerHandler;

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

            firebaseApp = FirebaseApp.initializeApp(options);

            playerHandler = new PlayerHandler();


        } catch (Exception e) {
            System.out.println("EBS -- Exception in eurditebattles init: " + e);
        }
    }

}
