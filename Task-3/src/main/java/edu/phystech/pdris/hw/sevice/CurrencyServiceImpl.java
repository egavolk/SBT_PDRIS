package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Util;
import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.model.CurrencyResponse;
import edu.phystech.pdris.hw.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

    private final CurrencyRepo currencyRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepo currencyRepo, RestTemplateBuilder builder) {
        this.currencyRepo = currencyRepo;
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
        Optional<Currency> findRes = currencyRepo.findById(dateString);
        if (findRes.isPresent()) {
            return findRes.get();
        }

        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(
                CURRENCY_API_URL + Util.getStringDateForPattern(date, "dd/MM/yyyy"),
                CurrencyResponse.class);
        Currency currency = response.getBody().getCurrency();
        currencyRepo.save(currency);
        return currency;
    }
}
