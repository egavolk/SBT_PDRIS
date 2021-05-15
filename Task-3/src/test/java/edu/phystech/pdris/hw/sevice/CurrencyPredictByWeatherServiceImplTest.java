package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyPredictByWeatherServiceImplTest {
    @Autowired
    CurrencyPredictByWeatherServiceImpl predictService;

    @Test
    void predict() {
        Currency currency = predictService.predict();
        Instant instant = Instant.now();

        assertEquals(
                Util.getStringDateForPattern(instant.plus(1, ChronoUnit.DAYS), "yyyy-MM-dd"),
                currency.getDate());
        assertTrue(currency.getDollar() < 90);
        assertTrue(currency.getDollar() > 60);
    }
}