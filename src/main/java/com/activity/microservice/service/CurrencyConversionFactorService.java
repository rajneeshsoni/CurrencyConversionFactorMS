package com.activity.microservice.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.activity.microservice.dao.CurrencyConversionFactorDao;
import com.activity.microservice.model.CurrencyConversionFactorBean;
import com.activity.microservice.model.CurrencyConverter;


@Service
public class CurrencyConversionFactorService {
	
	private static Logger log = LoggerFactory.getLogger(CurrencyConversionFactorService.class);
	
	@Autowired
	CurrencyConversionFactorDao factoryDao;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	/**
	 * Add new currency conversion factor record
	 * @param currencyConverterObject
	 * @return
	 */
	public ResponseEntity<String> addCurrencyConversionFactor(CurrencyConversionFactorBean currencyConverterObjectBean)
	{
		CurrencyConverter currencyConverterDTO = copyBean(currencyConverterObjectBean);
		if(ifRecordAlreadyExist(currencyConverterDTO.getCountryCode()))
		{
			return new ResponseEntity<String>("Record already exist ", HttpStatus.NOT_ACCEPTABLE);
		}
		else
		{
			factoryDao.save(currencyConverterDTO);
			log.debug("New conversion factor added successfully - countryCode :" + currencyConverterDTO.getCountryCode() + " , currency : " + currencyConverterDTO.getCurrency() + " , conversionFactor : " + currencyConverterDTO.getConversionFactor());				
			return new ResponseEntity<String>("Created ", HttpStatus.CREATED);
			
		}
	}
	
	public ResponseEntity<String> updateCurrencyConversionFactor(CurrencyConversionFactorBean currencyConverterObjectBean)
	{
		CurrencyConverter currencyConverterDTO = copyBean(currencyConverterObjectBean);
		
			CurrencyConverter currencyConverterDAO = factoryDao.findByCountryAndCurrency(currencyConverterDTO.getCountryCode(),currencyConverterDTO.getCurrency());
			
			if (currencyConverterDAO == null)
			{
				return new ResponseEntity<String>("Not Found - Invalid data", HttpStatus.NOT_FOUND);
			}
			else
			{
				currencyConverterDAO.setConversionFactor(currencyConverterDTO.getConversionFactor());
				factoryDao.save(currencyConverterDAO);
				return new ResponseEntity<String>("Updated ", HttpStatus.OK);
			}
			
		
	}
	
	
	private CurrencyConverter copyBean(CurrencyConversionFactorBean currencyConverterObject) {
		CurrencyConverter currencyConverterDTO = new CurrencyConverter();
		currencyConverterDTO.setCountryCode(currencyConverterObject.getCountryCode().toUpperCase());
		currencyConverterDTO.setCurrency(currencyConverterObject.getCurrency().toUpperCase());
		currencyConverterDTO.setConversionFactor(currencyConverterObject.getConversionFactor());
		return currencyConverterDTO;
	}
	
   public boolean ifRecordAlreadyExist(String countryCode) {
		
		CurrencyConverter currencyFactor = factoryDao.findByCountry(countryCode);
		return (currencyFactor != null);
	}
	
   /**
	 * API - Get conversion factor for given currency
	 * Return 0.0, if not exist.
	 */

   public double getCurrencyConversionFactor(String currency)
	{
		CurrencyConverter currencyConverterDAO = factoryDao.findByCurrency(currency);
		if (currencyConverterDAO != null)
		{
			return currencyConverterDAO.getConversionFactor();
		}
		else
		{
			return 0.0;
		}
	}

   /**
	 * API -Get conversion factor between two currencies
	 * If any of the currency not present, return 0.0
	 */
	public double getCurrencyConversionFactor(String fromcurrencycode, String tocurrencycode)
	{
		log.info("Service - getCurrencyConversionFactor ");
		
		Double fromConvertionFactor = factoryDao.findConversioFactorByCurrency(fromcurrencycode);
		if(fromConvertionFactor!=null)
		{
			Double toConvertionFactor = factoryDao.findConversioFactorByCurrency(tocurrencycode);
			
			if(toConvertionFactor!=null)
			{
				/*
				 * df2.setRoundingMode(RoundingMode.DOWN); Double damt1 = 1 *
				 * fromConvertionFactor; String convertedVal = df2.format(damt1 /
				 * toConvertionFactor); log.info("getCurrencyConversionFactor " +convertedVal);
				 * return Double.parseDouble(convertedVal);
				 */
				return ((1 / toConvertionFactor) * fromConvertionFactor);
			}
			else
			{
				return 0.0;
			}
		}
		else
		{
			return 0.0;
		}
	}
}
