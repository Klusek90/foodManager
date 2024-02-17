package com.scorac.stockmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name="dayEvent")
public class DayEvent {

    @Id
    @Temporal(TemporalType.DATE) // Specifies the precision of the date attribute
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Specifies the format for form submission
    private Date date;

    private String note;

    private int participants;


}
