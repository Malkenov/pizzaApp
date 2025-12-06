package com.pizzaApp.pizzaApp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain - это цепочка фильтров безопасности, для обработки каждого HTTP-запроса
        http
                .csrf(AbstractHttpConfigurer::disable) // отключаем csrf - защиту от поддельных запросов из браузера
                .authorizeHttpRequests(auth -> auth    // описание, какие URL не требуют доступ
                        .requestMatchers("/users/login", "/users/register").permitAll()
                        .anyRequest().authenticated()  // остальные URL требуют доступ
                )
                .sessionManagement(session -> session  // создается сессия только при успешном авторизации (создается cookie)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

}


