package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.UserDTO;
import com.scorac.stockmanager.service.UserRepository;
import com.scorac.stockmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class userControl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserService userService;

    public userControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", List.of("USER", "ADMIN"));
        return "users";
    }

    @PostMapping("/adduser")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        userService.addNewUser(userDTO);
        return "redirect:/users"; // Redirect or show a success page
    }
}
