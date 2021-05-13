package com.reddivestor.reddivestorapi.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.google.gson.*;

@Getter
@RequiredArgsConstructor
public class Crypto {
    final String name;
    final int coinFrequency;
    final int rank;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
