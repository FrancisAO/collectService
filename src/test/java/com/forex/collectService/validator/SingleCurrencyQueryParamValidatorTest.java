package com.forex.collectService.validator;

import com.forex.collectService.QueryParamFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;


public class SingleCurrencyQueryParamValidatorTest {


    private SingleCurrencyQueryParamValidator singleCurrencyQueryParamValidator = new SingleCurrencyQueryParamValidator();
    private Set<String> queryParams;

    @Before
    public void setUp(){
        queryParams = givenConfiguredValidQueryParameters();
        Field field = ReflectionUtils.findField(SingleCurrencyQueryParamValidator.class, "validQueryParams");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, singleCurrencyQueryParamValidator, queryParams);
    }

    private Set<String> givenConfiguredValidQueryParameters() {
        Set<String> queryParams = new HashSet<>();
        queryParams.add("c");
        queryParams.add("cpc");
        queryParams.add("ts");
        queryParams.add("sid");
        return queryParams;
    }

    @Test
    public void shouldReturnFalseIfAtLeastOneQueryParamIsNotValid(){
        //given
        Map<String, String> queryParameters = givenNonValidQueryParameters();

        //when
        boolean valid = singleCurrencyQueryParamValidator.valid(queryParameters);

        //then
        assertFalse(valid);

    }

    @Test
    public void shouldReturnTrueIfNoNonValidQueryParameterArePresent(){
        //given
        Map<String, String> queryParameters = QueryParamFactory.createValidSingleCurrencyQueryParams();

        //when
        boolean valid = singleCurrencyQueryParamValidator.valid(queryParameters);

        //then
        assertTrue(valid);

    }

    @Test
    public void shouldReturnFalseIfNoQueryParametersArePresent(){
        //given
        Map<String, String> queryParameters = givenEmptyQueryParameters();

        //when
        boolean valid = singleCurrencyQueryParamValidator.valid(queryParameters);

        //then
        assertFalse(valid);

    }

    private Map<String, String> givenNonValidQueryParameters() {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("nonValidQueryParam", "anyValue");
        return queryParameters;
    }

    private Map<String, String> givenEmptyQueryParameters() {
        Map<String, String> queryParameters = new HashMap<>();
        return queryParameters;
    }

}