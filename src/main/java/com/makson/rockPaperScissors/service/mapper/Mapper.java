package com.makson.rockPaperScissors.service.mapper;

import com.makson.rockPaperScissors.dto.UserDto;
import com.makson.rockPaperScissors.entity.User;
import com.makson.rockPaperScissors.repository.RoleRepository;
import com.makson.rockPaperScissors.webConfig.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Mapper {
    private final PasswordEncoder passwordEncoder = WebSecurityConfig.passwordEncoder();
    @Autowired
    RoleRepository roleRepository;

    public User mapToUser(UserDto dto) {
        return new User(
                dto.getName(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getTotal(),
                dto.getWins(),
                dto.getDraws(),
                dto.getLost(),
                Arrays.asList(roleRepository.findByName("ROLE_USER")));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getTotal(),
                user.getWins(),
                user.getDraws(),
                user.getLost());
    }
}
