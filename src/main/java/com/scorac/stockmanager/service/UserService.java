//package com.scorac.stockmanager.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    private final UserRepository usersRepository;
//
//    public UserService(UserRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usersRepository.findByUsername(username);
//    }
//
//}
