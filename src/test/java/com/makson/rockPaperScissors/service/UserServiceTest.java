package com.makson.rockPaperScissors.service;

import com.makson.rockPaperScissors.constant.Constant;
import com.makson.rockPaperScissors.dto.UserDto;
import com.makson.rockPaperScissors.entity.Role;
import com.makson.rockPaperScissors.entity.User;
import com.makson.rockPaperScissors.repository.RoleRepository;
import com.makson.rockPaperScissors.repository.UserRepository;
import com.makson.rockPaperScissors.service.mapper.Mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Mapper mapper;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName("ROLE_USER");
        userDto = new UserDto(
                1L, "name", "password",
                0, 0, 0, 0);
        user = new User("name", "password",
                0, 0, 0, 0, Arrays.asList(role));
    }

    @Test
    void createUser() {
        BindingResult result = Mockito.mock(BindingResult.class);
        Model model = Mockito.mock(Model.class);
        Assertions.assertEquals("redirect:/login", userService.createUser(userDto, result, model));
    }

    @Test
    void getUserByName() {
        Mockito.when(userRepository.findByName("name")).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserByName("name"));
    }
}