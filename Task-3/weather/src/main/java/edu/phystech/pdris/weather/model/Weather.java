package edu.phystech.pdris.weather.model;


import javax.persistence.*;

@Entity
@Table(name = "weather")
@IdClass(WeatherKey.class)
public class Weather {
    @Id
    private String date;
    @Id
    private String city;
    @Column(name = "avg_temp_c")
    private double avgTempC;
    @Column(name = "max_wind_kph")
    private double maxWindKph;
    private String condition;

    public Weather() {}

    public Weather(String date, String city, double avgTempC, double maxWindKph, String condition) {
        this.date = date;
        this.city = city;
        this.avgTempC = avgTempC;
        this.maxWindKph = maxWindKph;
        this.condition = condition;
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

    public double getMaxWindKph() {
        return maxWindKph;
    }

    public void setMaxWindKph(double maxWindKph) {
        this.maxWindKph = maxWindKph;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public WeatherKey weatherKey() {
        return new WeatherKey(date, city);
    }
}
