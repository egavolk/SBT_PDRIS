package edu.phystech.pdris.predict.service.outer_api;


import edu.phystech.pdris.predict.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class APIWeatherServiceImpl implements APIWeatherService {
    private static final String WEATHER_API_URL = "http://weather:8081/weather/?days=%d&city=%s";
    private static final String WEATHER_TOMORROW_API_URL = "http://weather:8081/weather/tomorrow/?city=%s";

    private final RestTemplate restTemplate;

    @Autowired
    public APIWeatherServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public List<Weather> getWeatherLastDays(int days, String city) {
        ResponseEntity<Weather[]> response = restTemplate.getForEntity(
                String.format(WEATHER_API_URL, days, city),
                Weather[].class);

        return new ArrayList<>(Arrays.asList(response.getBody()));
    }

    @Override
    public Weather getTomorrowWeather(String city) {
        ResponseEntity<Weather> response = restTemplate.getForEntity(
                String.format(WEATHER_TOMORROW_API_URL, city),
                Weather.class);

        return response.getBody();
    }
}
