package com.reddivestor.reddivestorapi.persist.mongo;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;

public interface MongoReadWriteDatastore extends ReadWriteDatastore {
    Crypto getCryptoForTimeBucket();
}
