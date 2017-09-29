package com.forex.collectService.validator;

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

        return validQueryParams.containsAll(queryParameters.keySet());
    }

    @PostConstruct
    private void givenValidQueryParameters() {
        validQueryParams = new HashSet<>();
        validQueryParams.add("cp");
        validQueryParams.add("sl");
        validQueryParams.add("by");
        validQueryParams.add("ts");
        validQueryParams.add("sid");
    }
}
