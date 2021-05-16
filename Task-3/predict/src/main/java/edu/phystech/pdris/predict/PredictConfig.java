package edu.phystech.pdris.predict;

import edu.phystech.pdris.predict.service.outer_api.APICurrencyService;
import edu.phystech.pdris.predict.service.outer_api.APICurrencyServiceImpl;
import edu.phystech.pdris.predict.service.outer_api.APIWeatherService;
import edu.phystech.pdris.predict.service.outer_api.APIWeatherServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class PredictConfig {
    @Bean
    RestTemplateBuilder builder() {
        return new RestTemplateBuilder();
    }

    @Bean
    APICurrencyService currencyService() {
        return new APICurrencyServiceImpl(builder());
    }

    @Bean
    APIWeatherService weatherService() {
        return new APIWeatherServiceImpl(builder());
    }
}
