package com.pizzaApp.pizzaApp.controller;

import com.pizzaApp.pizzaApp.dto.request.UserRequestDto;
import com.pizzaApp.pizzaApp.dto.response.UserResponseDto;
import com.pizzaApp.pizzaApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RequestContextFilter;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final RequestContextFilter requestContextFilter;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Validated UserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody @Validated UserRequestDto dto,
            HttpServletRequest request){
        UserResponseDto user = userService.login(dto);     // проверяем логин и получаем пользователя
        HttpSession session = request.getSession(true); // создается сессия если ее нету
        session.setAttribute("userEmail",user.getEmail()); // кладем емайл польщователя в сессию
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email){
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
