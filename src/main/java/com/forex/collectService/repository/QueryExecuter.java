package com.forex.collectService.repository;

import com.forex.collectService.model.CurrencyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class QueryExecuter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertAskBid(CurrencyPair cp, Double sellValue, Double buyValue, ZonedDateTime zonedDateTime, String serviceId) {
        jdbcTemplate.execute("INSERT INTO currencypairaskbid(currencyPair, buy, sell, time, serviceid) VALUES ('" + cp.name() + "'," + sellValue + "," +  buyValue + "," +   zonedDateTime.toString() + ", " + serviceId + ";");
    }



}
