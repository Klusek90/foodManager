package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.UserDTO;
import com.scorac.stockmanager.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void addNewUser(UserDTO userDTO){
        Users newUser = new Users();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setName(userDTO.getName());
        newUser.setSurname(userDTO.getSurname());
        newUser.setPosition(userDTO.getPosition());
        newUser.setRole(userDTO.getRole());

        Optional<Users> userOptional = userRepository.findByUsername(newUser.getUsername());

        if (userOptional.isEmpty()) {
            // Save the new user only if user with the same username doesn't exist
            userRepository.save(newUser);
        } else {
            // Handle the case when user already exists, e.g., throw an exception or log a message
            throw new IllegalStateException("Username already taken");
        }
    }

    public List<Users> getAllUsers(){
        List<Users> allUsers = new ArrayList<>();
        allUsers= userRepository.findAll();
        return allUsers;
    }

}
