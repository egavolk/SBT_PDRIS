package edu.phystech.pdris.weather.service;

import edu.phystech.pdris.weather.model.Weather;

import java.util.List;

public interface WeatherService {
    List<Weather> getWeatherLastDays(int daysCount);

    List<Weather> getWeatherLastDays(int daysCount, String city);

    Weather getTomorrowWeather();

    Weather getTomorrowWeather(String city);
}
