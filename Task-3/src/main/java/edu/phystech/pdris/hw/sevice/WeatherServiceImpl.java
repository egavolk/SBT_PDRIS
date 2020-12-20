package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.model.Weather;
import edu.phystech.pdris.hw.model.WeatherKey;
import edu.phystech.pdris.hw.model.WeatherResponse;
import edu.phystech.pdris.hw.storage.WeatherStorage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String weatherAPIKey;
    private final String DEFAULT_CITY = "Moscow";
    private static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/history.json?dt=%s&q=%s&key=%s";

    private final WeatherStorage weatherStorage;
    private final RestTemplate restTemplate;

    public WeatherServiceImpl(WeatherStorage weatherStorage, RestTemplateBuilder builder) {
        this.weatherStorage = weatherStorage;
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
        if (weatherStorage.isWeatherKeyExist(weatherKey)) {
            return weatherStorage.getWeatherByKey(weatherKey);
        }

        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(
                String.format(WEATHER_API_URL, dateString, city, weatherAPIKey),
                WeatherResponse.class);

        Weather weather = response.getBody().getWeather();
        weatherStorage.addWeather(weather);
        return weather;
    }
}
