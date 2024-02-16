package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Weather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class WeatherService {
    private final String apiKey = "e369dcbf19ddeb2042fe020c729ee7ec";

    public Weather getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        Weather weather = new Weather();
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        weather.setTemperature(main.get("temp").toString());

//        Must match with api json!
//        Map<String, Object>[] weatherInfo = (Map<String, Object>[]) response.get("weather");
//        weather.setDescription(weatherInfo[0].get("description").toString());
//        weather.setDescription("ddzyscie");

        return weather;
    }
}
