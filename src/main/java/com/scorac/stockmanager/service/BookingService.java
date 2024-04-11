package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Booking;
import com.scorac.stockmanager.service.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    public List<Booking> allBookings(){
        return bookingRepository.findAll();
    }

    public List<Booking> bookinsByDate(LocalDate date){
        return bookingRepository.findAllByBookingDate(date);
    }

    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }

}
