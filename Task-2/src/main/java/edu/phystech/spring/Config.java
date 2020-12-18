package edu.phystech.spring;

import edu.phystech.spring.serializer.JSONSerializer;
import edu.phystech.spring.serializer.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Audits audits() {
        return new Audits();
    }

    @Bean
    public UsersDB usersDB() {
        return new UsersDB();
    }

    @Bean
    public Serializer serializer() {
        return new JSONSerializer();
    }
}