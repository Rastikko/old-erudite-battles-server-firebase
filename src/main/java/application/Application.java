package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static Firebase firebaseHolder;

    public static void main(String[] args) {
        firebaseHolder = new Firebase();

        SpringApplication.run(Application.class, args);

    }
}
