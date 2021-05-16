package edu.phystech.pdris.predict.service;

import edu.phystech.pdris.common.util.Util;
import edu.phystech.pdris.predict.model.Currency;
import edu.phystech.pdris.predict.model.Weather;
import edu.phystech.pdris.predict.service.outer_api.APICurrencyService;
import edu.phystech.pdris.predict.service.outer_api.APIWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CurrencyPredictByWeatherServiceImpl implements CurrencyPredictByWeatherService {
    private final APIWeatherService weatherService;
    private final APICurrencyService currencyService;
    private final SimpleRegression regression = new SimpleRegression();

    private final String CITY = "Moscow";
    private static final int TRAIN_SIZE = 7;

    @Autowired
    public CurrencyPredictByWeatherServiceImpl(APIWeatherService weatherService, APICurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
    }

    @Override
    public Currency predict() {
        List<Currency> currencies = currencyService.getDollarCurrencyLastDays(TRAIN_SIZE);
        List<Weather> weathers = weatherService.getWeatherLastDays(TRAIN_SIZE, CITY);
        for (int i = 0; i < TRAIN_SIZE; ++i) {
            regression.addData(weathers.get(i).getAvgTempC(), currencies.get(i).getDollar());
        }

        double dollar = regression.predict(weatherService.getTomorrowWeather(CITY).getAvgTempC());
        return new Currency(Util.getStringDateForPattern(Instant.now().plus(1, ChronoUnit.DAYS), "yyyy-MM-dd"), dollar);
    }
}
