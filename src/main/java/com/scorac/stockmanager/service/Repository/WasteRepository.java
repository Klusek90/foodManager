package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasteRepository extends JpaRepository <Waste, Long> {
    List<Waste> findAll();
}
