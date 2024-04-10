package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrepRepository extends JpaRepository <Prep, Long> {

    List<Prep> findAll();
//    List<Prep> findAllByProduct(Product product);

    List<Prep> findByProduct_Id(Long productId);
}
