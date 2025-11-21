package com.pizzaApp.pizzaApp.service;

import com.pizzaApp.pizzaApp.dto.request.UserRequestDto;
import com.pizzaApp.pizzaApp.dto.response.UserResponseDto;
import com.pizzaApp.pizzaApp.entity.User;
import com.pizzaApp.pizzaApp.exception.BadRequestException;
import com.pizzaApp.pizzaApp.exception.NotFoundException;
import com.pizzaApp.pizzaApp.mapper.UserMapper;
import com.pizzaApp.pizzaApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;


    public UserResponseDto createUser(UserRequestDto dto){
        User user = userMapper.toEntity(dto);

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
            throw new BadRequestException("Пользователь с таким " + dto.getEmail() + "существует");
        });
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(String email){
        if(!userRepository.existsByEmail(email)){
            throw new NotFoundException("Пользователь с " + email + "не найден");
        }
        userRepository.deleteByEmail(email);
    }
}
