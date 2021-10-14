package com.activity.microservice.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CurrencyConverter {
	
	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String countryCode;
	
	@Column(nullable = false)
	private String currency;

	@Column(nullable = false)
	private double conversionFactor;

	
	public CurrencyConverter() {
		super();
		this.id = 0;
		this.countryCode = null;
		this.currency = null;
		this.conversionFactor = 0.0;
	}
	
	public CurrencyConverter(String countryCode,String currency, double conversionFactor) {
		super();
		this.countryCode = countryCode;
		this.currency = currency;
		this.conversionFactor = conversionFactor;
	}

	@Override
	public String toString() {
		return "CurrencyConverter [ id = " + id + " , countryCode=" + countryCode + ", currency=" + currency + ", conversionFactor=" + conversionFactor + "]";
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

	public double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	

}
