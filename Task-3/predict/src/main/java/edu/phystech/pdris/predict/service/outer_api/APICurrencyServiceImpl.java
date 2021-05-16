package edu.phystech.pdris.predict.service.outer_api;

import edu.phystech.pdris.predict.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class APICurrencyServiceImpl implements APICurrencyService {
    private static final String CURRENCY_API_URL = "http://currency:8080/currency?days=%d";

    private final RestTemplate restTemplate;

    @Autowired
    public APICurrencyServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public List<Currency> getDollarCurrencyLastDays(int days) {
        ResponseEntity<Currency[]> response = restTemplate.getForEntity(
                String.format(CURRENCY_API_URL, days),
                Currency[].class);

        return new ArrayList<>(Arrays.asList(response.getBody()));
    }
}
