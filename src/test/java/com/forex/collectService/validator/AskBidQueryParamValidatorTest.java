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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AskBidQueryParamValidatorTest {

    private AskBidQueryParamValidator askBidQueryParamValidator = new AskBidQueryParamValidator();
    private Set<String> queryParams;

    @Before
    public void setUp(){
        queryParams = givenConfiguredValidQueryParameters();
        Field field = ReflectionUtils.findField(AskBidQueryParamValidator.class, "validQueryParams");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, askBidQueryParamValidator, queryParams);
    }

    private Set<String> givenConfiguredValidQueryParameters() {
        queryParams = new HashSet<>();
        queryParams.add("cp");
        queryParams.add("sl");
        queryParams.add("by");
        queryParams.add("sid");
        queryParams.add("ts");
        return queryParams;
    }

    @Test
    public void shouldReturnFalseIfAtLeastOneQueryParamIsNotValid(){
        //given
        Map<String, String> queryParameters = givenNonValidQueryParameters();

        //when
        boolean valid = askBidQueryParamValidator.valid(queryParameters);

        //then
        assertFalse(valid);

    }

    @Test
    public void shouldReturnTrueIfNoNonValidQueryParameterArePresent(){
        //given
        Map<String, String> queryParameters = QueryParamFactory.createValidCurrencyPairQueryParams();

        //when
        boolean valid = askBidQueryParamValidator.valid(queryParameters);

        //then
        assertTrue(valid);

    }

    @Test
    public void shouldReturnFalseIfNoQueryParametersArePresent(){
        //given
        Map<String, String> queryParameters = givenEmptyQueryParameters();

        //when
        boolean valid = askBidQueryParamValidator.valid(queryParameters);

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
