package com.forex.collectService.validator;

import com.forex.collectService.model.AskBidQueryParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class AskBidQueryParamValidator implements IQueryParamValidator{

    private Set<String> validQueryParams;

    @Override
    public boolean valid(Map<String, String> queryParameters) {
        if(queryParameters.isEmpty()){
            return false;
        }
        if(queryParameters.size() != validQueryParams.size()){
            return false;
        }
        if(numberValuesNotValid(queryParameters)){
            return false;
        }

        return validQueryParams.containsAll(queryParameters.keySet());
    }

    private boolean numberValuesNotValid(Map<String, String> queryParameters) {
        String sellVal = queryParameters.get(AskBidQueryParams.SELL_PRICE);
        String buyVal = queryParameters.get(AskBidQueryParams.BUY_PRICE);
        try {
            Double.valueOf(sellVal);
            Double.valueOf(buyVal);
        } catch(NumberFormatException e){
            return true;
        }
        return false;
    }

    @PostConstruct
    private void givenValidQueryParameters() {
        validQueryParams = new HashSet<>();
        validQueryParams.add(AskBidQueryParams.CURRENCY_PAIR);
        validQueryParams.add(AskBidQueryParams.SELL_PRICE);
        validQueryParams.add(AskBidQueryParams.BUY_PRICE);
        validQueryParams.add(AskBidQueryParams.TIMESTAMP);
        validQueryParams.add(AskBidQueryParams.SERVICE_ID);
    }
}
