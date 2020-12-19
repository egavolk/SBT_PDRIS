package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.storage.CurrencyStorage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String CURRENCY_API_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private static final String DOLLAR_BLOCK_START = "<Valute ID=\"R01235\">";
    private static final String DOLLAR_VALUE_START = "<Value>";
    private static final String DOLLAR_VALUE_END = "</Value>";

    private final CurrencyStorage currencyStorage;
    private final RestTemplate restTemplate;

    public CurrencyServiceImpl(CurrencyStorage currencyStorage, RestTemplateBuilder builder) {
        this.currencyStorage = currencyStorage;
        this.restTemplate = builder.build();
    }

    @Override
    public List<Currency> getDollarCurrencyLastDays(int daysCount) {
        List<Currency> values = new ArrayList<>();
        for (int i = 0; i < daysCount; ++i) {
            values.add(getDollarCurrencyByDate(Instant.now().minus(i, ChronoUnit.DAYS)));
        }
        return values;
    }

    public Currency getDollarCurrencyByDate(Instant date) {
        String dateString = Util.getStringDateForPattern(date, "yyyy-MM-dd");
        if (currencyStorage.isDateExist(dateString)) {
            return currencyStorage.getCurrencyByDate(dateString);
        }

        ResponseEntity<String> response = restTemplate.getForEntity(
                CURRENCY_API_URL + Util.getStringDateForPattern(date, "dd/MM/yyyy"),
                String.class);
        Currency currency = new Currency(dateString, extractDollarCurrencyValue(response));
        currencyStorage.addCurrency(currency);
        return currency;
    }

    private double extractDollarCurrencyValue(ResponseEntity<String> response) {
        String body = response.getBody();
        int dollarBlockStartIndex = body.indexOf(DOLLAR_BLOCK_START);
        int dollarValueStartIndex = body.indexOf(DOLLAR_VALUE_START, dollarBlockStartIndex);
        int dollarValueEndIndex = body.indexOf(DOLLAR_VALUE_END, dollarValueStartIndex);
        String res = body.substring(dollarValueStartIndex + DOLLAR_VALUE_START.length(),
                dollarValueEndIndex);
        return Double.parseDouble(res.replace(',', '.'));
    }
}
