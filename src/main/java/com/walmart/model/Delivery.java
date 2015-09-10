package com.walmart.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Delivery {

	@NotEmpty
	private String map;
	@NotEmpty
	private String from;
	@NotEmpty
	private String to;
	@NotNull
	@DecimalMin(value = "0.00")
	private BigDecimal autonomy;
	@NotNull
	@DecimalMin(value = "0.00")
	private BigDecimal price;

	public Delivery() {
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
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

	public BigDecimal getAutonomy() {
		return autonomy;
	}

	public void setAutonomy(BigDecimal autonomy) {
		this.autonomy = autonomy;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
