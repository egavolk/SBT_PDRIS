package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.model.Weather;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.time.Instant;
import java.util.List;

@Service
public class CurrencyPredictByWeatherServiceImpl implements CurrencyPredictByWeatherService {
    private final WeatherService weatherService;
    private final CurrencyService currencyService;
    private final SimpleRegression regression = new SimpleRegression();

    private static final int TRAIN_SIZE = 7;

    public CurrencyPredictByWeatherServiceImpl(WeatherService weatherService, CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
    }

    @Override
    public Currency predict() {
        List<Weather> weathers = weatherService.getWeatherLastDays(TRAIN_SIZE);
        List<Currency> currencies = currencyService.getDollarCurrencyLastDays(TRAIN_SIZE);
        for (int i = 0; i < TRAIN_SIZE; ++i) {
            regression.addData(weathers.get(i).getAvgTempC(), currencies.get(i).getDollar());
        }

        double dollar = regression.predict(weatherService.getTomorrowWeather().getAvgTempC());
        return new Currency(Util.getStringDateForPattern(Instant.now(), "yyyy-MM-dd"), dollar);
    }
}
