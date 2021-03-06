package com.forex.collectService.controller;

import com.forex.collectService.validator.SingleCurrencyQueryParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SingleCurrencyCourseController {

    @Autowired
    private SingleCurrencyQueryParamValidator singleCurrencyQueryParamValidator;

    @RequestMapping(path="/collectSC", method = RequestMethod.POST)
    public void storeTraidingPairs(@RequestParam Map<String, String> queryParameters){
        Assert.isTrue(singleCurrencyQueryParamValidator.valid(queryParameters), "Invalid query parameter.");



    }

}
