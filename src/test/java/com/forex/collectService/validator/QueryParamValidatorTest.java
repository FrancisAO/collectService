package com.forex.collectService.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryParamValidatorTest {

    @Autowired
    private QueryParamValidator queryParamValidator;


    @Test
    public void shouldReturnFalseIfAtLeastOneQueryParamIsNotValid(){
        //given
        Map<String, String> queryParameters = givenNonValidQueryParameters();

        //when
        boolean valid = queryParamValidator.valid(queryParameters);

        //then
        assertFalse(valid);

    }

    @Test
    public void shouldReturnTrueIfNoNonValidQueryParameterArePresent(){
        //given
        Map<String, String> queryParameters = givenValidQueryParameters();

        //when
        boolean valid = queryParamValidator.valid(queryParameters);

        //then
        assertTrue(valid);

    }

    @Test
    public void shouldReturnFalseIfNoQueryParametersArePresent(){
        //given
        Map<String, String> queryParameters = givenEmptyQueryParameters();

        //when
        boolean valid = queryParamValidator.valid(queryParameters);

        //then
        assertFalse(valid);

    }

    private Map<String, String> givenNonValidQueryParameters() {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("nonValidQueryParam", "anyValue");
        return queryParameters;
    }

    private Map<String, String> givenValidQueryParameters() {
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("cp", "EURUSD");
        return queryParameters;
    }

    private Map<String, String> givenEmptyQueryParameters() {
        Map<String, String> queryParameters = new HashMap<>();
        return queryParameters;
    }
}
