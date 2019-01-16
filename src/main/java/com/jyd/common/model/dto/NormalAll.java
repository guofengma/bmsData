package com.jyd.common.model.dto;

public class NormalAll {

	private String show_res_code;
	private String show_res_error;
	private Show_res_body_detail show_res_body;
	private Show_req_para show_req_para;
	private String show_res_id;

	public void setShow_res_code(String show_res_code) {
		this.show_res_code = show_res_code;
	}

	public String getShow_res_code() {
		return show_res_code;
	}

	public void setShow_res_error(String show_res_error) {
		this.show_res_error = show_res_error;
	}

	public String getShow_res_error() {
		return show_res_error;
	}

	public Show_res_body_detail getShow_res_body() {
		return show_res_body;
	}

	public void setShow_res_body(Show_res_body_detail show_res_body) {
		this.show_res_body = show_res_body;
	}

	public void setShow_req_para(Show_req_para show_req_para) {
		this.show_req_para = show_req_para;
	}

	public Show_req_para getShow_req_para() {
		return show_req_para;
	}

	public void setShow_res_id(String show_res_id) {
		this.show_res_id = show_res_id;
	}

	public String getShow_res_id() {
		return show_res_id;
	}

}