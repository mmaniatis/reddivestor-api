package com.reddivestor.reddivestorapi.persist;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReadWriteDatastore<O> {
    List<O> findByTimestampBetween(ZonedDateTime lowerBound, ZonedDateTime upperBound);
}
