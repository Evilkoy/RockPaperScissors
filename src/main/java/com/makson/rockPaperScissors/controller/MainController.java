package com.makson.rockPaperScissors.controller;

import com.makson.rockPaperScissors.dto.UserDto;
import com.makson.rockPaperScissors.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String redirectToGamePage(Model model) {
        return "redirect:/game";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "Registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        return userService.createUser(userDto, result, model);
    }
}
