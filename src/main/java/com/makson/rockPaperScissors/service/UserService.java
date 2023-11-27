package com.makson.rockPaperScissors.service;

import com.makson.rockPaperScissors.constant.Constant;
import com.makson.rockPaperScissors.dto.UserDto;
import com.makson.rockPaperScissors.entity.User;
import com.makson.rockPaperScissors.repository.UserRepository;
import com.makson.rockPaperScissors.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Mapper mapper;

    public String createUser(UserDto userDto, BindingResult result, Model model) {
        User existingUser = getUserByName(userDto.getName());
        if (existingUser != null && !existingUser.getName().isEmpty()) {
            result.rejectValue("name", null,
                    "There is already an account with the same name");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registration";
        }
        userDto.setTotal(0);
        userDto.setWins(0);
        userDto.setDraws(0);
        userDto.setLost(0);
        userRepository.save(mapper.mapToUser(userDto));
        return "redirect:/login";
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void updateStatistic(String result) {
        User user = getLoggedUser();
        user.setTotal(user.getTotal() + 1);
        if (result.equals(Constant.WON))
            user.setWins(user.getWins() + 1);
        if (result.equals(Constant.LOST))
            user.setLost(user.getLost() + 1);
        if (result.equals(Constant.DRAW))
            user.setDraws(user.getDraws() + 1);
        userRepository.save(user);
    }

    public UserDto getLoggedUserDto() {
        return mapper.mapToUserDto
                (userRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    private User getLoggedUser() {
        return userRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
