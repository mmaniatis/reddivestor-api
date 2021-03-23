package com.reddivestor.reddivestorapi.service.cryptocounts;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CryptoCountsService implements CountsService<Crypto> {
    final ReadWriteDatastore datastore;

    public List<Crypto> getByTimeBucket(String timeBucket) {

    }
}
