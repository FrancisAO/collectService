package com.forex.collectService.controller;

import com.forex.collectService.extractor.QueryParamValueExtractor;
import com.forex.collectService.model.AskBidQueryParams;
import com.forex.collectService.model.CurrencyPair;
import com.forex.collectService.repository.QueryExecuter;
import com.forex.collectService.validator.AskBidQueryParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
public class TradingPairController {


	@Autowired
	private AskBidQueryParamValidator askBidQueryParamValidator;
	@Autowired
	private QueryExecuter queryExecuter;

	@RequestMapping(path="/collectTP", method = RequestMethod.POST)
	public void storeTraidingPairs(@RequestParam Map<String, String> queryParameters){
		Assert.isTrue(askBidQueryParamValidator.valid(queryParameters), "Invalid query parameter.");

		InsertQueryParametersInDatabase(queryParameters);

	}

	private void InsertQueryParametersInDatabase(@RequestParam Map<String, String> queryParameters) {
		CurrencyPair cp = extractCurrencyPair(queryParameters);
		Double sell = extractNumber(AskBidQueryParams.SELL_PRICE, queryParameters);
		Double buy = extractNumber(AskBidQueryParams.BUY_PRICE, queryParameters);
		ZonedDateTime zonedDateTime = extractTimestamp(queryParameters);
		String serviceId = extractServiceId(queryParameters);

		queryExecuter.insertAskBid(cp, sell, buy, zonedDateTime, serviceId);
	}

	private String extractServiceId(Map<String, String> queryParameters) {
		return queryParameters.get(AskBidQueryParams.SERVICE_ID);
	}

	private ZonedDateTime extractTimestamp(Map<String, String> queryParameters) {
		String zonedDateTimeString = queryParameters.get(AskBidQueryParams.TIMESTAMP);
		return ZonedDateTime.parse(zonedDateTimeString);
	}

	private Double extractNumber(String position, Map<String, String> queryParameters) {
		String value = queryParameters.get(position);
		return Double.valueOf(value);
	}

	private CurrencyPair extractCurrencyPair(Map<String, String> queryParams) {
		String currencyPair = queryParams.get(AskBidQueryParams.CURRENCY_PAIR);
		return QueryParamValueExtractor.extractCurrencyPair(currencyPair);
	}


}
