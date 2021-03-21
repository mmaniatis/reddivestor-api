package com.reddivestor.reddivestorapi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Crypto {
    final String post;
    final String name;
    final String subReddit;
    final Date timestamp;
}
