package edu.phystech.pdris.hw.sevice;

import edu.phystech.pdris.hw.Config;
import edu.phystech.pdris.hw.model.Weather;
import edu.phystech.pdris.hw.repo.WeatherRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherServiceImplTest {
    @Autowired
    private WeatherServiceImpl weatherService;

    public WeatherServiceImplTest() {}

    @Test
    void getWeatherLastDays() {
        List<Weather> weather1 = weatherService.getWeatherLastDays(5);
        List<Weather> weather2 = weatherService.getWeatherLastDays(5, "Moscow");
        assertEquals(weather1.size(), weather2.size());
        for (int i = 0; i < weather1.size(); ++i) {
            assertEquals(weather1.get(i).getDate(), weather2.get(i).getDate());
            assertEquals(weather1.get(i).getCity(), weather2.get(i).getCity());
            assertEquals(weather1.get(i).getMaxWindKph(), weather2.get(i).getMaxWindKph());
            assertEquals(weather1.get(i).getAvgTempC(), weather2.get(i).getAvgTempC());
            assertEquals(weather1.get(i).getCondition(), weather2.get(i).getCondition());
        }
    }
}