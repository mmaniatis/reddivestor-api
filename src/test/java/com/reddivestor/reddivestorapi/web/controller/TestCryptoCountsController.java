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
        when(mockTimeBucketCRUDService.getByTimeBucket(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(mockCryptoList);
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRange(LocalDateTime.now().minusDays(1).toString(), LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 3);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.OK);
        assertTrue(!StringUtils.hasText(cryptoCountsResponse.exception));
    }
    @Test
    public void testGetByTimeRangeBadLowerBound() throws Exception{
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
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRange("03/2/33333", LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));


    }
    @Test
    public void testGetByTimeRangeUpperBound() throws Exception{
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
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRange(LocalDateTime.now().minusDays(5).toString(), "03/2/33333");

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));


    }
    @Test
    public void testGetByTimeRangeNullLowerBound() throws Exception{
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
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRange(null, LocalDateTime.now().minusDays(5).toString());

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));
    }
    @Test
    public void testGetByTimeRangeNullUpperBound() throws Exception{
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
        CryptoCountsResponse cryptoCountsResponse = cryptoCountsController
                .getByTimeRange(LocalDateTime.now().minusDays(5).toString(), null);

        assertTrue(cryptoCountsResponse.payload.size() == 0);
        assertTrue(cryptoCountsResponse.httpStatus == HttpStatus.BAD_REQUEST);
        assertTrue(StringUtils.hasText(cryptoCountsResponse.exception));
    }


}
