package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Weather;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherServiceImplTest {
    @Autowired
    private WeatherServiceImpl weatherService;

    public WeatherServiceImplTest() {}

    @Test
    void getWeatherLastDays() {
        List<Weather> weather1 = weatherService.getWeatherLastDays(5);
        List<Weather> weather2 = weatherService.getWeatherLastDays(5, "Moscow");

        assertEquals(5, weather1.size());
        assertEquals(5, weather2.size());
        Instant instant = Instant.now();

        for (int i = 0; i < weather1.size(); ++i) {
            assertEquals(weather1.get(i).getDate(), weather2.get(i).getDate());
            assertEquals(weather1.get(i).getCity(), weather2.get(i).getCity());
            assertEquals(weather1.get(i).getMaxWindKph(), weather2.get(i).getMaxWindKph());
            assertEquals(weather1.get(i).getAvgTempC(), weather2.get(i).getAvgTempC());
            assertEquals(weather1.get(i).getCondition(), weather2.get(i).getCondition());

            assertEquals("Moscow", weather2.get(i).getCity());
            assertEquals(
                    Util.getStringDateForPattern(instant.minus(i, ChronoUnit.DAYS), "yyyy-MM-dd"),
                    weather2.get(i).getDate());
        }

        for (Weather weather : weather1) {
            assertNotEquals(weather.getDate(), null);
            assertNotEquals(weather.getCity(), null);
            assertNotEquals(weather.getMaxWindKph(), null);
            assertNotEquals(weather.getAvgTempC(), null);
            assertNotEquals(weather.getCondition(), null);
        }
    }

    @Test
    void getTomorrowWeather() {
        String city = "Vologda";
        String defaultCity = "Moscow";
        Instant instant = Instant.now();

        Weather weather = weatherService.getTomorrowWeather(city);
        assertEquals(city, weather.getCity());
        assertEquals(
                Util.getStringDateForPattern(instant.plus(1, ChronoUnit.DAYS), "yyyy-MM-dd"),
                weather.getDate());

        weather = weatherService.getTomorrowWeather();
        assertEquals(defaultCity, weather.getCity());
        assertEquals(
                Util.getStringDateForPattern(instant.plus(1, ChronoUnit.DAYS), "yyyy-MM-dd"),
                weather.getDate());
    }
}