package edu.phystech.pdris.currency.controller;

import edu.phystech.pdris.currency.model.Currency;
import edu.phystech.pdris.currency.sevice.CurrencyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Currency> getDollarCurrencyLastDays(@RequestParam(required = false, defaultValue = "1") Integer days) {
        return currencyService.getDollarCurrencyLastDays(days);
    }
}