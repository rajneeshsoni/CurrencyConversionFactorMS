package com.activity.microservice.model;

public class CurrencyConversionFactorBean {
	private Integer id;

	private String countryCode;
	
	private String currency;

	private double conversionFactor;

	public CurrencyConversionFactorBean() {
		super();
	}

	
	public CurrencyConversionFactorBean(String countryCode, String currency, double conversionFactor) {
		super();
		this.countryCode = countryCode;
		this.currency = currency;
		this.conversionFactor = conversionFactor;
	}


	public CurrencyConversionFactorBean(Integer id, String countryCode, String currency, double conversionFactor) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.currency = currency;
		this.conversionFactor = conversionFactor;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	

}
