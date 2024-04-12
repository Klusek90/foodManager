package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Weather;
import com.scorac.stockmanager.model.TDO.WeatherDTO;
import com.scorac.stockmanager.service.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.decode;
import static java.lang.Integer.parseInt;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private final String apiKey = "e369dcbf19ddeb2042fe020c729ee7ec";

    public Weather getWeather(String city) {

        RestTemplate restTemplate = new RestTemplate();
        //todays weather
    //        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        //weather for tommorow
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey + "&units=metric";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        float temperature = 0;
        int humidity = 0;
        int pressure = 0;

        if (response != null && response.containsKey("list")) {
            List<Map<String, Object>> forecastList = (List<Map<String, Object>>) response.get("list");
            for (Map<String, Object> forecast : forecastList) {
                // Extract the weather data for today
                Instant forecastDateTime = Instant.ofEpochSecond((Integer) forecast.get("dt"));
                ZonedDateTime forecastZonedDateTime = forecastDateTime.atZone(ZoneId.systemDefault());
                LocalDate forecastDate = forecastZonedDateTime.toLocalDate();
                LocalDate today = LocalDate.now();

                if (forecastDate.equals(today)) {
                    // Extract temperature, humidity, and pressure from this forecast entry
                    Map<String, Object> mainData = (Map<String, Object>) forecast.get("main");
                    temperature = ((Number) mainData.get("temp")).floatValue();
                    humidity = ((Number) mainData.get("humidity")).intValue();
                    pressure = ((Number) mainData.get("pressure")).intValue();

                    // Output the weather data for today
                    System.out.println("Today's weather forecast:");
                    System.out.println("Temperature: " + temperature);
                    System.out.println("Humidity: " + humidity);
                    System.out.println("Pressure: " + pressure);
                    break; // Exit the loop once today's forecast is found
                }
            }
        }

        LocalDate timestamp = LocalDate.now().plusDays(1);
        Weather weather = new Weather();
        weather.setHumidity(humidity);
        weather.setTemperature(temperature);
        weather.setPressure(pressure);
        weather.setDate(timestamp);

        return weather;
    }

    //Weather for next day- run every day at 17:00
    @Scheduled(cron = "0 0 17 * * ?")
    public void saveWeatherDataDaily() {
       Weather weather = getWeather("Rugby");
       weatherRepository.save(weather);
    }

    public WeatherDTO checkWeather(LocalDate date){
        List<Weather> weatherlist= weatherRepository.findAllByDate(date);
        WeatherDTO weatherDTO = new WeatherDTO();
        if(weatherlist.size()>0){
            String description = condition(weatherlist.get(0).getTemperature(), weatherlist.get(0).getHumidity(), weatherlist.get(0).getPressure());
            weatherDTO.setDescription(description);
            weatherDTO.setTemp(weatherlist.get(0).getTemperature());
            return weatherDTO;
        }else {
            return new WeatherDTO();
        }

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
