package com.pizzaApp.pizzaApp.mapper;

import com.pizzaApp.pizzaApp.dto.request.UserRequestDto;
import com.pizzaApp.pizzaApp.dto.response.UserResponseDto;
import com.pizzaApp.pizzaApp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     User toEntity(UserRequestDto dto);

     UserResponseDto toDto(User user);
}
