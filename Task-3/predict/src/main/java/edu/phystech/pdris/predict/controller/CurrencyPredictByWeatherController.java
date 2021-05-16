package edu.phystech.pdris.predict.controller;

import edu.phystech.pdris.predict.model.Currency;
import edu.phystech.pdris.predict.service.CurrencyPredictByWeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyPredictByWeatherController {
    private final CurrencyPredictByWeatherService predictService;

    public CurrencyPredictByWeatherController(CurrencyPredictByWeatherService predictService) {
        this.predictService = predictService;
    }

    @GetMapping(value = "/predict", produces =  MediaType.APPLICATION_JSON_VALUE)
    public Currency getDollarCurrencyLastDays(@RequestParam(required = false, defaultValue = "1") Integer days) {
        return predictService.predict();
    }
}
