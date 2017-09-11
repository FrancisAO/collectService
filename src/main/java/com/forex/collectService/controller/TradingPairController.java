package com.forex.collectService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradingPairController {
	
	@RequestMapping(path="/collectTP", method = RequestMethod.POST)
	public void storeTraidingPairPost(@RequestParam("tp") String traidingPair, @RequestParam("sell") String sell, @RequestParam("buy") String buy){
		System.out.println(traidingPair + ", SELL=" + sell + ", BUY=" + buy);
	}
	
	@RequestMapping(path="/collectTP", method = RequestMethod.GET)
	public void storeTraidingPairGet(@RequestParam("tp") String traidingPair, @RequestParam("sell") String sell, @RequestParam("buy") String buy){
		System.out.println(traidingPair + ", SELL=" + sell + ", BUY=" + buy);
	}
	
	
}
