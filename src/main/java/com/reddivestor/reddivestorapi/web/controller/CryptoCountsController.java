package com.reddivestor.reddivestorapi.web.controller;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.service.cryptocounts.TimeBucketCRUDService;
import com.reddivestor.reddivestorapi.web.responses.CryptoCountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public CryptoCountsResponse getLastHour() {
        CryptoCountsResponse response = new CryptoCountsResponse();
        try {
            LocalDateTime dateStart = LocalDateTime.ofInstant(Instant.now().minus(1, ChronoUnit.HOURS), ZoneOffset.UTC);
            LocalDateTime dateEnd = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            response.setPayload(timeBucketCRUDService.getByTimeBucket(dateStart,dateEnd));
            response.httpStatus = HttpStatus.OK;
        } catch (Exception ex) {
            response.exception = ex.toString();
            response.httpStatus = HttpStatus.BAD_REQUEST;
        }
        return response;
    }

    @RequestMapping("/cryptocounts/getlasttwelevehours")
    private List<Crypto> getLastTwelveHours() {

        return null;
    }

    @RequestMapping("/cryptocounts/getlastday")
    private List<Crypto> getLastDay() {

        return null;
    }

    @RequestMapping("/cryptocounts/getlastweek")
    private List<Crypto> getLastWeek() {

        return null;
    }

    @RequestMapping("/cryptocounts/getlastthreeweeks")
    private List<Crypto> getLastThreeWeeks() {
        return null;
    }

    @RequestMapping("/cryptocounts/getlastmonth")
    private List<Crypto> getLastMonth() {
        return null;
    }

    @RequestMapping("/cryptocounts/getlastthreemonths")
    private List<Crypto> getLastThreeMonths() {
        return null;
    }

    @RequestMapping("/cryptocounts/getlastyear")
    private List<Crypto> getLastYear() {
        return null;
    }
}
