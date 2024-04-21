package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.Entity.Waste;
import com.scorac.stockmanager.service.Repository.WasteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
@SpringBootTest
public class WasteRepoisotyTest {

    @MockBean
    private WasteRepository wasteRepository;

    private LocalDate date = LocalDate.of(2024,4,20);
    private Product product= new Product();

    @BeforeEach
    public void setUp() {
        // Configure mock behavior if needed
        when(wasteRepository.findAll()).thenReturn(Arrays.asList(
                new Waste(1l, date, 200,product),
                new Waste(2L, date.plusDays(2), 300, product),
                new Waste(3L, date.plusDays(6),500,product)
        ));
        when(wasteRepository.findAllByDate(date)).thenReturn(Arrays.asList(
                new Waste(1l, date, 200,product)
        ));
    }

    @Test
    public void testFindAllMethodInWasteRepository(){
        List<Waste> wasteList= wasteRepository.findAll();
        Assertions.assertEquals(wasteList.size(),3);
        Assertions.assertEquals(wasteList.get(1).getQuantity(), 300);
    }

    @Test void testFindByDateMethodWasteRepository(){
        List<Waste> waste = wasteRepository.findAllByDate(date);
        Assertions.assertEquals(waste.size(),1);
    }
}
