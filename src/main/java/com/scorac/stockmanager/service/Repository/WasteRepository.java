package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepository extends JpaRepository <Waste, Long> {
}
