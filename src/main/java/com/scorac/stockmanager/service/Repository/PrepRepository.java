package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Prep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrepRepository extends JpaRepository <Prep, Long> {

    List<Prep> findAll();
}
