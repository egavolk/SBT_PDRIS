package edu.phystech.pdris.predict.service.outer_api;


import edu.phystech.pdris.predict.model.Weather;

import java.util.List;

public interface APIWeatherService {
    List<Weather> getWeatherLastDays(int daysCount, String city);

    Weather getTomorrowWeather(String city);
}
