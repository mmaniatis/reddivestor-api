package com.reddivestor.reddivestorapi.web.controller;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.service.cryptocounts.TimeBucketCRUDService;
import com.reddivestor.reddivestorapi.web.responses.CryptoCountsResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;

public class TestCryptoCountsController {
    TimeBucketCRUDService mockTimeBucketCRUDService;
    CryptoCountsController cryptoCountsController;

    @Before
    public void init() {
        mockTimeBucketCRUDService = mock(TimeBucketCRUDService.class);
        cryptoCountsController = new CryptoCountsController(mockTimeBucketCRUDService);
    }

    @Test
    public void testGetByTimeRangeHappyPath() throws Exception {
        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        3,
                        1),
                new Crypto("blabla crypto2",
                        2,
                        2),
                new Crypto("blabla crypto3",
                        1,
                        3)
        );
        when(mockTimeBucketCRUDService.getByTimeBucket(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(mockCryptoList);
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRangeUTC(LocalDateTime.now().minusDays(1).toString(), LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 3);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.OK);
        assertTrue(!StringUtils.hasText(cryptoCountsResponse.exception));
    }
    @Test
    public void testGetByTimeRangeBadLowerBound() throws Exception{
        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        3,
                        1),
                new Crypto("blabla crypto2",
                        2,
                        2),
                new Crypto("blabla crypto3",
                        1,
                        3)
        );
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRangeUTC("03/2/33333", LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));


    }
    @Test
    public void testGetByTimeRangeUpperBound() throws Exception{
        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        3,
                        1),
                new Crypto("blabla crypto2",
                        2,
                        2),
                new Crypto("blabla crypto3",
                        1,
                        3)
        );
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRangeUTC(LocalDateTime.now().minusDays(5).toString(), "03/2/33333");

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));


    }
    @Test
    public void testGetByTimeRangeNullLowerBound() throws Exception{
        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        3,
                        1),
                new Crypto("blabla crypto2",
                        2,
                        2),
                new Crypto("blabla crypto3",
                        1,
                        3)
        );
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRangeUTC(null, LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));
    }
    @Test
    public void testGetByTimeRangeNullUpperBound() throws Exception{
        List<Crypto> mockCryptoList = Arrays.asList(
                new Crypto("blabla crypto",
                        3,
                        1),
                new Crypto("blabla crypto2",
                        2,
                        2),
                new Crypto("blabla crypto3",
                        1,
                        3)
        );
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRangeUTC(LocalDateTime.now().minusDays(5).toString(), null);

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));
    }


}
