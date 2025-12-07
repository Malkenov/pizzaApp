package com.pizzaApp.pizzaApp.controller;

import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.service.PizzaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@AllArgsConstructor

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;


    @PostMapping
    public ResponseEntity<PizzaResponseDto> postPizza(
            @RequestBody @Validated PizzaRequestDto dto,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userEmail") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(pizzaService.postPizza(dto,email));
    }

    @GetMapping("/{all}")
    public ResponseEntity<List<PizzaResponseDto>> getAllPizza(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userEmail") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(pizzaService.getAllPizza(email));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PizzaResponseDto> getByName(@PathVariable String name,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userEmail") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(pizzaService.getByName(name,email));
    }

    @GetMapping
    public ResponseEntity<Page<PizzaResponseDto>> getPizzaByPage(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "5") @Min(1) int size,
            @RequestParam(defaultValue = "id") String sort
    ){
        return ResponseEntity.ok(pizzaService.getPizzaByPage(page,size,sort));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<PizzaResponseDto> updatePizza(
            @PathVariable String name,
            @RequestBody PizzaRequestDto dto,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userEmail") == null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(pizzaService.updatePizza(name, dto, email));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> removePizza(@PathVariable String name) {
        pizzaService.deletePizza(name);
        return ResponseEntity.noContent().build();
    }
}
