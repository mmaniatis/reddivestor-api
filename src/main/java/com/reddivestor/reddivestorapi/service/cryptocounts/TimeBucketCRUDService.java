package com.reddivestor.reddivestorapi.service.cryptocounts;


import java.time.LocalDateTime;
import java.util.List;

public interface TimeBucketCRUDService<O> {
    List<O> getByTimeBucket(LocalDateTime timeBucketStart, LocalDateTime timeBucketEnd) throws Exception;
}
