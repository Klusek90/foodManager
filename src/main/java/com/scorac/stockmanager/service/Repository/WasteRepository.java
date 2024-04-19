package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WasteRepository extends JpaRepository <Waste, Long> {
    List<Waste> findAll();
    List<Waste> findAllByDate(LocalDate localDate);

}
