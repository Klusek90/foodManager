package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Made;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrepCreatedRepository extends JpaRepository<Made,Long> {
    List<Made> findAll();
}
