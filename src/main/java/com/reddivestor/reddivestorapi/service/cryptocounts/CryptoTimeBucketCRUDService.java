package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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

    public List<Crypto> getTopCoinNamesByTimeBucket(LocalDateTime timeBucketStart, LocalDateTime timeBucketEnd) throws Exception {
        if(timeBucketStart != null && timeBucketEnd != null) {
            List<Crypto> coinList = findByTimeStampBetween(timeBucketStart, timeBucketEnd);
            Map<String, Integer> coinFrequencyMap = new HashMap<>();
            Set<Crypto> cryptoSet = new HashSet<>();
            coinList.forEach(coin -> {
                coinFrequencyMap.put(coin.getName(), coinFrequencyMap.getOrDefault(coin, 0) + 1);
            });

//        return coinFrequencyMap
//                     .entrySet()
//                     .stream()
//                     .distinct()
//                     .sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder()))
//                     .map(entry -> entry.getKey())
//                     .collect(Collectors.toList());

            return coinList;
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
