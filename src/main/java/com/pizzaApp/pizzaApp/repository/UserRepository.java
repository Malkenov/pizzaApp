package com.pizzaApp.pizzaApp.repository;

import com.pizzaApp.pizzaApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Optional чтобы избежать null,так как пользователя может не быть в бд

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
