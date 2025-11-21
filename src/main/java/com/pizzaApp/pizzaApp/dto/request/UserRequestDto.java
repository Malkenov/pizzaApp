package com.pizzaApp.pizzaApp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {

    @Email(message = "Email должен быть правильного формата")
    @NotBlank(message = "Пользователь с данным email существует")
    private String email;

    @NotBlank(message = "Имя пользователя обязательно")
    private String name;

    @NotBlank(message = "Пароль обязательно")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;
}
