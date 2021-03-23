package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import com.reddivestor.reddivestorapi.persist.mongo.MongoReadWriteDatastore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class CryptoCountsService implements CountsService<Crypto> {
    final ReadWriteDatastore<Crypto> datastore;

    public List<Crypto> getByTimeBucket(LocalDateTime timeBucket) throws Exception{
        if(timeBucket != null) {
            Date date = Date.from(timeBucket.atZone(ZoneId.systemDefault()).toInstant());
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            return datastore.findByTimestampBetween(date, currentDate);
        }
        else {
            throw new Exception("timeBucket parameter was null or empty.");
        }
    }
}
