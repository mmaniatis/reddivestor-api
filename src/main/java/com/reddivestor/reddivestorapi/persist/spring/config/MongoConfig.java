package com.reddivestor.reddivestorapi.persist.spring.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.reddivestor.reddivestorapi.persist.mongo.MongoReadWriteDatastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableConfigurationProperties
@Configuration
public class MongoConfig {
    //TODO: Map these to yaml file.
    @Value("${reddivestorapi.mongodb.username}")
    String username;

    @Value("${reddivestorapi.mongodb.password}")
    String password;

    @Value("${reddivestorapi.mongodb.database}")
    String database;

    @Bean
    public MongoClient mongoClient() {
        System.out.println("!! MongoClient bean initialized !! ");
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://"+username+":" +password+
                        "@cluster0.fco56.mongodb.net/"+database+"?" +
                        "retryWrites=true&w=majority");
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate =  new MongoTemplate(mongoClient(), "main");
        System.out.println("!! MongoTemplate bean initialized !! ");
        return mongoTemplate;
    }

    @Bean
    public MongoReadWriteDatastore mongoReadWriteDatastore(@Autowired MongoTemplate mongoTemplate) {
        return new MongoReadWriteDatastore(mongoTemplate);
    }

}
