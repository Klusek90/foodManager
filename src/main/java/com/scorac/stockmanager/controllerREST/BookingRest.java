package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.Entity.Booking;
import com.scorac.stockmanager.model.Entity.Users;
import com.scorac.stockmanager.model.TDO.WeatherDTO;
import com.scorac.stockmanager.service.BookingService;
import com.scorac.stockmanager.service.Repository.BookingRepository;
import com.scorac.stockmanager.service.WeatherService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookingRest {

    private BookingService bookingService;

    private WeatherService weatherService;

    public BookingRest(BookingService bookingService, WeatherService weatherService) {
        this.bookingService = bookingService;
        this.weatherService = weatherService;
    }

    @GetMapping("/bookings/{date}")
    public List<Booking> checkbooking(@PathVariable("date")LocalDate date) {
       List<Booking> bookings = bookingService.bookinsByDate(date);
        return bookings;
    }

    @GetMapping("/weatherCondition/{date}")
    public WeatherDTO checkweather(@PathVariable("date")LocalDate date){
        WeatherDTO weatherForDay = weatherService.checkWeather(date);
        return weatherForDay;
    }

}
