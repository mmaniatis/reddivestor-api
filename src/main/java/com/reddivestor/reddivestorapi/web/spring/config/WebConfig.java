package com.reddivestor.reddivestorapi.web.spring.config;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.service.cryptocounts.TimeBucketCRUDService;
import com.reddivestor.reddivestorapi.web.controller.CryptoCountsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Autowired
    TimeBucketCRUDService<Crypto> timeBucketCRUDService;

//    @Bean
//    public CryptoCountsController cryptoCountsController() {
//        return new CryptoCountsController(timeBucketCRUDService);
//    }




}
