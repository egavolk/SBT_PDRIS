package edu.phystech.pdris.weather.model;

import java.io.Serializable;
import java.util.Objects;

public class WeatherKey implements Serializable {
    private String date;
    private String city;

    public WeatherKey() {}

    public WeatherKey(String date, String city) {
        this.date = date;
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherKey that = (WeatherKey) o;
        return date.equals(that.date) &&
                city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, city);
    }
}
