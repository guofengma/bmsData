package com.jyd.common.model.dto;

import java.util.List;

public class Show_res_body_detail {

	private String type;
	private List<Data> datas;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

}