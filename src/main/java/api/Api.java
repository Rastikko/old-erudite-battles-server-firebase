package api;

import eurditebattles.Firebase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Api {

    public static Firebase firebaseHolder;

    public static void main(String[] args) {

        SpringApplication.run(Api.class, args);
        firebaseHolder = new Firebase();

    }
}
