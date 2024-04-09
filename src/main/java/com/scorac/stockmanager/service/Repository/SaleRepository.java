package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
