package com.Spring.module1spring.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;


@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunnerPerson(PersonRepository repository) {
        return args -> {
            Person poki = new Person(
                    "Poki",
                    LocalDate.of(1996, OCTOBER, 13),
                    "poki1234567@gmail.com",
                    "678-905-4836"
            );
            Person leon = new Person(
                    "Leon",
                    LocalDate.of(2001, DECEMBER, 10),
                    "leon@gmail.com",
                    "662-579-6094"
            );
            repository.saveAll(
                    List.of(poki, leon)
            );
        };
    }
}
