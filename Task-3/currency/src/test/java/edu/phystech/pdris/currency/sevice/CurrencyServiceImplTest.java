package edu.phystech.pdris.currency.sevice;

import edu.phystech.pdris.currency.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyServiceImplTest {
    @Autowired
    CurrencyServiceImpl currencyService;

    @Test
    void getDollarCurrencyLastDays() {
        List<Currency> currencies = currencyService.getDollarCurrencyLastDays(5);

        assertEquals(currencies.size(), 5);
        Instant instant = Instant.now();

        for (Currency currency : currencies) {
            assertTrue(currency.getDollar() < 90);
            assertTrue(currency.getDollar() > 60);
            assertNotEquals(null, currency.getDate());
        }
    }
}