package edu.phystech.pdris.hw.storage;

import edu.phystech.pdris.hw.model.Weather;
import edu.phystech.pdris.hw.model.WeatherKey;

import java.util.HashMap;
import java.util.Map;

public class WeatherStorage {
    private final Map<WeatherKey, Weather> storage = new HashMap<>();

    public void addWeather(Weather weather) {
        storage.put(weather.weatherKey(), weather);
    }

    public boolean isWeatherKeyExist(WeatherKey weatherKey) {
        return storage.containsKey(weatherKey);
    }

    public Weather getWeatherByKey(WeatherKey weatherKey) {
        return storage.get(weatherKey);
    }
}
