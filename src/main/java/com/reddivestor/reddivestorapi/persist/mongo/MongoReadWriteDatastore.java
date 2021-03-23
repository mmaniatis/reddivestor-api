package com.reddivestor.reddivestorapi.persist.mongo;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class MongoReadWriteDatastore implements ReadWriteDatastore<Crypto>{
    final MongoTemplate mongoTemplate;

    public List<Crypto> findByTimestampBetween(Date lowerBound, Date upperBound){
        return mongoTemplate.findAll(Crypto.class, "crypto_counts")
                .stream()
                .filter(x -> (x.getTimestamp().after(lowerBound) && x.getTimestamp().before(upperBound)))
                .collect(Collectors.toList());
    }
}