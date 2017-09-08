package io.sbelkin.codenames;

import io.sbelkin.codenames.engine.GenerateGameBoard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(GenerateGameBoard generateGameBoard) {
        return (args) -> {
            generateGameBoard.init();
        };
    }
}