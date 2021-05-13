package com.reddivestor.reddivestorapi.persist;

import java.util.Date;
import java.util.List;

public interface ReadWriteDatastore<O> {
    List<O> findByTimestampBetween(Date lowerBound, Date upperBound);
}
