package com.forex.collectService.validator;

import com.forex.collectService.model.SingleCurrencyQueryParams;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class SingleCurrencyQueryParamValidator implements IQueryParamValidator {

    private Set<String> validQueryParams;

    @Override
    public boolean valid(Map<String, String> queryParameters) {
        if(queryParameters.isEmpty()){
            return false;
        }
        if(queryParameters.size() != validQueryParams.size()){
            return false;
        }

        return validQueryParams.containsAll(queryParameters.keySet());
    }

    @PostConstruct
    private void givenValidQueryParameters() {
        validQueryParams = new HashSet<>();
        validQueryParams.add(SingleCurrencyQueryParams.CURRENCY);
        validQueryParams.add(SingleCurrencyQueryParams.TIMESTAMP);
        validQueryParams.add(SingleCurrencyQueryParams.CURRENCY_COURSE);
        validQueryParams.add(SingleCurrencyQueryParams.SERVICE_ID);
    }
}
