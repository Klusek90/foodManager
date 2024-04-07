package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQuantityRepository  extends JpaRepository <ProductQuantity, Long> {

}
