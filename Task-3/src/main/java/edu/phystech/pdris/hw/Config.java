package edu.phystech.pdris.hw;

import edu.phystech.pdris.hw.sevice.CurrencyService;
import edu.phystech.pdris.hw.sevice.CurrencyServiceImpl;
import edu.phystech.pdris.hw.storage.CurrencyStorage;
import edu.phystech.pdris.hw.storage.WeatherStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CurrencyStorage currencyStorage() {
        return new CurrencyStorage();
    }

    @Bean
    WeatherStorage weatherStorage() {
        return new WeatherStorage();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
