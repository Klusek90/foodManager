package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAll();

    List<Sale> findAllByDate(LocalDate localDate);
}
