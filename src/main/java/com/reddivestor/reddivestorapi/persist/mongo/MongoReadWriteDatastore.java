package com.reddivestor.reddivestorapi.persist.mongo;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
public class MongoReadWriteDatastore implements ReadWriteDatastore<Crypto>{
    final MongoTemplate mongoTemplate;
    static String CRYPTO_COUNTS_COLLECTION_NAME = "crypto_counts";

    public List<Crypto> findByTimestampBetween(Date lowerBound, Date upperBound){
        Query query = new Query();
        query.addCriteria(Criteria.where("timestamp").gte(lowerBound).lt(upperBound));
        return mongoTemplate.find(query, Crypto.class, this.CRYPTO_COUNTS_COLLECTION_NAME);
    }
}