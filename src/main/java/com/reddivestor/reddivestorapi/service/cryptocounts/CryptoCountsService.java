package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CryptoCountsService implements CountsService<Crypto> {
    final ReadWriteDatastore datastore;

    public List<Crypto> getByTimeBucket(ZonedDateTime timeBucket) throws Exception{
        if(timeBucket != null) {
            return datastore.findByTimestampBetween(timeBucket, ZonedDateTime.now());
        }
        else {
            throw new Exception("timeBucket parameter was null or empty.");
        }
    }
}
