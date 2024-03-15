package com.Spring.module1spring.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class ConsoleConfig {
    @Bean
    CommandLineRunner commandLineRunnerConsole(ConsoleRepository repository) {
        return args -> {
            Console n64 = new Console(
                    "N64",
                    647,
                    LocalDate.of(1996, JUNE, 23)
            );
            Console wii = new Console(
                    "Wii",
                    314,
                    LocalDate.of(2008, MAY, 5)
            );
            Console ns = new Console(
                    "Switch",
                    400,
                    LocalDate.of(2019, MARCH, 15)

            );
            repository.saveAll(
                    List.of (n64, wii, ns)
            );
        };
    }
}
