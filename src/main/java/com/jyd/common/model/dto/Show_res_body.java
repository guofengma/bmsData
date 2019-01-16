/**
  * Copyright 2018 bejson.com 
  */
package com.jyd.common.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2018-11-08 16:44:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Show_res_body {

	private String type;
	private List<String> month;
	private BigDecimal[] data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getMonth() {
		return month;
	}

	public void setMonth(List<String> month) {
		this.month = month;
	}

	public BigDecimal[] getData() {
		return data;
	}

	public void setData(BigDecimal[] data) {
		this.data = data;
	}

	
}