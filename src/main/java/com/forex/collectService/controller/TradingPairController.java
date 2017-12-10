package com.forex.collectService.controller;

import com.forex.collectService.extractor.QueryParamValueExtractor;
import com.forex.collectService.model.AskBidQueryParams;
import com.forex.collectService.model.CurrencyPair;
import com.forex.collectService.repository.QueryExecuter;
import com.forex.collectService.validator.AskBidQueryParamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class TradingPairController {


    @Autowired
    private AskBidQueryParamValidator askBidQueryParamValidator;
    @Autowired
    private QueryExecuter queryExecuter;
    private DateTimeFormatter dateTimeFormatter;
    Logger LOG = LoggerFactory.getLogger(TradingPairController.class);

    @RequestMapping(path = "/collectTP", method = RequestMethod.POST)
    public String storeTraidingPairs(@RequestParam Map<String, String> queryParameters) {
        if (!askBidQueryParamValidator.valid(queryParameters)) {
            return "Fail :-(";
        }
        insertQueryParametersInDatabase(queryParameters);
        return "Success :-)";

    }

    private void insertQueryParametersInDatabase(@RequestParam Map<String, String> queryParameters) {
        CurrencyPair cp = extractCurrencyPair(queryParameters);
        Double sell = extractNumber(AskBidQueryParams.SELL_PRICE, queryParameters);
        Double buy = extractNumber(AskBidQueryParams.BUY_PRICE, queryParameters);
        OffsetDateTime offsetDateTime = extractTimestamp(queryParameters);
        String serviceId = extractServiceId(queryParameters);

        queryExecuter.insertAskBid(cp, sell, buy, offsetDateTime, serviceId);
    }

    private String extractServiceId(Map<String, String> queryParameters) {
        return queryParameters.get(AskBidQueryParams.SERVICE_ID);
    }

    private OffsetDateTime extractTimestamp(Map<String, String> queryParameters) {
        String timeInMilliSecondsString = queryParameters.get(AskBidQueryParams.TIMESTAMP);
        Long timeInMilliSeconds = Long.valueOf(timeInMilliSecondsString);
        Instant timeInstant = Instant.ofEpochMilli(timeInMilliSeconds);
        return OffsetDateTime.ofInstant(timeInstant, ZoneId.systemDefault());
    }

    private Double extractNumber(String position, Map<String, String> queryParameters) {
        String value = queryParameters.get(position);
        return Double.valueOf(value);
    }

    private CurrencyPair extractCurrencyPair(Map<String, String> queryParams) {
        String currencyPair = queryParams.get(AskBidQueryParams.CURRENCY_PAIR);
        return QueryParamValueExtractor.extractCurrencyPair(currencyPair);
    }

    @PostConstruct
    private void initDateTimeFormatter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("");
    }


}
