package edu.phystech.pdris.hw.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherResponseTest {

    @Test
    void getWeather() throws JsonProcessingException {
        String date = "2020-12-12";
        String city = "Dolgoprudny";
        double avgTempC = 20.0;
        double maxWindKph = 5.0;
        String condition = "cool";

        String json =
                String.format(
                        "{" +
                            "\"location\" : {" +
                                "\"name\" : \"%s\"" +
                            "}," +
                            "\"forecast\" : {" +
                                "\"forecastday\" : [{" +
                                    "\"date\" : \"%s\"," +
                                    "\"day\" : {" +
                                        "\"avgtemp_c\" : %f," +
                                        "\"maxwind_kph\" : %f," +
                                        "\"condition\" : {" +
                                            "\"text\" : \"%s\" " +
                                        "}" +
                                    "}" +
                                "}]" +
                            "}" +
                        "}", city, date, avgTempC, maxWindKph, condition);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse response = mapper.readerFor(WeatherResponse.class)
                                         .readValue(json);

        Weather weather = new Weather(date, city, avgTempC, maxWindKph, condition);
        assertEquals(weather.getDate(), response.getWeather().getDate());
        assertEquals(weather.getCity(), response.getWeather().getCity());
        assertEquals(weather.getAvgTempC(), response.getWeather().getAvgTempC());
        assertEquals(weather.getMaxWindKph(), response.getWeather().getMaxWindKph());
        assertEquals(weather.getCondition(), response.getWeather().getCondition());

    }
}