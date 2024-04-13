package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Made;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MadeRepository extends JpaRepository<Made,Long> {
    List<Made> findAll();
}
