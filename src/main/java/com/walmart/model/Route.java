package com.walmart.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Route {
	
	@NotEmpty
	private String from;
	@NotEmpty
	private String to;
	@NotNull
	@DecimalMin(value = "0.00")
	private BigDecimal amount;

	public Route() {
		
	}
	
	public Route(String from, String to, BigDecimal amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
