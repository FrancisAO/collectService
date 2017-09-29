package com.forex.collectService;

import java.util.HashMap;
import java.util.Map;

public final class QueryParamFactory {

    public static Map<String, String> createValidSingleCurrencyQueryParams(){
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("c", "EUR");
        queryParameters.put("ts", "notvalidated");
        queryParameters.put("cpc", "1.5");
        queryParameters.put("sid", "anid");
        return queryParameters;
    }


    public static Map<String, String> createValidCurrencyPairQueryParams() {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("cp", "EURUSD");
        queryParameters.put("sl", "1.5");
        queryParameters.put("by", "1.5");
        queryParameters.put("ts", "notvalidated");
        queryParameters.put("sid", "anid");
        return queryParameters;
    }

}
