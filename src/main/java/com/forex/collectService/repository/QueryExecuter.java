package com.forex.collectService.repository;

import com.forex.collectService.model.CurrencyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Component
public class QueryExecuter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertAskBid(CurrencyPair cp, Double sellValue, Double buyValue, OffsetDateTime offsetDateTime, String serviceId) {
        jdbcTemplate.execute("INSERT INTO cpsaskbid(currencypair, buy, sell, time, serviceid) VALUES ('" + cp.name() + "'," + sellValue + "," +  buyValue + ",'" +   offsetDateTime.toString() +  "', '" + serviceId + "');");
    }



}
