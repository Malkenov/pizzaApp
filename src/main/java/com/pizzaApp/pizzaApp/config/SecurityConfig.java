package com.pizzaApp.pizzaApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // отключаем CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/register").permitAll() // публичная регистрация
                        .anyRequest().authenticated() // остальные эндпоинты защищены
                )
                .httpBasic(AbstractHttpConfigurer::disable); // базовую аутентификацию можно включить через DSL
        return http.build();
    }

}
