package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

@RequiredArgsConstructor
public class CryptoTimeBucketCRUDService implements TimeBucketCRUDService<Crypto> {
    final ReadWriteDatastore<Crypto> datastore;

    public List<Crypto> getByTimeBucket(LocalDateTime timeBucketStart, LocalDateTime timeBucketEnd) throws Exception{
        if(timeBucketStart != null && timeBucketEnd != null) {
            return findByTimeStampBetween(timeBucketStart, timeBucketEnd);
        }
        else {
            throw new Exception("timeBucket parameter was null or empty.");
        }
    }

    public List<String> getTopCoinNamesByTimeBucket(LocalDateTime timeBucketStart, LocalDateTime timeBucketEnd) throws Exception {
        if(timeBucketStart != null && timeBucketEnd != null) {
            Map<String, Integer> coinMap = new HashMap<>();
            findByTimeStampBetween(timeBucketStart, timeBucketEnd)
                .forEach(coin -> coinMap.put(coin.getName(), coinMap.getOrDefault(coin.getName(), 0) + 1));
            return coinMap
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder()))
                    .map(entry -> entry.getKey())
                    .collect(Collectors.toList());
        }
        else {
            throw new Exception("timeBucket parameter was null or empty.");
        }
    }

    private List<Crypto> findByTimeStampBetween(LocalDateTime start, LocalDateTime end){
        Date dateStart = Date.from(start.atZone(ZoneId.of("UTC")).toInstant());
        Date dateEnd = Date.from(end.atZone(ZoneId.of("UTC")).toInstant());
        return datastore.findByTimestampBetween(dateStart, dateEnd);
    }
}
