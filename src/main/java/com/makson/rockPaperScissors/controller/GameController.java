package com.makson.rockPaperScissors.controller;

import com.makson.rockPaperScissors.exception.ResultException;
import com.makson.rockPaperScissors.service.UserService;
import com.makson.rockPaperScissors.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameService gameService;
    @Autowired
    UserService userService;

    @GetMapping()
    public String showGamePage(Model model) {
        model.addAttribute("title", "Select the option");
        model.addAttribute("user", userService.getLoggedUserDto
                (SecurityContextHolder.getContext().getAuthentication().getName()));
        return "Game";
    }

    @PostMapping()
    public String showResults(@RequestParam(value = "action") String action, Model model) {
        try {
            model.addAttribute("title", gameService.processGame(action));
        } catch (ResultException exception) {
            model.addAttribute("title", exception.getMessage());
        }
        model.addAttribute("user", userService.getLoggedUserDto
                (SecurityContextHolder.getContext().getAuthentication().getName()));
        return "Game";
    }
}
