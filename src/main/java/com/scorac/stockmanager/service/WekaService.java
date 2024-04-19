package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.service.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48; // Using J48 decision tree for demonstration
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.Attribute;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;


@Service
public class WekaService {

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

    public String performPrediction(BigData data) {
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
            return "Predicted made: " + predictedMade;
        } catch (Exception e) {
            return "Error during prediction: " + e.getMessage();
        }
    }
}