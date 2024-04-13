package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Weather;
import com.scorac.stockmanager.service.Repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepositoryMock;


    @Test
    public void weatherTest() {
        LocalDate today = LocalDate.now();
        Weather weather= weatherService.getWeather("Rugby");

        assertEquals(weather.getDate(), today.plusDays(1));

    }
}