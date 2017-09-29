package com.forex.collectService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueryExecuter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertAskBid() {

    }



}
