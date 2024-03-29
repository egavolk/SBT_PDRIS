package edu.phystech.pdris.weather.service;

import edu.phystech.pdris.weather.model.Weather;
import edu.phystech.pdris.weather.model.WeatherKey;
import edu.phystech.pdris.common.util.Util;
import edu.phystech.pdris.weather.model.WeatherResponse;
import edu.phystech.pdris.weather.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String weatherAPIKey;
    private static final String DEFAULT_CITY = "Moscow";
    private static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/history.json?dt=%s&q=%s&key=%s";

    private final WeatherRepo weatherRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(WeatherRepo weatherRepo, RestTemplateBuilder builder) {
        this.weatherRepo = weatherRepo;
        this.restTemplate = builder.build();
    }

    @Override
    public List<Weather> getWeatherLastDays(int daysCount) {
        return getWeatherLastDays(daysCount, DEFAULT_CITY);
    }

    @Override
    public List<Weather> getWeatherLastDays(int daysCount, String city) {
        List<Weather> values = new ArrayList<>();
        for (int i = 0; i < daysCount; ++i) {
            values.add(getWeatherByDateAndCity(Instant.now().minus(i, ChronoUnit.DAYS), city));
        }
        return values;
    }

    @Override
    public Weather getTomorrowWeather() {
        return getTomorrowWeather(DEFAULT_CITY);
    }

    @Override
    public Weather getTomorrowWeather(String city) {
        return getWeatherByDateAndCity(Instant.now().plus(1, ChronoUnit.DAYS), city);
    }

    private Weather getWeatherByDateAndCity(Instant date, String city) {
        String dateString = Util.getStringDateForPattern(date, "yyyy-MM-dd");
        WeatherKey weatherKey = new WeatherKey(dateString, city);
        Optional<Weather> findRes = weatherRepo.findById(weatherKey);
        if (findRes.isPresent()) {
            return findRes.get();
        }

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(
                String.format(WEATHER_API_URL, dateString, city, weatherAPIKey),
                WeatherResponse.class);

        Weather weather = response.getBody().getWeather();
        weatherRepo.save(weather);
        return weather;
    }
}
