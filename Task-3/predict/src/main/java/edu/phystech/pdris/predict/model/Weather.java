package edu.phystech.pdris.predict.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(WeatherKey.class)
public class Weather {
    @Id
    private String date;
    @Id
    private String city;
    private double avgTempC;

    public Weather() {}

    public Weather(String date, String city, double avgTempC) {
        this.date = date;
        this.city = city;
        this.avgTempC = avgTempC;
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

    public double getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(double avgTempC) {
        this.avgTempC = avgTempC;
    }

    public WeatherKey weatherKey() {
        return new WeatherKey(date, city);
    }
}
