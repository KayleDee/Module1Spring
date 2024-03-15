package com.Spring.module1spring.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class GameConfig {
    @Bean
    CommandLineRunner commandLineRunnerGame(GameRepository repository) {
        return args -> {
            Game dbd = new Game(
                    "Dead By Daylight",
                    40,
                    LocalDate.of(2016, Month.JUNE, 14)
            );
            Game loztk = new Game(
                    "Legend Of Zelda Tears Of Kingdom",
                    40,
                    LocalDate.of(2023, Month.MAY, 12)
            );
            Game wiimusic = new Game(
                    "Wii Music",
                    40,
                    LocalDate.of(2008, Month.OCTOBER, 16)
            );
            repository.saveAll(
                    List.of(dbd, loztk, wiimusic)
            );
        };

    }
}
