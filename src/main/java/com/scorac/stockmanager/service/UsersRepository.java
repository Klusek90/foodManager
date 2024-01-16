package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    UserDetails findByUsername(String username);

}
