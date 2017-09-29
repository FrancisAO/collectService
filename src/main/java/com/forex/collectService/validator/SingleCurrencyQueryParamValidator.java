package com.forex.collectService.validator;

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
        validQueryParams.add("c");
        validQueryParams.add("ts");
        validQueryParams.add("cpc");
        validQueryParams.add("sid");
    }
}
