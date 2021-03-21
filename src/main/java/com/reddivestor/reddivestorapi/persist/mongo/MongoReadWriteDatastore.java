package com.reddivestor.reddivestorapi.persist.mongo;

import com.mongodb.internal.async.client.AsyncFindIterable;
import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoReadWriteDatastore extends MongoRepository<Crypto, String> {
    List<Crypto> findByTimestamp(String timestamp);
}