package edu.phystech.pdris.predict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PredictApplication {
    public static void main(String[] args) {
        SpringApplication.run(PredictApplication.class, args);
    }
}