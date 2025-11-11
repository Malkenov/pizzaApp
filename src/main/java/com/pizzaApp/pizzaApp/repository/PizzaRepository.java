package com.pizzaApp.pizzaApp.repository;

import com.pizzaApp.pizzaApp.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}
