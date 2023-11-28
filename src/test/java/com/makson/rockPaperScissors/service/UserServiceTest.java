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
import org.mockito.ArgumentCaptor;
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
        Mockito.when(userService.getUserByName(userDto.getName())).thenReturn(user);
        Mockito.when(mapper.mapToUser(userDto)).thenReturn(user);
//        Assertions.assertEquals("redirect:/login", userService.createUser(userDto, result, model));
        userService.createUser(userDto, result, model);
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(argument.capture());
        Assertions.assertEquals(user, argument.getValue());
    }

    @Test
    void getUserByName() {
        Mockito.when(userRepository.findByName("name")).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserByName("name"));
    }

    @Test
    void updateStatistic() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("name",null);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(userRepository.findByName("name")).thenReturn(user);
        userService.updateStatistic(Constant.WON);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(argument.capture());
        Assertions.assertEquals(1, argument.getValue().getWins());
    }
}