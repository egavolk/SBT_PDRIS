package edu.phystech.pdris.hw.storage;

import edu.phystech.pdris.hw.model.Currency;

import java.util.HashMap;
import java.util.Map;

public class CurrencyStorage {
    private final Map<String, Currency> storage = new HashMap<>();

    public void addCurrency(Currency currency) {
        storage.put(currency.getDate(), currency);
    }

    public boolean isDateExist(String date) {
        return storage.containsKey(date);
    }

    public Currency getCurrencyByDate(String date) {
        return storage.get(date);
    }
}
