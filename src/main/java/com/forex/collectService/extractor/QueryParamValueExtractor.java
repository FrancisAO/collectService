package com.forex.collectService.extractor;

import com.forex.collectService.model.CurrencyPair;

import java.time.ZonedDateTime;

public class QueryParamValueExtractor {


    public static CurrencyPair extractCurrencyPair(String currencyPairAsString) {
        for(CurrencyPair cp : CurrencyPair.values()) {
            if(cp.name().equalsIgnoreCase(currencyPairAsString)){
                return cp;
            }
        }
        return null;
    }

    public static Double extractNumber(String number) {
        return Double.valueOf(number);
    }

    public static ZonedDateTime extractTimestamp(String zonedDateTimeString) {
        return ZonedDateTime.parse(zonedDateTimeString);
    }
}