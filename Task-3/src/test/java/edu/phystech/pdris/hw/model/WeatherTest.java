package edu.phystech.pdris.hw.model;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {
    @Test
    void emptyConstructor() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";
        double avgTempC = 20.0;
        double maxWindKph = 5.0;
        String condition = "cool";

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setDate(date);
        weather.setAvgTempC(avgTempC);
        weather.setMaxWindKph(maxWindKph);
        weather.setCondition(condition);

        assertEquals(city, weather.getCity());
        assertEquals(date, weather.getDate());
        assertEquals(avgTempC, weather.getAvgTempC());
        assertEquals(maxWindKph, weather.getMaxWindKph());
        assertEquals(condition, weather.getCondition());
    }

    @Test
    void fields() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";
        double avgTempC = 20.0;
        double maxWindKph = 5.0;
        String condition = "cool";

        Weather weather = new Weather(date, city, avgTempC, maxWindKph, condition);

        assertEquals(city, weather.getCity());
        assertEquals(date, weather.getDate());
        assertEquals(avgTempC, weather.getAvgTempC());
        assertEquals(maxWindKph, weather.getMaxWindKph());
        assertEquals(condition, weather.getCondition());

        WeatherKey key = new WeatherKey(date, city);
        assertEquals(key, weather.weatherKey());
    }
}