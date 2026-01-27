package com.pizzaApp.pizzaApp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequestDto {

    private String name;
    private String password;
    private String email;
}
