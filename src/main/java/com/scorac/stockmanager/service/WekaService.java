package com.scorac.stockmanager.service;
import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.model.Entity.*;
import com.scorac.stockmanager.service.Repository.*;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class WekaService {

    @Autowired
    private WeatherRepository weatherRepository;
    private PrepCreatedRepository prepCreatedRepository;
    private WasteRepository wasteRepository;
    private SaleRepository saleRepository;
    private BookingRepository bookingRepository;

    public List<BigData> bigDataSet(){

        List<Weather> weathers = weatherRepository.findAll();
        List<Made> preparationMde= prepCreatedRepository.findAll();
        List<Sale> sales= saleRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Waste> wastes = wasteRepository.findAll();






        List<BigData> dataset= new ArrayList<>();
        return  dataset;

    }


}
