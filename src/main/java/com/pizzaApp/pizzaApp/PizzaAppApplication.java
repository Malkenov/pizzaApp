package com.pizzaApp.pizzaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PizzaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaAppApplication.class, args);

    }
}
