package edu.phystech.pdris.hw.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyResponseTest {

    @Test
    void getCurrency() throws JsonProcessingException {
        String date = "10.10.2020";
        double dollar = 30.0;
        double euro = 35.0;

        String xml =
                String.format("<ValCurs Date=\"%s\">" +
                        "<Valute ID=\"R01235\">" +
                        "<Value>%f</Value>" +
                        "</Valute>" +
                        "<Valute ID=\"R01234\">" +
                        "<Value>%f</Value>" +
                        "</Valute>" +
                        "</ValCurs>", date, dollar, euro);

        XmlMapper xmlMapper = new XmlMapper();
        CurrencyResponse response = xmlMapper.readValue(xml, CurrencyResponse.class);

        Currency currency = new Currency(date, dollar);
        assertEquals(currency.getDate(), response.getCurrency().getDate());
        assertEquals(currency.getDollar(), response.getCurrency().getDollar());
    }

    @Test
    void getCurrencyNotFound() throws JsonProcessingException {
        String date = "10.10.2020";
        double euro = 35.0;

        String xml =
                String.format("<ValCurs Date=\"%s\">" +
                        "<Valute ID=\"R01234\">" +
                        "<Value>%f</Value>" +
                        "</Valute>" +
                        "</ValCurs>", date, euro);

        XmlMapper xmlMapper = new XmlMapper();
        CurrencyResponse response = xmlMapper.readValue(xml, CurrencyResponse.class);

        assertThrows(NoSuchElementException.class, response::getCurrency);
    }
}