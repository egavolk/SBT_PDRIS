package edu.phystech.pdris.weather.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherKeyTest {

    @Test
    void emptyConstructor() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";

        WeatherKey key = new WeatherKey();
        key.setCity(city);
        key.setDate(date);

        assertEquals(city, key.getCity());
        assertEquals(date, key.getDate());
    }


    @Test
    void fields() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";

        WeatherKey key = new WeatherKey(date, city);

        assertEquals(date, key.getDate());
        assertEquals(city, key.getCity());

        String newDate = "2020-12-20";
        String newCity = "Moscow";
        key.setCity(newCity);
        key.setDate(newDate);

        assertEquals(newDate, key.getDate());
        assertEquals(newCity, key.getCity());
    }

    @Test
    void testEquals() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";

        String newDate = "2020-12-20";
        String newCity = "Moscow";

        WeatherKey key1 = new WeatherKey(date, city);
        WeatherKey key2 = new WeatherKey(date, city);

        assertEquals(key1, key2);
        assertNotEquals(null, key1);
        assertNotEquals(key1, null);

        key1.setDate(newDate);
        assertNotEquals(key1, key2);
        assertNotEquals(key2, key1);

        key1.setDate(date);
        key2.setCity(newCity);
        assertNotEquals(key1, key2);
        assertNotEquals(key2, key1);

        assertEquals(key1, key1);
        assertNotEquals(key1, "");
        assertNotEquals("", key1);
    }

    @Test
    void testHashCode() {
        String date = "2020-12-12";
        String city = "Dolgoprudny";

        String newDate = "2020-12-20";

        WeatherKey key1 = new WeatherKey(date, city);
        WeatherKey key2 = new WeatherKey(date, city);

        assertEquals(key1, key2);
        assertEquals(key1.hashCode(), key2.hashCode());
    }
}