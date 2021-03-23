package com.reddivestor.reddivestorapi.service.cryptocounts;


import java.util.List;

public interface CountsService<O> {
    List<O> getByTimeBucket(String timeBucket);
}
