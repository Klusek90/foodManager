package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@Service
public class WeatherService {
    private final String apiKey = "e369dcbf19ddeb2042fe020c729ee7ec";

    public Weather getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

//        Weather weather = new Weather();
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        float temp =  parseFloat(main.get("temp").toString());
        int humidity = parseInt(main.get("humidity").toString());
        int pressure = parseInt(main.get("pressure").toString());
        String description = condition(temp, humidity, pressure);
        String currentDate = LocalDate.now().toString();


        Weather weather = new Weather(String.valueOf(temp), description, currentDate);

        return weather;
    }

    private String condition(float temp, int humidity, int pressure){

        String description;

        if(temp > 20 && humidity < 60) {
//            imageSrc = "sun.png";
            description = "Sunny and warm";
        } else if(temp <= 20 && temp >= 10 && humidity > 60) {
//            imageSrc = "clouds.png";
            description = "Cloudy or overcast";
        } else if(humidity > 80 && temp < 20 && temp >= 5) {
//            imageSrc = "rain.png";
            description = "Rain";
        } else if(temp > 18 && humidity > 80 && pressure < 1010) {
//            imageSrc = "thunderstorm.png";
            description = "Thunderstorms";
        } else if(temp < 0 && humidity > 80) {
//            imageSrc = "snow.png";
            description = "Snow";
        } else if(Math.abs(temp - humidity) < 5) {
//            imageSrc = "fog.png";
            description = "Fog";
        } else {
//            imageSrc = "default.png";
            description = "Unknown";
        }

        return description;
    }
}
