package com.reddivestor.reddivestorapi.persist.mongo;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface MongoReadWriteDatastore  extends MongoRepository<Crypto, String>, ReadWriteDatastore<Crypto>{
    List<Crypto> findByTimestampBetween(ZonedDateTime lowerBound, ZonedDateTime upperBound);
}