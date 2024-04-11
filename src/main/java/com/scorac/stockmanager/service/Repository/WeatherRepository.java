package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findAllByDate(LocalDate date);
}
