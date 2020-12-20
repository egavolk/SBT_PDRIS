package edu.phystech.pdris.hw.controller;

import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.model.Weather;
import edu.phystech.pdris.hw.sevice.CurrencyService;
import edu.phystech.pdris.hw.sevice.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Weather> getWeatherLastDays(
            @RequestParam(required = false, defaultValue = "1") Integer days,
            @RequestParam(required = false, defaultValue = "Moscow") String city) {
        return weatherService.getWeatherLastDays(days, city);
    }
}