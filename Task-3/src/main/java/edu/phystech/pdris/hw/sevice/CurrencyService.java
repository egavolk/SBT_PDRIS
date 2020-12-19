package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> getDollarCurrencyLastDays(int daysCount);
}
