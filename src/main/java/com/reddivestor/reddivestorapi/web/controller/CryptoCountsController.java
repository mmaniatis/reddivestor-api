package com.reddivestor.reddivestorapi.web.controller;

import com.google.gson.Gson;
import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.service.cryptocounts.TimeBucketCRUDService;
import com.reddivestor.reddivestorapi.web.responses.CryptoCountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CryptoCountsController {
    final TimeBucketCRUDService<Crypto> timeBucketCRUDService;

    @RequestMapping("/cryptocounts/getbytimerangeutc")
    //Requests should be in UTC Format. i.e 2021-03-24T01:00:21.000
    public CryptoCountsResponse getByTimeRangeUTC(String lowerBound, String upperBound) {
        CryptoCountsResponse response = new CryptoCountsResponse();
        try {
            LocalDateTime dateStart = LocalDateTime.parse(lowerBound);
            LocalDateTime dateEnd = LocalDateTime.parse(upperBound);
            response.setPayload(timeBucketCRUDService.getByTimeBucket(dateStart, dateEnd));
            response.httpStatus = HttpStatus.OK;
        } catch (Exception ex) {
            response.exception = ex.toString();
            response.httpStatus = HttpStatus.BAD_REQUEST;
        }
        return response;
    }

    @RequestMapping("/cryptocounts/getlasthour")
    @Description("Returns the last hour of coin names sorted by frequency.")
    public List<Crypto> getLastHour() {
        try {
            LocalDateTime dateStart = LocalDateTime.ofInstant(Instant.now().minus(1, ChronoUnit.HOURS), ZoneOffset.UTC);
            LocalDateTime dateEnd = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            return timeBucketCRUDService.getTopCoinNamesByTimeBucket(dateStart,dateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/cryptocounts/getlastday")
    @Description("Returns the last day of coin names sorted by frequency.")
    private List<Crypto> getLastDay() {
        try {
            LocalDateTime dateStart = LocalDateTime.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS), ZoneOffset.UTC);
            LocalDateTime dateEnd = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            return timeBucketCRUDService.getTopCoinNamesByTimeBucket(dateStart,dateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/cryptocounts/getlastweek")
    private List<Crypto> getLastWeek() {
        try {
            LocalDateTime dateStart = LocalDateTime.ofInstant(Instant.now().minus(1, ChronoUnit.WEEKS), ZoneOffset.UTC);
            LocalDateTime dateEnd = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            return timeBucketCRUDService.getTopCoinNamesByTimeBucket(dateStart,dateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/cryptocounts/getlastmonth")
    private List<Crypto> getLastMonth() {
        try {
            LocalDateTime dateStart = LocalDateTime.ofInstant(Instant.now().minus(1, ChronoUnit.WEEKS), ZoneOffset.UTC);
            LocalDateTime dateEnd = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            return timeBucketCRUDService.getTopCoinNamesByTimeBucket(dateStart,dateEnd);
        } catch (Exception e) {
            return null;
        }
    }

}
