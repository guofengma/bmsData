package com.jyd.controller.api;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jyd.common.model.dto.NormalDetail;
import com.jyd.common.model.dto.Show_req_para;
import com.jyd.tool.ApiService;

public class IndexApiController extends Controller {
	private ApiService apiService = ApiService.me;

	public void index() {
		JSONObject json = new JSONObject();
		json.put("test", "hellworld");
		renderText(json.toJSONString());
	}

	public void single() {
		/******** 参数接入 ********/
		String beginDate = this.getPara("begin");
		String endDate = this.getPara("end");
		Integer type = this.getParaToInt("type", 0);
		Integer value = this.getParaToInt("value", 0);
		String key = this.getPara("key");

		/********** 构造json类 ***********/
		NormalDetail detail = new NormalDetail();
		Show_req_para reqPara = new Show_req_para();

		reqPara.setBegin_date(beginDate);
		reqPara.setEnd_date(endDate);
		reqPara.setKey(key);
		detail.setShow_req_para(reqPara);
		detail.setShow_res_error("参数有误!");
		detail.setShow_res_code("-1");
		detail.setShow_res_id(StrKit.getRandomUUID());

		/******** 失败返回json *********/
		if (beginDate == null || endDate == null || type == 0 || value == 0 || key == null) {
			renderJson(JsonKit.toJson(detail));
		} else {
			Map para = new HashMap();
			para.put("begin", beginDate);
			para.put("end", endDate);
			para.put("type", type);
			para.put("value", value);
			para.put("key", key);
			renderJson(apiService.single(para));
		}
		/******** 成功返回json *********/
	}

	public void detail() {
		/******** 参数接入 ********/
		String beginDate = this.getPara("begin");
		String endDate = this.getPara("end");
		String key = this.getPara("key");
		Integer type = this.getParaToInt("type", 0);
		Integer value = this.getParaToInt("value", 0);
		/********** 构造json类 ***********/
		NormalDetail detail = new NormalDetail();
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginDate);
		reqPara.setEnd_date(endDate);
		reqPara.setKey(key);
		detail.setShow_req_para(reqPara);
		detail.setShow_res_error("参数有误!");
		detail.setShow_res_code("-1");
		detail.setShow_res_id(StrKit.getRandomUUID());

		/******** 失败返回json *********/
		if (beginDate == null || endDate == null || key == null) {
			renderJson(JsonKit.toJson(detail));
		} else {
			Map para = new HashMap();
			para.put("begin", beginDate);
			para.put("end", endDate);
			para.put("type", type);
			para.put("value", value);
			para.put("key", key);
			renderJson(apiService.detail(para));
		}
		/******** 成功返回json *********/
	}

}
