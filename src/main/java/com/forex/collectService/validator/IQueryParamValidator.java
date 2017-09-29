package com.forex.collectService.validator;

import java.util.Map;

public interface IQueryParamValidator {

    boolean valid(Map<String, String> requestParams);

}
