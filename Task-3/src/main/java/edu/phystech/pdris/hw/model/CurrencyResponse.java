package edu.phystech.pdris.hw.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@JacksonXmlRootElement(localName = "ValCurs")
public class CurrencyResponse {
    public static final String DOLLAR_ID = "R01235";

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    private String date;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<Valute> valute = new ArrayList<>();

    @JacksonXmlRootElement(localName = "Valute")
    public static class Valute {
        @JacksonXmlProperty(localName = "ID", isAttribute = true)
        private String id;

        @JacksonXmlProperty(localName = "Value")
        private String dollar;
    }


    public Currency getCurrency() {
        for (Valute v : valute) {
            if (DOLLAR_ID.equals(v.id)) {
                return new Currency(date, Double.parseDouble(v.dollar.replace(',', '.')));
            }
        }
        throw new NoSuchElementException("Could not find dollar curs");
    }
}
