package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class CryptoTimeBucketCRUDService implements TimeBucketCRUDService<Crypto> {
    final ReadWriteDatastore<Crypto> datastore;

    public List<Crypto> getByTimeBucket(LocalDateTime timeBucketStart, LocalDateTime timeBucketEnd) throws Exception{
        if(timeBucketStart != null && timeBucketEnd != null) {
            Date dateStart = Date.from(timeBucketStart.atZone(ZoneId.of("UTC")).toInstant());
            Date dateEnd = Date.from(timeBucketEnd.atZone(ZoneId.of("UTC")).toInstant());
            return datastore.findByTimestampBetween(dateStart, dateEnd);
        }
        else {
            throw new Exception("timeBucket parameter was null or empty.");
        }
    }
}
