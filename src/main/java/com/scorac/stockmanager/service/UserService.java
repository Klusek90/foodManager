package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.TDO.UserDTO;
import com.scorac.stockmanager.model.Entity.Users;
import com.scorac.stockmanager.service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        List<Users> allUsers =userRepository.findAll();
        return allUsers;
    }

    public  void  updateUser(UserDTO userDTO){
        Users updatedUser = new Users();
        updatedUser.setUsername(userDTO.getUsername());
        if(userDTO.getPassword().isEmpty()){
            Users inMemoryUser = userRepository.findOneByUsername(userDTO.getUsername());
            updatedUser.setPassword(inMemoryUser.getPassword());
        }
        else{
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        updatedUser.setName(userDTO.getName());
        updatedUser.setSurname(userDTO.getSurname());
        updatedUser.setPosition(userDTO.getPosition());
        updatedUser.setRole(userDTO.getRole());
        userRepository.save(updatedUser);
    }

}
