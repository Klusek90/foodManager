package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.Entity.Booking;
import com.scorac.stockmanager.model.Entity.Users;
import com.scorac.stockmanager.service.BookingService;
import com.scorac.stockmanager.service.Repository.BookingRepository;
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

    public BookingRest(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings/{date}")
    public List<Booking> editUser(@PathVariable("date")LocalDate date) {
       List<Booking> bookings = bookingService.bookinsByDate(date);
        return bookings;
    }


}
