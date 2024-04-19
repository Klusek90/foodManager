package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.model.Entity.*;
import com.scorac.stockmanager.service.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.classifiers.functions.LinearRegression;
import weka.core.DenseInstance;
import weka.core.Attribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WekaService {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MadeRepository madeRepository;
    @Autowired
    private WasteRepository wasteRepository;

    private LinearRegression model;

    public WekaService() {
        loadModel(); // Load model when service is instantiated
    }

    private void loadModel() {
        try {
            this.model = (LinearRegression) SerializationHelper.read("model.model");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load the model", e);
        }
    }

    public Double performPrediction(BigData data) {
        try {
            // Lista atrybutów zgodna z modelem
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("productId"));
            attributes.add(new Attribute("temperature"));
            attributes.add(new Attribute("humidity"));
            attributes.add(new Attribute("pressure"));
            attributes.add(new Attribute("bookings"));
            attributes.add(new Attribute("dayOfWeek"));
            attributes.add(new Attribute("month"));
            attributes.add(new Attribute("made_quantity"));
            attributes.add(new Attribute("waste"));
            attributes.add(new Attribute("made"));  // Zakładamy, że 'made' jest celem

            // Stworzenie nowego zbioru danych
            Instances dataset = new Instances("PredictionInstance", attributes, 0);
            dataset.setClassIndex(dataset.numAttributes() - 1);  // 'made' jest zmienną zależną

            // Stworzenie nowej instancji na podstawie danych
            double[] instanceValues = new double[]{
                    data.getProductId(),
                    data.getTemperature(),
                    data.getHumidity(),
                    data.getPressure(),
                    data.getBookings(),
                    data.getDayOfWeek(),
                    data.getMonthNumber(),
                    data.getMadeQuantity(),
                    data.getWasteQuantity(),
                    0  // wartość 'made' nie jest znana podczas predykcji
            };
            dataset.add(new DenseInstance(1.0, instanceValues));
            dataset.instance(0).setDataset(dataset);

            // Przewidywanie wartości 'made'
            double predictedMade = model.classifyInstance(dataset.instance(0));
            return  predictedMade;
        } catch (Exception e) {
            double error =0;
            return error;
        }
    }

    public Map<String, Double> preditionMap(LocalDate date) {
        // Create a new HashMap<String, Integer>
        Map<String, Double> productPredictions = new HashMap<>();
        List<Weather> weather = weatherRepository.findAllByDate(date);

        float temperature = 0;
        float humidity = 0;
        float pressure =0;

        if(weather.size() >0 ){
            temperature = weather.get(0).getTemperature();
            humidity = weather.get(0).getHumidity();
            pressure = weather.get(0).getPressure();
        }

        List<Booking> bookings = bookingRepository.findAllByBookingDate(date);
        int bookingNumber = 0;
        for(Booking booking : bookings){
            bookingNumber =+ booking.getNumberOfGuest();
        }
        int dayOfweek = date.getDayOfWeek().getValue();
        int monthNumber =  date.getMonthValue();
       //how much was made yesterday
        List<Made> madelist= madeRepository.findAllByCreated(date.minusDays(1));
        //how much was removed from the stock
        List<Waste> wasteList= wasteRepository.findAllByDate(date);
        List<Product> allproducts = productRepository.findAll();
        if(allproducts.size() > 0) {
            for (Product product : allproducts) {
                int created = 0;
                for (Made made : madelist) {
                    if (made.getProduct().getId() == product.getId()) {
                        created = +made.getAmount();
                    }
                }
                int wasted = 0;
                for (Waste waste : wasteList) {
                    if (waste.getProduct().getId() == product.getId()) {
                        wasted = +waste.getQuantity();
                    }
                }

                int id = product.getId().intValue();
                BigData data = new BigData(id, temperature, humidity, pressure, bookingNumber, dayOfweek, monthNumber, created, wasted);
                double productPrediction = Math.round(performPrediction(data)) ;
                productPredictions.put(product.getName(), productPrediction);
            }
        }
        // Return the map
        return productPredictions;
    }

}