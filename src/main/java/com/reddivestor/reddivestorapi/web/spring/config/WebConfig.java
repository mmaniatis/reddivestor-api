package com.reddivestor.reddivestorapi.web.spring.config;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.service.cryptocounts.TimeBucketCRUDService;
import com.reddivestor.reddivestorapi.web.controller.CryptoCountsController;
import com.reddivestor.reddivestorapi.web.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
public class WebConfig {

    @Autowired
    TimeBucketCRUDService<Crypto> timeBucketCRUDService;

    @Value("${reddivestorapi.jwt.signingKey}")
    private String jwtSigningKey;

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilter() {
        return JWTAuthorizationFilter
                .builder()
                .JWT_SIGNING_KEY(jwtSigningKey)
                .build();
    }
}
