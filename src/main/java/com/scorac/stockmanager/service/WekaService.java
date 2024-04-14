package com.scorac.stockmanager.service;
import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.model.Entity.*;
import com.scorac.stockmanager.service.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private RecipeProductService recipeProductService;

    public WekaService(RecipeProductService recipeProductService) {
        this.recipeProductService = recipeProductService;
    }







    public List<BigData> bigDataSet() {
        LocalDate today = LocalDate.now();
        LocalDate dateToValidate;

        List<BigData> bigDataSet = new ArrayList<>();

        List<Weather> weathers = weatherRepository.findAll();
        List<Made> preparationMode = madeRepository.findAll();
        List<Sale> sales = saleRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<Waste> wastes = wasteRepository.findAll();
        List<Product> allproducts = productRepository.findAll();


        //createing 2 years data
        for (int i = 0; i < 730; i++) {
            dateToValidate = today.minusDays(i);

            for(int x=0; x< allproducts.size() ;x++){
                BigData bigDataRow = new BigData();
                bigDataRow.setProductid( allproducts.get(x).getId());
                bigDataRow.setMonthNumber(dateToValidate.getMonth().getValue());
                bigDataRow.setDayOfWeek(dateToValidate.getDayOfWeek().getValue());

                int made=0;
                for (int j = 0; j < preparationMode.size(); j++) {

                    if (preparationMode.get(j).getCreated().isEqual(dateToValidate) && allproducts.get(x).getId() == preparationMode.get(j).getProduct().getId()) {
                        made += preparationMode.get(j).getAmount();
                    }
                }
                bigDataRow.setMadeQuantity(made);

                for (int k = 0; k < weathers.size(); k++) {
                    if (weathers.get(k).getDate().isEqual(dateToValidate)) {
                        bigDataRow.setWeatherTemp(weathers.get(k).getTemperature());
                        bigDataRow.setWeatherHumidity(weathers.get(k).getHumidity());
                        bigDataRow.setWeatherPressure(weathers.get(k).getPressure());
                    }
                }

                int allBookings = 0;
                for (int l = 0; l < bookings.size(); l++) {
                    if (bookings.get(l).getBookingDate().isEqual(dateToValidate)) {
                        allBookings += bookings.get(l).getNumberOfGuest();

                    }
                }
                bigDataRow.setBookings(allBookings);

                //how much product was prepared that day and how much was wasted
                int waste = 0;
                for (int m = 0; m < wastes.size(); m++) {
                    if (wastes.get(m).getDate().isEqual(dateToValidate) && wastes.get(m).getProduct().getId() == allproducts.get(x).getId()) {
                        waste += wastes.get(m).getQuantity();
                    }
                }
                bigDataRow.setWaste(waste);

                        //how much of this product was sold that day
                int sold = 0;
                for (int n = 0; n < sales.size(); n++) {
                    if (sales.get(n).getDate().isEqual(dateToValidate)) {
                        List<RecipeProduct> productsInRecipie = recipeProductService.listforRecipe(sales.get(n).getRecipe().getRecipeId());
                        for (int o = 0; o < productsInRecipie.size(); o++) {
                            if (productsInRecipie.get(o).getProduct().getId() == allproducts.get(x).getId()) {
                                int productAmountOfSold = productsInRecipie.get(o).getQuantity() * sales.get(n).getMultiplicity();
                                sold += productAmountOfSold;
                            }
                        }
                    }
                }
                bigDataRow.setSaleQuantity(sold);

                bigDataSet.add(bigDataRow);
            }

        }
        return bigDataSet;
    }


    // creating csv
    public void writeListToCSV() {

        try {


        List<BigData> alldata = bigDataSet();

        FileWriter csvWriter = new FileWriter("BigData.scv");

        // Write header
        csvWriter.append("ProductID,WeatherTemp,WeatherHumidity,WeatherPressure,MadeQuantity,SaleQuantity,Bookings,DayOfWeek,DayOfMonth,Waste\n");

        // Write data
        for (BigData data : alldata) {
            csvWriter.append(String.valueOf(data.getProductid()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getWeatherTemp()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getWeatherHumidity()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getWeatherPressure()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getMadeQuantity()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getSaleQuantity()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getBookings()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getDayOfWeek()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getMonthNumber()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(data.getWaste()));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }catch (Exception e){
            System.out.println("Error while creating CSV ");
        }
    }

    public Instances convertListToInstances() {
        List<BigData> dataList = bigDataSet();

        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("productid"));
        attributes.add(new Attribute("weatherTemp"));
        attributes.add(new Attribute("weatherHumidity"));
        attributes.add(new Attribute("weatherPressure"));
        attributes.add(new Attribute("madeQuantity"));
        attributes.add(new Attribute("saleQuantity"));
        attributes.add(new Attribute("bookings"));
        attributes.add(new Attribute("dayOfWeek"));
        attributes.add(new Attribute("monthNumber"));
        attributes.add(new Attribute("waste"));

        Instances dataset = new Instances("ProductData", attributes, dataList.size());
        dataset.setClassIndex(dataset.attribute("saleQuantity").index()); // Setting saleQuantity as the target variable for sales prediction

        for (BigData data : dataList) {
            double[] instanceValue = new double[dataset.numAttributes()];
            instanceValue[0] = data.getProductid();
            instanceValue[1] = data.getWeatherTemp();
            instanceValue[2] = data.getWeatherHumidity();
            instanceValue[3] = data.getWeatherPressure();
            instanceValue[4] = data.getMadeQuantity();
            instanceValue[5] = data.getSaleQuantity();
            instanceValue[6] = data.getBookings();
            instanceValue[7] = data.getDayOfWeek();
            instanceValue[8] = data.getMonthNumber();
            instanceValue[9] = data.getWaste();
            dataset.add(new DenseInstance(1.0, instanceValue));
        }
        return dataset;
    }

    public String trainAndEvaluateModel() {
        try {
            Instances data = convertListToInstances();
            // Set the last attribute as the target
            data.setClassIndex(data.numAttributes() - 1);

            // Initialize and build the classifier
            LinearRegression model = new LinearRegression();
            model.buildClassifier(data);

            // Evaluate the model
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(model, data, 10, new Random(1)); // 10-fold cross-validation

            return eval.toSummaryString("\nResults\n======\n", false);

        } catch (Exception e) {
            return "Faild to process data";
        }

    }


}











