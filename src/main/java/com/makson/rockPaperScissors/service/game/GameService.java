package com.makson.rockPaperScissors.service.game;

import com.makson.rockPaperScissors.constant.Constant;
import com.makson.rockPaperScissors.exception.OpponentsChoiceException;
import com.makson.rockPaperScissors.exception.ResultException;
import com.makson.rockPaperScissors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private UserService userService;

    public String processGame(String choice) throws ResultException {
        String result = getResult(choice);
        userService.updateStatistic(result);
        return switch (result) {
            case (Constant.WON) -> "You Won!";
            case (Constant.LOST) -> "You Lost";
            case (Constant.DRAW) -> "Draw";
            default -> throw new ResultException("Invalid result of the game");
        };
    }

    private String getResult(String choice) {
        String opponentsChoice;
        try {
            opponentsChoice = getOpponentsChoice();
        } catch (OpponentsChoiceException exception) {
            return Constant.WON;
        }
        if (choice.equals(opponentsChoice)) return Constant.DRAW;
        else if (choice.equals(Constant.ROCK) && opponentsChoice.equals(Constant.SCISSORS)) return Constant.WON;
        else if (choice.equals(Constant.PAPER) && opponentsChoice.equals(Constant.ROCK)) return Constant.WON;
        else if (choice.equals(Constant.SCISSORS) && opponentsChoice.equals(Constant.PAPER)) return Constant.WON;
        else return Constant.LOST;
    }

    private int randomize() {
        return (int) Math.floor(Math.random() * 3) + 1;
    }

    private String getOpponentsChoice() throws OpponentsChoiceException {
        return switch (randomize()) {
            case 1 -> Constant.ROCK;
            case 2 -> Constant.PAPER;
            case 3 -> Constant.SCISSORS;
            default -> throw new OpponentsChoiceException("Wrong computers choice");
        };
    }
}
