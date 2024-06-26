package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByBookingDate(LocalDate localDate);

    List<Booking> findAll();
}
