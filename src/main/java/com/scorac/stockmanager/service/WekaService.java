package com.scorac.stockmanager.service;
import com.scorac.stockmanager.service.Repository.WeatherRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
@Service
public class WekaService {

    @Autowired
    private WeatherRepository weatherRepository;
//    private MadeR
}
