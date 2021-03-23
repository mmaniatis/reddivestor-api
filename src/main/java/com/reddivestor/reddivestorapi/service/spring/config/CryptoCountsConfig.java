package com.reddivestor.reddivestorapi.service.spring.config;

import com.reddivestor.reddivestorapi.persist.mongo.MongoReadWriteDatastore;
import com.reddivestor.reddivestorapi.service.cryptocounts.CryptoCountsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class CryptoCountsConfig {
    @Autowired
    MongoReadWriteDatastore mongoReadWriteDatastore;

    @Bean
    public CryptoCountsCRUDService cryptoCountsService() {
        CryptoCountsCRUDService cryptoCountsCRUDService = new CryptoCountsCRUDService(mongoReadWriteDatastore);
        System.out.println("!! CryptoCountsService bean created !!");
        try {
            System.out.println(String.format("Cryptos in past hour: %s",
                    cryptoCountsCRUDService.getByTimeBucket(LocalDateTime.now().minusMinutes(59))));
        } catch (Exception ex) {
            System.out.println("Error with CryptoCountsService ..." + ex.toString());
        }
        return cryptoCountsCRUDService;
    }
}
