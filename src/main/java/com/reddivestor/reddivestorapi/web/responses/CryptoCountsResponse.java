package com.reddivestor.reddivestorapi.web.responses;

import com.reddivestor.reddivestorapi.models.Crypto;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@Setter
public class CryptoCountsResponse {
    public HttpStatus httpStatus;
    public String exception;
    public List<Crypto> payload = new ArrayList<>();
}
