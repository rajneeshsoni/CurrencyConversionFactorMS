package com.activity.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.activity.microservice.model.CurrencyConverter;;


public interface CurrencyConvertorRepository extends JpaRepository<CurrencyConverter, String> {
	
	@Query("SELECT c FROM CurrencyConverter c WHERE c.currency = ?1")
	CurrencyConverter findByCurrency(String currency);
	
	/**
	 * Repository API - Fetch currency converter record based on country only. 
	 */
	@Query("SELECT c FROM CurrencyConverter c WHERE c.countryCode = ?1")
	CurrencyConverter findByCountry(String countryCode);

	/**
	 * Repository API - Fetch currency converter record based on country and currency.
	 */
	@Query("SELECT c FROM CurrencyConverter c WHERE c.countryCode = ?1 and c.currency = ?2")
	CurrencyConverter findByCountryAndCurrency(String country, String currency);
	
	/**
	 * Repository API - Fetch currency factor based on currency only.
	 */
	@Query("SELECT conversionFactor FROM CurrencyConverter c WHERE c.currency = ?1")
	Double findConversioFactorByCurrency(String currency);

}
