package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//https://datatables.net/manual/server-side#:~:text=Server%2Dside%20processing%20in%20DataTables,get%20its%20Ajax%20data%20from.
//https://www.baeldung.com/spring-data-jpa-pagination-sorting
public interface StockRepository  extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Product findByName(String name);
    Product findOneById(Long id);
}
