package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Prep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrepRepository extends JpaRepository <Prep, Long> {

    List<Prep> findAll();
//    List<Prep> findAllByProduct(Product product);

    List<Prep> findByProduct_Id(Long productId);

    List<Prep> findAllById(Long id);

}
