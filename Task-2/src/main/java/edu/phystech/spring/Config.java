package edu.phystech.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class Config {
    @Bean
    public Audits audits() {
        return new Audits();
    }

    @Bean
    public HashMap<String, String> usersDB() {
        return new HashMap<String, String>();
    }
}