package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "[A-Za-z0-9 ]*", message = "Name must only contain letters, numbers, and spaces.")
    private String name;

    private int numberOfGuest;

    private LocalDate bookingDate;

}
