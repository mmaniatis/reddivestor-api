package com.reddivestor.reddivestorapi.service;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import com.reddivestor.reddivestorapi.service.cryptocounts.CryptoCountsCRUDService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class TestCryptoCountsCRUDService {

    ReadWriteDatastore mockReadWriteDatastore;
    CryptoCountsCRUDService cryptoCountsCRUDService;

    @Before
    public void init() {
        //Init the Crypto service with a mock.
        mockReadWriteDatastore = mock(ReadWriteDatastore.class);
        cryptoCountsCRUDService = new CryptoCountsCRUDService(mockReadWriteDatastore);
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

        Mockito.when(mockReadWriteDatastore.findByTimestampBetween(any(Date.class), any(Date.class)))
                .thenReturn(mockCryptoList);
        try {
            assertTrue(cryptoCountsCRUDService.getByTimeBucket(
                    LocalDateTime.now().minusDays(6))
                    .equals(mockCryptoList));
        } catch (Exception ex) {
            //Test failed.
        }
    }

    @Test
    public void testGetByTimeBucketNullTimeBucket() throws Exception{
        EXCEPTION_RULE.expect(Exception.class);
        EXCEPTION_RULE.expectMessage("timeBucket parameter was null or empty.");
        cryptoCountsCRUDService.getByTimeBucket(null);
    }
}
