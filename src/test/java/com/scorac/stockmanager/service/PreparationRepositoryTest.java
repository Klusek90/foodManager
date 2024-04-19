package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PreparationRepositoryTest {

    @Autowired
    private PrepRepository prepRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void PrepRepository_Save_Prep(){
        LocalDate date = LocalDate.now();
        Product product = new Product();
        Product product2 = new Product();

        entityManager.persist(product); // Save product to database
        entityManager.persist(product2); // Save product2 to database

        Prep prep = new Prep(1L, date, date.plusDays(3), 500, product);
        Prep prep2 = new Prep(2L, date, date.plusDays(1), 700, product2);
        // Act
        prepRepository.save(prep);
        prepRepository.save(prep2);
        // Assert
        List<Prep> list = prepRepository.findAll();
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1L, list.get(0).getId());
        Assertions.assertEquals(500, list.get(0).getAmount());
        Assertions.assertEquals(2L, list.get(1).getId());
        Assertions.assertEquals(700, list.get(1).getAmount());
    }



}
