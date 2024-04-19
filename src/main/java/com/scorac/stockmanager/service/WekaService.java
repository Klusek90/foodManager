package com.scorac.stockmanager.service;

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

    public String performPrediction() {
        try {
            // List of attributes
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
            attributes.add(new Attribute("made"));  // Target attribute

            // Tworzenie zbioru danych
            Instances dataset = new Instances("TrainingData", attributes, 0);
            dataset.setClassIndex(dataset.numAttributes() - 1);  // 'made' jest zmienną zależną

            // Dodanie przykładowej instancji danych
            double[] instanceValue1 = new double[]{6, 13.27, 70.88, 1005.28, 125, 3, 10, 24, 413, 419};
            dataset.add(new DenseInstance(1.0, instanceValue1));

            double[] instanceValue2 = new double[]{20, 15.60, 60.53, 1029.61, 57, 1, 8, 144, 937, 156};
            dataset.add(new DenseInstance(1.0, instanceValue2));

            // Trenowanie modelu regresji liniowej
            LinearRegression model = new LinearRegression();
            model.buildClassifier(dataset);

            // Użycie modelu do przewidzenia wartości 'made' dla nowej instancji
            double[] newInstance = new double[]{6, 15.00, 65.00, 1010.00, 100, 4, 11, 50, 500, 0};
            Instances newData = new Instances("NewInstance", attributes, 0);
            newData.add(new DenseInstance(1.0, newInstance));
            newData.setClassIndex(newData.numAttributes() - 1);

            double predictedMade = model.classifyInstance(newData.instance(0));
            return "Predicted made: " + predictedMade;
        } catch (Exception e) {
            return "Error during prediction: " + e.getMessage();
        }
    }
}