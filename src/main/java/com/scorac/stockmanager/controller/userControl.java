package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.TDO.UserDTO;
import com.scorac.stockmanager.model.Users;
import com.scorac.stockmanager.service.Repository.UserRepository;
import com.scorac.stockmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping(value="/users")
public class userControl {

    @Autowired
    private UserRepository userRepository;
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

        return "userNew";
    }

    @PostMapping("/adduser")
    public String registerUser(@ModelAttribute UserDTO userDTO, Model model,RedirectAttributes redirectAttributes) {
        try{
            userService.addNewUser(userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User successfully added!");
            return "redirect:/users/list"; // Redirect or show a success page
        } catch (IllegalStateException e){
            model.addAttribute("error", "User already exist!");
            return "userNew";
        }
    }

    @GetMapping("/edit/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {
       Users singleUser = userRepository.findOneByUsername(username);
       model.addAttribute("user", singleUser);
       return "userEdit";
    }

    @PostMapping("/updateuser")
    public String updateuser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully updated!");
        return "redirect:/users/list";
        }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        userRepository.deleteById(username);
        redirectAttributes.addFlashAttribute("successMessage", "User successfully deleted!");
        return "redirect:/users/list";
    }
}
