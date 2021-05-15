package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.model.Weather;

import java.util.List;

public interface WeatherService {
    List<Weather> getWeatherLastDays(int daysCount);

    List<Weather> getWeatherLastDays(int daysCount, String city);

    Weather getTomorrowWeather();

    Weather getTomorrowWeather(String city);
}
