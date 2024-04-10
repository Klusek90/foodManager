package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);
    Users findOneByUsername(String username);

}
