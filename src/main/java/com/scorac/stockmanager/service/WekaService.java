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
    @Autowired
    private RecipeProductRepository recipeProductRepository;

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

//    public Instance createInstance(BigData data) {
//        ArrayList<Attribute> attributes = new ArrayList<>();
//        attributes.add(new Attribute("temperature"));
//        attributes.add(new Attribute("humidity"));
//        attributes.add(new Attribute("pressure"));
//        attributes.add(new Attribute("bookings"));
//        attributes.add(new Attribute("dayOfWeek"));
//        attributes.add(new Attribute("month"));
//        attributes.add(new Attribute("waste"));
//
//        Instances dataset = new Instances("Instance", attributes, 0);
//        dataset.setClassIndex(dataset.numAttributes() - 1);
//
//        Instance instance = new DenseInstance(attributes.size());
//        instance.setValue(attributes.get(0), data.getWeatherTemp());
//        instance.setValue(attributes.get(1), data.getWeatherHumidity());
//        instance.setValue(attributes.get(2), data.getWeatherPressure());
//        instance.setValue(attributes.get(3), data.getBookings());
//        instance.setValue(attributes.get(4), data.getDayOfWeek());
//        instance.setValue(attributes.get(5), data.getMonthNumber());
//
//        if (data.getWaste() == 0) {
//            instance.setMissing(attributes.get(6));
//        } else {
//            instance.setValue(attributes.get(6), data.getWaste());
//        }
//
//        dataset.add(instance);
//
//        return dataset.firstInstance();
//    }


    public Instances collectDataForTraining() {
        List<Weather> weathers = weatherRepository.findAll();
        List<Made> madeList = madeRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Waste> wastes = wasteRepository.findAll();
        List<Product> products = productRepository.findAll();

        Instances dataset = initializeDataset();

        for (Product product : products) {
            for (Weather weather : weathers) {
                if (weather.getDate().isBefore(LocalDate.now().minusYears(2))) {
                    double[] instanceValues = new double[dataset.numAttributes()];
                    instanceValues[0] = product.getId();
                    instanceValues[1] = weather.getTemperature();
                    instanceValues[2] = weather.getHumidity();
                    instanceValues[3] = weather.getPressure();

                    int bookingsTotal = bookings.stream()
                            .filter(b -> b.getBookingDate().equals(weather.getDate()))
                            .mapToInt(Booking::getNumberOfGuest)
                            .sum();
                    instanceValues[4] = bookingsTotal;

                    instanceValues[5] = weather.getDate().getDayOfWeek().getValue();
                    instanceValues[6] = weather.getDate().getMonthValue();

                    int madeQuantity = madeList.stream()
                            .filter(m -> m.getProduct().equals(product) && m.getCreated().isEqual(weather.getDate()))
                            .mapToInt(Made::getAmount)
                            .sum();
                    instanceValues[7] = madeQuantity;

                    int wasteTotal = wastes.stream()
                            .filter(w -> w.getProduct().equals(product) && w.getDate().isEqual(weather.getDate()))
                            .mapToInt(Waste::getQuantity)
                            .sum();
                    instanceValues[8] = wasteTotal;

                    // Dodanie danych o sprzedaży
                    Map<Long, Integer> salesData = aggregateProductSales(weather.getDate());
                    int salesTotal = salesData.getOrDefault(product.getId(), 0);
                    instanceValues[9] = salesTotal;

                    dataset.add(new DenseInstance(1.0, instanceValues));
                }
            }
        }

        return dataset;
    }

    private Map<Long, Integer> aggregateProductSales(LocalDate date) {
        List<Sale> sales = saleRepository.findAllByDate(date);
        Map<Long, Integer> productSales = new HashMap<>();

        for (Sale sale : sales) {
            List<RecipeProduct> recipeProducts = recipeProductRepository.findAllByRecipe_RecipeId(sale.getRecipe().getRecipeId());
            for (RecipeProduct rp : recipeProducts) {
                Long productId = rp.getProduct().getId();
                int soldQuantity = rp.getQuantity() * sale.getMultiplicity();
                productSales.put(productId, productSales.getOrDefault(productId, 0) + soldQuantity);
            }
        }

        return productSales;
    }

    private Instances initializeDataset() {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("productId"));
        attributes.add(new Attribute("temperature"));
        attributes.add(new Attribute("humidity"));
        attributes.add(new Attribute("pressure"));
        attributes.add(new Attribute("bookingsTotal"));
        attributes.add(new Attribute("dayOfWeek"));
        attributes.add(new Attribute("month"));
        attributes.add(new Attribute("madeQuantity"));
        attributes.add(new Attribute("wasteTotal"));
        attributes.add(new Attribute("salesTotal")); // Dodajemy atrybut dla sprzedaży

        Instances dataset = new Instances("ProductData", attributes, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);
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

    private Instance createInstanceForPrediction(BigData data) {
        ArrayList<Attribute> attributes = initializeAttributes();
        Instances dataset = new Instances("PredictionInstance", attributes, 1);
        dataset.setClassIndex(dataset.numAttributes() - 1);

        Instance instance = new DenseInstance(attributes.size());
        instance.setValue(attributes.get(0), data.getProductid());
        instance.setValue(attributes.get(1), data.getWeatherTemp());
        instance.setValue(attributes.get(2), data.getWeatherHumidity());
        instance.setValue(attributes.get(3), data.getWeatherPressure());
        instance.setValue(attributes.get(4), data.getBookings());
        instance.setValue(attributes.get(5), data.getDayOfWeek());
        instance.setValue(attributes.get(6), data.getMonthNumber());
        instance.setValue(attributes.get(7), data.getCurrentStock());

        dataset.add(instance);
        return dataset.firstInstance();
    }
}
