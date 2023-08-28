package com.example.ravin.dtos.mapper;

import com.example.ravin.domains.user.User;
import com.example.ravin.dtos.request.UserRequestDto;
import com.example.ravin.dtos.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserRequestDto, UserResponseDto> {

    public UserMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    Class<UserRequestDto> getRequestClass() {
        return UserRequestDto.class;
    }

    @Override
    Class<UserResponseDto> getResponseClass() {
        return UserResponseDto.class;
    }
}
