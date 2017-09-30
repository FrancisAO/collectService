package com.forex.collectService.extractor;

import com.forex.collectService.model.CurrencyPair;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueryParamValueExtractorTest {

    @Test
    public void thatCurrencyPairIsExtracted(){
        //given
        String currencyPairAsString = "EURUSD";

        //when
        CurrencyPair currencyPair = QueryParamValueExtractor.extractCurrencyPair(currencyPairAsString);

        //then
        assertThat(currencyPair.name(), is(currencyPairAsString));
    }

    @Test
    public void thatNumberIsExtracted(){
        //given
        String number = "1.24";

        //when
        Double extractedNumber = QueryParamValueExtractor.extractNumber(number);

        //then
        assertThat(extractedNumber, is(Double.valueOf(number)));
    }

    @Test
    public void thatTimestampIsExtracted(){
        //given
        String zonedDateTimeString = ZonedDateTime.now().toString();

        //when
        ZonedDateTime zonedDateTime = QueryParamValueExtractor.extractTimestamp(zonedDateTimeString);

        //then
        assertThat(zonedDateTimeString, is(zonedDateTime.toString()));
    }

}