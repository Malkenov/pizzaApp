package com.pizzaApp.pizzaApp.config;

import org.springframework.stereotype.Service;
import com.pizzaApp.pizzaApp.repository.UserRepository;
import com.pizzaApp.pizzaApp.entity.User;
import com.pizzaApp.pizzaApp.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    //класс который, говорит Spring security, как искать пользователя и его пароль
    //Проще говоря мост между БД и Spring security

    //UserDetailsService это интерфейс, у которого есть метод для пойска по email

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())  // пароль в БД должен быть захеширован
                .roles("USER")
                .build();
    }
}
