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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto createUser(UserRequestDto dto){
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
            throw new BadRequestException("Пользователь с таким " + dto.getEmail() + "существует");
        });
        User user = userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

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
