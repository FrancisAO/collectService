package com.forex.collectService.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class QueryParamValidator {

    @Value("#{'${collectService.requestPath.collectTp.queryParams}'.split(',')}")
    private Set<String> validQueryParams;

    public boolean valid(Map<String, String> queryParameters) {
        if(queryParameters.isEmpty()){
            return false;
        }
        if(queryParameters.size() > validQueryParams.size()){
            return false;
        }

        return validQueryParams.containsAll(queryParameters.keySet());
    }
}
