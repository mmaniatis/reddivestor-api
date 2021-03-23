package com.reddivestor.reddivestorapi.service.cryptocounts;


import java.time.ZonedDateTime;
import java.util.List;

public interface CountsService<O> {
    List<O> getByTimeBucket(ZonedDateTime timeBucket) throws Exception;
}
