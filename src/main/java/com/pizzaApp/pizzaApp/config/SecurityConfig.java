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
                .csrf(AbstractHttpConfigurer::disable) // отключаем csrf - защиту
                .authorizeHttpRequests(auth -> auth    // описание, какие URL не требуют доступ
                        .requestMatchers("/users/login", "/users/register").permitAll()
                        .anyRequest().authenticated()  // остальные URL требуют доступ
                )
                .formLogin(from -> from
                        .loginPage("/users/login")     // блок кода, где описано, в каком URL будет логирование
                        .defaultSuccessUrl("/home",true)
                )
                .sessionManagement(session -> session  // создается сессия только при успешном авторизации (создается cookie)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

}


