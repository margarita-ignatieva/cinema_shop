package com.cinema.shop.mappers;

import com.cinema.shop.model.User;
import com.cinema.shop.model.dto.UserRequestDto;
import com.cinema.shop.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User getUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }

    public UserResponseDto getUserDt0(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }

}
