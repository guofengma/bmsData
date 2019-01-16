package com.jyd.common.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class Data {
	private List<String> month;
	private BigDecimal[] data;
	private String name;

	public BigDecimal[] getData() {
		return data;
	}

	public void setData(BigDecimal[] data) {
		this.data = data;
	}

	public List<String> getMonth() {
		return month;
	}

	public void setMonth(List<String> month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
