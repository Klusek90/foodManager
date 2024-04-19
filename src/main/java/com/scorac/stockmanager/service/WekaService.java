package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.*;
import com.scorac.stockmanager.model.Entity.*;
import com.scorac.stockmanager.service.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.*;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

@Service
public class WekaService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private MadeRepository madeRepository;
    @Autowired
    private WasteRepository wasteRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ProductRepository productRepository;

    private Classifier model;

    public void loadModel(String path) throws Exception {
        this.model = (Classifier) SerializationHelper.read(path);
    }

    public double makePrediction(Instance instance) throws Exception {
        if (model == null) {
            throw new Exception("Model is not loaded. Please load the model first.");
        }
        return model.classifyInstance(instance);
    }

    public Instance createInstance(double temp, double humidity, double pressure, int bookings, int dayOfWeek, int month, int waste) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("temperature"));
        attributes.add(new Attribute("humidity"));
        attributes.add(new Attribute("pressure"));
        attributes.add(new Attribute("bookings"));
        attributes.add(new Attribute("dayOfWeek"));
        attributes.add(new Attribute("month"));
        attributes.add(new Attribute("waste"));

        Instances dataset = new Instances("Instance", attributes, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);

        Instance instance = new DenseInstance(1.0, new double[]{temp, humidity, pressure, bookings, dayOfWeek, month, waste});
        dataset.add(instance);

        return dataset.firstInstance();
    }

    public Instances collectDataForTraining() {
        List<Weather> weathers = weatherRepository.findAll();
        List<Made> madeList = madeRepository.findAll();
        List<Sale> sales = saleRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Waste> wastes = wasteRepository.findAll();
        List<Product> products = productRepository.findAll();

        Instances dataset = initializeDataset();

        products.forEach(product -> {
            weathers.forEach(weather -> {
                if (weather.getDate().isBefore(LocalDate.now().minusYears(2))) {
                    double[] instanceValues = new double[dataset.numAttributes()];
                    instanceValues[0] = weather.getTemperature();
                    instanceValues[1] = weather.getHumidity();
                    instanceValues[2] = weather.getPressure();

                    int bookingsTotal = 0;
                    for (Booking booking : bookings) {
                        if (booking.getBookingDate().equals(weather.getDate())) {
                            bookingsTotal += booking.getNumberOfGuest();
                        }
                    }

                    instanceValues[3] = bookingsTotal;
                    instanceValues[4] = weather.getDate().getDayOfWeek().getValue();
                    instanceValues[5] = weather.getDate().getMonthValue();

                    int wasteTotal = wastes.stream()
                            .filter(w -> w.getProduct().equals(product) && w.getDate().equals(weather.getDate()))
                            .mapToInt(Waste::getQuantity)
                            .sum();

                    instanceValues[6] = wasteTotal;

                    dataset.add(new DenseInstance(1.0, instanceValues));
                }
            });
        });

        return dataset;
    }

    private Instances initializeDataset() {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("temperature"));
        attributes.add(new Attribute("humidity"));
        attributes.add(new Attribute("pressure"));
        attributes.add(new Attribute("bookings"));
        attributes.add(new Attribute("dayOfWeek"));
        attributes.add(new Attribute("month"));
        attributes.add(new Attribute("waste"));

        return new Instances("ProductData", attributes, 0);
    }

    public void trainAndEvaluateModel() throws Exception {
        Instances data = collectDataForTraining();
        data.setClassIndex(data.numAttributes() - 1);

        LinearRegression model = new LinearRegression();
        model.buildClassifier(data);

        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(model, data, 10, new Random(1));

        saveModel(model, "model.model");
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }

    public void saveModel(Classifier model, String path) throws Exception {
        SerializationHelper.write(path, model);
    }
}
