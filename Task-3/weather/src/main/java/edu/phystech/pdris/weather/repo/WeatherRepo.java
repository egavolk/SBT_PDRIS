package edu.phystech.pdris.weather.repo;

import edu.phystech.pdris.weather.model.Weather;
import edu.phystech.pdris.weather.model.WeatherKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends CrudRepository<Weather, WeatherKey> {

}
