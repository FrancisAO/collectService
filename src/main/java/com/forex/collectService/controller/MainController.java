package com.forex.collectService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String mainPage(){
		return "Hello world :-)";
	}

}
