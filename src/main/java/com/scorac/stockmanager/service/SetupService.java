package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Setup;
import com.scorac.stockmanager.service.Repository.SetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupService {
    @Autowired
    private SetupRepository setupRepository; // Assume this is your JPA repository

    public Setup getSetup() {
        // This is a simplified approach. You might want to handle this differently based on your application's needs.
        return setupRepository.findAll().stream().findFirst().orElse(new Setup());
    }

    public void updateSetup(Setup setup) {
        // Since there's only ever one entry, you can simply save it. The repository handles the update if the ID (restaurantName) exists.
        setupRepository.save(setup);
    }
}
