package com.reddivestor.reddivestorapi.service.cryptocounts;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CountsService<O> {
    List<O> getByTimeBucket(LocalDateTime timeBucket) throws Exception;
}
