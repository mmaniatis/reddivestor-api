package com.reddivestor.reddivestorapi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.google.gson.*;

import java.util.Date;
@Getter
@RequiredArgsConstructor
public class Crypto {
    final String post;
    final String name;
    final String sub_reddit;
    final Date timestamp;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
