package com.chessmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;  // Este es el import correcto

@Configuration
@EnableMongoRepositories(basePackages = "com.chessmaster.repository")
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "ajedrez");
    }
}
