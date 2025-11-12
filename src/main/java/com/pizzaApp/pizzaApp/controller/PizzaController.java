package com.pizzaApp.pizzaApp.controller;

import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaService pizzaService;


    @PostMapping
    public ResponseEntity<PizzaResponseDto> postPizza(@RequestBody @Validated PizzaRequestDto dto) {
        return ResponseEntity.ok(pizzaService.postPizza(dto));
    }

    @GetMapping
    public ResponseEntity<List<PizzaResponseDto>> getAllPizza() {
        return ResponseEntity.ok(pizzaService.getAllPizza());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PizzaResponseDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<PizzaResponseDto> updatePizza(@PathVariable String name, @RequestBody PizzaRequestDto dto) {
        return ResponseEntity.ok(pizzaService.updatePizza(name, dto));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> removePizza(@PathVariable String name) {
        pizzaService.deletePizza(name);
        return ResponseEntity.noContent().build();
    }
}
