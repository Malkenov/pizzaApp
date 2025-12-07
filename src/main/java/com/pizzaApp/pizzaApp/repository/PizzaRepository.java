package com.pizzaApp.pizzaApp.repository;

import com.pizzaApp.pizzaApp.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    List<Pizza> findByUserEmail(String email);

    Optional<Pizza> findByNameAndUserEmail(String name, String email);

}
