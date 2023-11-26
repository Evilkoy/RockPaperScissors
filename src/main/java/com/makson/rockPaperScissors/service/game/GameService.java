package com.makson.rockPaperScissors.service.game;

import com.makson.rockPaperScissors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private UserService userService;

    public String getStatus(String choice) {
        String result = getResult(choice);
        userService.updateStatistic(result);
        return switch (result) {
            case ("won") -> "You Won!";
            case ("lost") -> "You Lost";
            case ("tied") -> "Tied";
            default -> "Try again, please";
        };
    }

    private String getResult(String choice) {
        String opponentsChoice = getOpponentsChoice();
        if (choice.equals(opponentsChoice)) return "tied";
        else if (choice.equals("rock") && opponentsChoice.equals("scissors")) return "won";
        else if (choice.equals("paper") && opponentsChoice.equals("rock")) return "won";
        else if (choice.equals("scissors") && opponentsChoice.equals("paper")) return "won";
        else return "lost";
    }

    private int randomize() {
        return (int) Math.floor(Math.random() * 3) + 1;
    }

    private String getOpponentsChoice() {
        return switch (randomize()) {
            case 1 -> "rock";
            case 2 -> "paper";
            case 3 -> "scissors";
            default -> "null";
        };
    }
}
