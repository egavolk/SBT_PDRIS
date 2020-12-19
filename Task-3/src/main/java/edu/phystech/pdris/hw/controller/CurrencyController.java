package edu.phystech.pdris.hw.controller;

import edu.phystech.pdris.hw.model.Currency;
import edu.phystech.pdris.hw.sevice.CurrencyService;
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

    @GetMapping("/currency")
    public List<Currency> getDollarCurrencyLastDays(@RequestParam(required = false, defaultValue = "1") Integer days) {
        return currencyService.getDollarCurrencyLastDays(days);
    }
}