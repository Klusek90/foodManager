package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//https://datatables.net/manual/server-side#:~:text=Server%2Dside%20processing%20in%20DataTables,get%20its%20Ajax%20data%20from.
//https://www.baeldung.com/spring-data-jpa-pagination-sorting
public interface StockRepository  extends JpaRepository<Product, Long> {

    List<Product> findAll();
}
