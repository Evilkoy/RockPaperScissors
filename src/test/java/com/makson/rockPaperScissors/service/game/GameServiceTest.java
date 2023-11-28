package com.makson.rockPaperScissors.service.game;

import com.makson.rockPaperScissors.exception.ResultException;
import com.makson.rockPaperScissors.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @InjectMocks
    private GameService gameService;
    @Mock
    private UserService userService;
    @Test
    void processGame() throws ResultException {
        Mockito.mock(UserService.class);
        String actual;
        try {
            actual = gameService.processGame("paper");
        } catch (ResultException exception){
            actual = exception.getMessage();
        }
        Assertions.assertNotEquals("Invalid result of the game", actual);
    }
}