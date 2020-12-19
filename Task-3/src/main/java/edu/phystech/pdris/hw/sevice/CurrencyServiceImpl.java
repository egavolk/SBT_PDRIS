package edu.phystech.pdris.hw.sevice;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String CURRENCY_API_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    private static final String DOLLAR_BLOCK_START = "<Valute ID=\"R01235\">";
    private static final String DOLLAR_VALUE_START = "<Value>";
    private static final String DOLLAR_VALUE_END = "</Value>";

    private final CurrencyRepo currencyRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepo currencyRepo, RestTemplate restTemplate) {
        this.currencyRepo = currencyRepo;
        this.restTemplate = restTemplate;
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
        Optional<Currency> findRes = currencyRepo.findById(dateString);
        if (findRes.isPresent()) {
            return findRes.get();
        }

        ResponseEntity<String> response = restTemplate.getForEntity(
                CURRENCY_API_URL + Util.getStringDateForPattern(date, "dd/MM/yyyy"),
                String.class);
        Currency currency = new Currency(dateString, extractDollarCurrencyValue(response));
        currencyRepo.save(currency);
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
