package edu.phystech.pdris.predict.service.outer_api;


import edu.phystech.pdris.predict.model.Currency;

import java.util.List;

public interface APICurrencyService {
    List<Currency> getDollarCurrencyLastDays(int days);
}
