package com.reddivestor.reddivestorapi.persist.spring.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Configuration
public class MongoConfig {
    //TODO: Map these to yaml file.
    String username;
    String password;
    String database;
    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://"+username+":" +password+
                        "@cluster0.fco56.mongodb.net/"+database+"?" +
                        "retryWrites=true&w=majority");
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate =  new MongoTemplate(mongoClient(), "main");

        Set<String> collections = mongoTemplate.getCollectionNames();
        System.out.println(collections.toString());
        return mongoTemplate;
    }

}
