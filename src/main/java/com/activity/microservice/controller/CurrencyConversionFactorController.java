package com.activity.microservice.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.activity.microservice.model.CurrencyConversionFactorBean;
import com.activity.microservice.service.CurrencyConversionFactorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/conversionfactor")
public class CurrencyConversionFactorController {
	
	private static Logger log = LoggerFactory.getLogger(CurrencyConversionFactorController.class);
	
	@Autowired
	private CurrencyConversionFactorService service;
	
	
	/**
	 * API - To add new conversion factor 
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public ResponseEntity<String> addConversionFactor(@RequestBody CurrencyConversionFactorBean currencyConversionFactorBean)
	{
		log.info("--- Add conversion factor --- Country code : " + currencyConversionFactorBean.getCountryCode() + ", currency : "+ currencyConversionFactorBean.getCurrency() + ", conversio factory : " + currencyConversionFactorBean.getConversionFactor());
		return service.addCurrencyConversionFactor(currencyConversionFactorBean);
	}
	
	/**
	 * API - To update existing conversion factor 
	 */
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<String> updateConversionFactor(@RequestBody CurrencyConversionFactorBean currencyConversionFactorBean)
	{
		log.debug("--- Update conversion factor --- Country code : " + currencyConversionFactorBean.getCountryCode() + ", currency : "+ currencyConversionFactorBean.getCurrency() + ", conversio factory : " + currencyConversionFactorBean.getConversionFactor());
		return service.updateCurrencyConversionFactor(currencyConversionFactorBean);
		
	}

	/**
	 * API - To get conversion factor for given currency w.r.t USD
	 */
	@RequestMapping(value = "/{currency}", method = RequestMethod.GET)
	public double getConversionFactor(@PathVariable String currency)
	{
		log.debug("--- Get conversion factor --- currency : "+ currency );
		return service.getCurrencyConversionFactor(currency.toUpperCase());
	}
	
	/**
	 * API - To get conversion factor between two currencies w.r.t. USD
	 */
	@RequestMapping (path="/{fromcurrencycode}/{tocurrencycode}", method = RequestMethod.GET)
	public double getCurrencyConversionFactor(@PathVariable String fromcurrencycode,@PathVariable String tocurrencycode)
	{
		log.info("Start controller - getCurrencyConversionFactor ");
		return service.getCurrencyConversionFactor(fromcurrencycode.toUpperCase(),tocurrencycode.toUpperCase());
	}

	
	
}
