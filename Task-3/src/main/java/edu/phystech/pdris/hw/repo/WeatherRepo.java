package edu.phystech.pdris.hw.repo;

import edu.phystech.pdris.hw.model.Weather;
import edu.phystech.pdris.hw.model.WeatherKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends CrudRepository<Weather, WeatherKey> {

}
