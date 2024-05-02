package com.scorac.stockmanager.service.controller;

import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(StockLevelControlTest.class)
public class StockLevelControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService; // Mocked dependency

    @Test
    void stockLevelTest() throws Exception {
        List<Stock> list = new ArrayList<>();
        Stock stock1 = new Stock("ogorek", "warzywo", 200, LocalDate.of(2024, Month.APRIL, 29), LocalDate.of(2024, Month.MAY, 3), 4, 2L);
        Stock stock2 = new Stock("pomidor", "warzywo", 300, LocalDate.of(2024, Month.APRIL, 30), LocalDate.of(2024, Month.MAY, 2), 2, 1L);
        list.add(stock1);
        list.add(stock2);

        // Mock the behavior of stockService.currentStock()
        when(stockService.currentStock()).thenReturn(list);

        // Perform the request and expect a 200 (OK) status
        mockMvc.perform(get("/stock/data"))
                .andExpect(status().is(401));
    }
}
