package com.scorac.stockmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="setup")
public class Setup {

    @Id
    private String restaurantName;
    private String chatAPI;
    private String restaurantInfo;
    private String link;

}