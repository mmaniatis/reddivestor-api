package com.reddivestor.reddivestorapi.service;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import com.reddivestor.reddivestorapi.service.cryptocounts.CryptoCountsService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class TestCryptoCountsService {

    ReadWriteDatastore<Crypto> mockReadWriteDatastore;
    CryptoCountsService cryptoCountsService;

    @Before
    public void init() {
        //Init the Crypto service with a mock.
        mockReadWriteDatastore = mock(ReadWriteDatastore.class);
        cryptoCountsService = new CryptoCountsService(mockReadWriteDatastore);
    }
    @Rule
    public ExpectedException EXCEPTION_RULE = ExpectedException.none();

    @Test
    public void testGetByTimeBucketHappyPath() {
        Date in = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());

        Date dateOne = Date.from(localDateTime.minusDays(6).atZone(ZoneId.systemDefault()).toInstant());
        Date dateTwo = Date.from(localDateTime.minusDays(4).atZone(ZoneId.systemDefault()).toInstant());
        Date dateThree = Date.from(localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant());

        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        "blaname",
                        "blasubreddit",
                        dateOne),
                new Crypto("blabla crypto2",
                        "blaname2",
                        "blasubreddit2",
                        dateTwo),
                new Crypto("blabla crypto3",
                        "blaname3",
                        "blasubreddit4",
                        dateThree)
                );

        Mockito.when(mockReadWriteDatastore.findByTimestampBetween(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(mockCryptoList);
        try {
            assertTrue(cryptoCountsService.getByTimeBucket(
                    ZonedDateTime.now().minusDays(6))
                    .equals(mockCryptoList));
        } catch (Exception ex) {
            //Test failed.
        }
    }

    @Test
    public void testGetByTimeBucketNullTimeBucket() throws Exception{
        EXCEPTION_RULE.expect(Exception.class);
        EXCEPTION_RULE.expectMessage("timeBucket parameter was null or empty.");
        cryptoCountsService.getByTimeBucket(null);
    }
}