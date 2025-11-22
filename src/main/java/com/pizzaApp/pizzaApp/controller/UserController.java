package com.pizzaApp.pizzaApp.controller;

import com.pizzaApp.pizzaApp.dto.request.UserRequestDto;
import com.pizzaApp.pizzaApp.dto.response.UserResponseDto;
import com.pizzaApp.pizzaApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Validated UserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email){
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody UserRequestDto dto) {
        return userService.login(dto);
    }
}
