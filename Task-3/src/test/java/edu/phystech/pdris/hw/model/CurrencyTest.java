package edu.phystech.pdris.hw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    @Test
    void emptyConstructor() {
        String date = "2020-12-21";
        double dollar = 30.0; // I am optimist

        Currency currency = new Currency();
        currency.setDate(date);
        currency.setDollar(dollar);

        assertEquals(date, currency.getDate());
        assertEquals(dollar, currency.getDollar());
    }

    @Test
    void fields() {
        String date = "2020-12-21";
        double dollar = 30.0; // I am optimist

        Currency currency = new Currency(date, dollar);

        assertEquals(date, currency.getDate());
        assertEquals(dollar, currency.getDollar());
    }
}