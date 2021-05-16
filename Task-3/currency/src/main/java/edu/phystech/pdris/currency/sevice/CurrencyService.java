package edu.phystech.pdris.currency.sevice;

import edu.phystech.pdris.currency.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getDollarCurrencyLastDays(int daysCount);
}
