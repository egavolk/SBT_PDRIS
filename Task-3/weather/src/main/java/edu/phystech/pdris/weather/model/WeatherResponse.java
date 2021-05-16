package edu.phystech.pdris.weather.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;


public class WeatherResponse {
    @JsonProperty
    private Location location;
    @JsonProperty
    private Forecast forecast;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        @JsonProperty
        private String name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Forecast {
        @JsonProperty(value = "forecastday")
        private List<ForecastDay> forecastDay;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastDay {
        @JsonProperty
        private String date;
        @JsonProperty
        private Day day;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Day {
        @JsonProperty("avgtemp_c")
        private double avgTempC;
        @JsonProperty("maxwind_kph")
        private double maxWindKph;
        @JsonProperty
        private Condition condition;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {
        @JsonProperty
        private String text;
    }

    public Weather getWeather() {
        return new Weather(
                forecast.forecastDay.get(0).date,
                location.name,
                forecast.forecastDay.get(0).day.avgTempC,
                forecast.forecastDay.get(0).day.maxWindKph,
                forecast.forecastDay.get(0).day.condition.text
        );
    }
}
