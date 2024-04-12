package com.scorac.stockmanager.service;
import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.model.Entity.*;
import com.scorac.stockmanager.service.Repository.*;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
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
        LocalDate today = LocalDate.now();
        LocalDate previusDate;

        List<Weather> weathers = weatherRepository.findAll();
        List<Made> preparationMde= prepCreatedRepository.findAll();
        List<Sale> sales= saleRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Waste> wastes = wasteRepository.findAll();

        //2 years data
        for(int i =0; i<730; i++){
            previusDate = today.minusDays(i);
            if()


        }


        List<BigData> dataset= new ArrayList<>();







        return  dataset;

    }


}
