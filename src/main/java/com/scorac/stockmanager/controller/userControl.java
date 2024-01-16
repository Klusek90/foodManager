package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Role;
import com.scorac.stockmanager.model.UserDTO;
import com.scorac.stockmanager.model.Users;
import com.scorac.stockmanager.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.support.BeanDefinitionDsl;
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

    @GetMapping("/users")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", List.of("USER", "ADMIN"));
        return "users";
    }

    @PostMapping("/adduser")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        Users newUser = new Users();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setName(userDTO.getName());
        newUser.setSurname(userDTO.getSurname());
        newUser.setPosition(userDTO.getPosition());
        newUser.setRole(Role.valueOf(userDTO.getRole().toString()));

        userRepository.save(newUser);
        return "redirect:/users"; // Redirect or show a success page
    }
}
