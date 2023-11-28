package com.makson.rockPaperScissors.service.mapper;

import com.makson.rockPaperScissors.dto.UserDto;
import com.makson.rockPaperScissors.entity.Role;
import com.makson.rockPaperScissors.entity.User;
import com.makson.rockPaperScissors.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class MapperTest {
    @InjectMocks
    private Mapper mapper;
    @Mock
    private RoleRepository roleRepository;
    private UserDto userDto;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName("ROLE_USER");
        userDto = new UserDto(
                1L,
                "name",
                "password",
                0, 0, 0, 0);
        user = new User("name", "password", 0, 0, 0, 0, Arrays.asList(role));
        user.setId(1L);
    }

    @Test
    void mapToUserDto() {
        Assertions.assertEquals(userDto, mapper.mapToUserDto(user));
    }

    @Test
    void mapToUser() {
        user.setId(null);
        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        User actual = mapper.mapToUser(userDto);
        actual.setPassword("password");
        Assertions.assertEquals(user, actual);
    }
}