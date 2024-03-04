package com.Spring.module1spring;

import com.Spring.module1spring.person.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@SpringBootApplication
public class Module1SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Module1SpringApplication.class, args);
	}

}

