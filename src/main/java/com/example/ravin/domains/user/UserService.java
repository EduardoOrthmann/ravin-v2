package com.example.ravin.domains.user;

import com.example.ravin.domains.dtos.mapper.UserMapper;
import com.example.ravin.domains.dtos.request.UserRequestDto;
import com.example.ravin.domains.dtos.response.UserResponseDto;
import com.example.ravin.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (existsByLogin(userRequestDto.getLogin())) {
            throw new UserAlreadyExistsException();
        }

        return userMapper.toResponse(userRepository.save(userMapper.toEntity(userRequestDto)));
    }
}
