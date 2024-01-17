package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.UserDTO;
import com.scorac.stockmanager.model.Users;
import com.scorac.stockmanager.service.UserRepository;
import com.scorac.stockmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value="/users")
public class userControl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserService userService;

    public userControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String userlist(Model model){
        List <Users> userlist = userService.getAllUsers();
        model.addAttribute("list", userlist);
        return "userList";
    }

    @GetMapping("/newuser")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", List.of("USER", "ADMIN"));

        return "newUser";
    }

    @PostMapping("/adduser")
    public String registerUser(@ModelAttribute UserDTO userDTO, Model model) {
        try{
            userService.addNewUser(userDTO);
            return "redirect:/users/list"; // Redirect or show a success page
        } catch (IllegalStateException e){
            model.addAttribute("error", "User already exist!");
            return "newUser";
        }
    }
}
