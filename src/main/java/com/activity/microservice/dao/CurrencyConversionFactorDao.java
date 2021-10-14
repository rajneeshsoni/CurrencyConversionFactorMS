package com.activity.microservice.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activity.microservice.model.CurrencyConverter;
import com.activity.microservice.repository.CurrencyConvertorRepository;



@Component
public class CurrencyConversionFactorDao {

	private static Logger log = LoggerFactory.getLogger(CurrencyConversionFactorDao.class);
	
	@Autowired
	public CurrencyConvertorRepository repo;

	public CurrencyConverter findByCurrency(String currency)
	{
		return repo.findByCurrency(currency);
	}
	
	public CurrencyConverter findByCountry(String countryCode)
	{
		return repo.findByCountry(countryCode);
	}
	
	public CurrencyConverter findByCountryAndCurrency(String countryCode, String currencyy)
	{
		return repo.findByCountryAndCurrency(countryCode,currencyy);
	}

	public Double findConversioFactorByCurrency(String currency)
	{
		log.info("Dao - getCurrencyConversionFactor ");
		return repo.findConversioFactorByCurrency(currency);
	}

	public void save(CurrencyConverter currencyConversionFactor)
	{
		repo.save(currencyConversionFactor);
	}

}
