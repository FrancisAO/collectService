package com.forex.collectService.collector;

import com.forex.collectService.model.CurrencyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AskBidCollector {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addAskBid(CurrencyPair currencyPair, Double ask, Double bid){
        String insert = "INSERT INTO cpaskbid VALUES(" + currencyPair.name() + ","  + ask + "," + bid + "NOW());";
        jdbcTemplate.execute(insert);
    }
}
