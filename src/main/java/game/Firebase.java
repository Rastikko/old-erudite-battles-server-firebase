package game;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import game.handlers.PlayerHandler;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

public class Firebase {
    public static FirebaseApp firebase;
    public static PlayerHandler playerHandler;

    public Firebase() {
        init();
    }

    @PostConstruct
    public void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("erudite-battles-0de6d72b4d6d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://erudite-battles.firebaseio.com/")
                    .build();

            firebase = FirebaseApp.initializeApp(options);
            playerHandler = new PlayerHandler();


        } catch (Exception e) {
            System.out.println("EBS -- Exception in firebase init: " + e);
        }
    }

}
