package com.reddivestor.reddivestorapi.persist.spring.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.reddivestor.reddivestorapi.models.Crypto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Set;


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
        Set<String> collections = mongoTemplate.getCollectionNames();
        List<Crypto> cryptoList = mongoTemplate.findAll(Crypto.class, "crypto_counts");
        System.out.println("!! MongoTemplate bean initialized !! ");
        System.out.println("!! Collections in current env: " + collections.toString() + " !!");
        System.out.println("!! Size of crypto_counts: " + cryptoList.size()+ " !!");
        return mongoTemplate;
    }

}