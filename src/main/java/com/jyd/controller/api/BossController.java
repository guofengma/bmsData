package com.jyd.controller.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.dto.Data;
import com.jyd.common.model.dto.ListContent;
import com.jyd.common.model.dto.ListData1;
import com.jyd.common.model.dto.ListHead;
import com.jyd.common.model.dto.NormalAll;
import com.jyd.common.model.dto.TotalDTO;
import com.jyd.service.ContractService;
import com.jyd.service.StoreService;
import com.jyd.tool.ApiService;
import com.jyd.tool.DateTool;

/**
 * 老板控制层
 * 
 * @author mjy
 *
 */
public class BossController extends Controller {
	private ApiService apiService = ApiService.me;
	private Date begin = DateTool.getNextWeekMonday(new Date(), -6);
	private Date end = new Date();

	public void index() {
		TotalDTO total = new TotalDTO();
		/****** 当日成交额 ********/
		Record cord = ContractService.me.findLoanAmount();
		total.setTodayMoney(cord.get("loan_amount").toString());
		Record cordD = ContractService.me.findDemandAmount();
		total.setWeekMoney(cordD.get("loan_amount").toString());
		ListHead store_head = new ListHead();
		store_head.setCategories("门店");
		ListHead bo_head = new ListHead();
		bo_head.setCategories("成交额");
		ListHead load_head = new ListHead();
		load_head.setCategories("进件额");
		ListHead bo_5_head = new ListHead();
		bo_5_head.setCategories("5万");
		ListHead bo_5_10_head = new ListHead();
		bo_5_10_head.setCategories("5~10万");
		ListHead bo_10_20_head = new ListHead();
		bo_10_20_head.setCategories("10~20万");
		ListHead bo_30_head = new ListHead();
		bo_30_head.setCategories("30W");
		List<ListHead> heads = new ArrayList<>();
		heads.add(store_head);
		heads.add(bo_head);
		heads.add(load_head);
		heads.add(bo_5_head);
		heads.add(bo_5_10_head);
		heads.add(bo_10_20_head);
		heads.add(bo_30_head);
		total.setListHead(heads);

		// 小屏数据
		List<ListData1> data1s = new ArrayList<>();
		ListData1 listData1 = new ListData1();
		listData1.setCategories("1");
		listData1.setData1(1);
		listData1.setData2(1);
		listData1.setData3(1);
		data1s.add(listData1);
		total.setListData1(data1s);

		renderText(JsonKit.toJson(total));
	}

	public void late() {
		String[] t3 = { "M1", "M2", "M3", "M4", "M5", "M6", "M7" };
		String[] d3 = new String[t3.length];
		d3[0] = ContractService.me.findLate_m_1() + "";
		d3[1] = ContractService.me.findLate_m_2() + "";
		d3[2] = ContractService.me.findLate_m_3() + "";
		d3[3] = ContractService.me.findLate_m_4() + "";
		d3[4] = ContractService.me.findLate_m_5() + "";
		d3[5] = ContractService.me.findLate_m_6() + "";
		d3[6] = ContractService.me.findLate_m_7() + "";
		JSONObject json = new JSONObject();
		json.put("l3", t3);
		json.put("t3", t3);
		json.put("d3", d3);

		String[] d_1_3 = new String[t3.length];
		d_1_3[0] = ContractService.me.findLate_m_1_2() + "";
		d_1_3[1] = ContractService.me.findLate_m_2_2() + "";
		d_1_3[2] = ContractService.me.findLate_m_3_2() + "";
		d_1_3[3] = ContractService.me.findLate_m_4_2() + "";
		d_1_3[4] = ContractService.me.findLate_m_5_2() + "";
		d_1_3[5] = ContractService.me.findLate_m_6_2() + "";
		d_1_3[6] = ContractService.me.findLate_m_7_2() + "";
		json.put("d13", d_1_3);
		renderJson(json);
	}

	// 逾期趋势图
	public void lateLine() {
		int month = this.getParaToInt("month", 0);
		Map para = new HashMap();
		if (month != 0) {
			begin = DateTool.getNextWeekMonday(new Date(), -month);
		}
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);

		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		List<Date> dates = DateTool.getBetweenDates(begin, end);

		String[] data = new String[dates.size()];
		for (int i = 0; i < dates.size(); i++) {
			data[i] = sdf.format(dates.get(i));
		}

		para.put("key", "late_m_1");
		NormalAll late_1 = apiService.detailObject(para);

		para.put("key", "late_m_2");
		NormalAll late_2 = apiService.detailObject(para);

		para.put("key", "late_m_3");
		NormalAll late_3 = apiService.detailObject(para);

		para.put("key", "late_m_4");
		NormalAll late_4 = apiService.detailObject(para);

		para.put("key", "late_m_5");
		NormalAll late_5 = apiService.detailObject(para);

		para.put("key", "late_m_6");
		NormalAll late_6 = apiService.detailObject(para);

		para.put("key", "late_m_7");
		NormalAll late_7 = apiService.detailObject(para);

		// 遍历所有门店
		String[] data1 = new String[dates.size()];
		String[] data2 = new String[dates.size()];
		String[] data3 = new String[dates.size()];
		String[] data4 = new String[dates.size()];
		String[] data5 = new String[dates.size()];
		String[] data6 = new String[dates.size()];
		String[] data7 = new String[dates.size()];

		for (int i = 0; i < dates.size(); i++) {
			data1[i] = get(i, dates.size(), late_1).toString();
			data2[i] = get(i, dates.size(), late_2).toString();
			data3[i] = get(i, dates.size(), late_3).toString();
			data4[i] = get(i, dates.size(), late_4).toString();
			data5[i] = get(i, dates.size(), late_5).toString();
			data6[i] = get(i, dates.size(), late_6).toString();
			data7[i] = get(i, dates.size(), late_7).toString();
		}

		JSONObject json = new JSONObject();
		json.put("data", data);
		json.put("data1", data1);
		json.put("data2", data2);
		json.put("data3", data3);
		json.put("data4", data4);
		json.put("data5", data5);
		json.put("data6", data6);
		json.put("data7", data7);
		renderJson(json);
	}

	public BigDecimal get(int i, int size, NormalAll late_1) {
		BigDecimal[] bigDecimal1 = new BigDecimal[size];
		bigDecimal1[i] = new BigDecimal("0");
		for (Data date : late_1.getShow_res_body().getDatas()) {
			bigDecimal1[i] = bigDecimal1[i].add(date.getData()[i]);
		}
		return bigDecimal1[i];
	}

	public void spules() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		int month = this.getParaToInt("month", 0);
		Map para = new HashMap();
		if (month != 0) {
			begin = DateTool.getNextWeekMonday(new Date(), -month);
		}
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);
		List<Date> dates = DateTool.getBetweenDates(begin, end);
		para.put("key", "must_capital");
		NormalAll must_capital_object = apiService.detailObject(para);

		para.put("key", "must_interest");
		NormalAll must_interest_object = apiService.detailObject(para);

		para.put("key", "alr_capital");
		NormalAll real_capital_object = apiService.detailObject(para);

		para.put("key", "alr_interest");
		NormalAll real_interest_object = apiService.detailObject(para);

		String[] l5 = { "剩余本金", "剩余利息" };

		String[] capital5 = new String[dates.size()];
		String[] interest5 = new String[dates.size()];
		String[] t5 = new String[dates.size()];

		for (int j = 0; j < dates.size(); j++) {
			t5[j] = sdf.format(dates.get(j));
			BigDecimal must_capital_decimal = get(j, dates.size(), must_capital_object);
			BigDecimal must_interest_decimal = get(j, dates.size(), must_interest_object);
			BigDecimal real_capital_decimal = get(j, dates.size(), real_capital_object);
			BigDecimal real_interest_decimal = get(j, dates.size(), real_interest_object);
			capital5[j] = must_capital_decimal.subtract(real_capital_decimal).toString();
			interest5[j] = must_interest_decimal.subtract(real_interest_decimal).toString();
		}
		JSONObject json = new JSONObject();
		json.put("t5", t5);
		json.put("l5", l5);
		List<String[]> d5 = new ArrayList<>();
		d5.add(capital5);
		d5.add(interest5);
		json.put("i", interest5);
		json.put("c", capital5);
		json.put("d5", d5);
		renderJson(json);
	}

	public void use() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int month = this.getParaToInt("month", 0);
		Map para = new HashMap();
		if (month != 0) {
			begin = DateTool.getNextWeekMonday(new Date(), -month);
		}
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);

		para.put("key", "loan_amount_product_1");
		NormalAll product_1m = apiService.detailObject(para);
		para.put("key", "loan_amount_product_2");
		NormalAll product_2m = apiService.detailObject(para);
		para.put("key", "loan_amount_product_3");
		NormalAll product_3m = apiService.detailObject(para);
		para.put("key", "loan_amount_product_4");
		NormalAll product_4m = apiService.detailObject(para);
		List<Date> dates = DateTool.getBetweenDates(begin, end);
		BigDecimal p_d_1 = new BigDecimal("0");
		BigDecimal p_d_2 = new BigDecimal("0");
		BigDecimal p_d_3 = new BigDecimal("0");
		BigDecimal p_d_4 = new BigDecimal("0");

		for (int i = 0; i < dates.size(); i++) {
			p_d_1 = p_d_1.add(get(i, dates.size(), product_1m));
			p_d_2 = p_d_2.add(get(i, dates.size(), product_2m));
			p_d_3 = p_d_3.add(get(i, dates.size(), product_3m));
			p_d_4 = p_d_4.add(get(i, dates.size(), product_4m));
		}
		String[] t1 = { "车贷", "优信贷", "优车贷", "优速贷" };
		String[] d1 = new String[t1.length];

		double total = p_d_1.doubleValue() + p_d_2.doubleValue() + p_d_3.doubleValue() + p_d_4.doubleValue();

		int num = 100;
		double p1 = (p_d_1.doubleValue() / total) * num;
		double p2 = (p_d_2.doubleValue() / total) * num;
		double p3 = (p_d_3.doubleValue() / total) * num;
		double p4 = (p_d_4.doubleValue() / total) * num;

		d1[0] = Math.floor(p1) + "";
		d1[1] = Math.floor(p2) + "";
		d1[2] = Math.floor(p3) + "";
		d1[3] = Math.floor(p4) + "";

		JSONObject json = new JSONObject();
		json.put("uset1", t1);
		json.put("used1", d1);
		renderJson(json);
	}

	public void loan_month() {
		int month = this.getParaToInt("month", 0);
		Map para = new HashMap();
		if (month != 0) {
			begin = DateTool.getNextWeekMonday(new Date(), -month);
		}
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);
		para.put("key", "entry_contract_num");
		NormalAll loan_object = apiService.detailObject(para);
		List<BaStore> stores = StoreService.me.findAll();
		String[] t = new String[stores.size()];
		String[] d = new String[stores.size()];
		for (int i = 0; i < stores.size(); i++) {
			t[i] = stores.get(i).getShortName();
			Data data1 = loan_object.getShow_res_body().getDatas().get(i);
			int a = 0;
			for (BigDecimal b : data1.getData()) {
				a += b.intValue();
			}
			d[i] = a + "";
		}
		JSONObject json = new JSONObject();
		json.put("t", t);
		json.put("d", d);
		renderJson(json);
	}

	public void loan() throws IOException, ParseException {
		int month = this.getParaToInt("month", 0);
		Map para = new HashMap();
		if (month != 0) {
			begin = DateTool.getNextWeekMonday(new Date(), -month);
		}
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);
		para.put("key", "loan_amount");
		NormalAll loan_object = apiService.detailObject(para);
		List<Date> dates = DateTool.getBetweenDates(begin, end);
		String[] t1 = new String[dates.size()];
		String[] d1 = new String[dates.size()];
		SimpleDateFormat sdf = new SimpleDateFormat("dd");

		for (int j = 0; j < dates.size(); j++) {
			t1[j] = sdf.format(dates.get(j));
			d1[j] = get(j, dates.size(), loan_object).toString();
		}
		JSONObject json = new JSONObject();
		json.put("t", t1);
		json.put("d", d1);
		renderJson(json);
	}

	public void loanNum() {
		Map para = new HashMap();
		para.put("begin", begin);
		para.put("end", end);
		para.put("type", 3);
		para.put("value", 0);
		para.put("key", "principal_5w");
		NormalAll pri_5 = apiService.detailObject(para);
		para.put("key", "principal_5_10w");
		NormalAll pri_5_10 = apiService.detailObject(para);
		para.put("key", "principal_10_20w");
		NormalAll pri_10_20 = apiService.detailObject(para);
		para.put("key", "principal_20_30w");
		NormalAll pri_20_30 = apiService.detailObject(para);
		para.put("key", "principal_30w");
		NormalAll pri_30 = apiService.detailObject(para);
		String[] t2 = { "5万", "5~10万", "10~20万", "20~30万", "30万以上" };
		String[] d2 = new String[t2.length];
		BigDecimal big_5 = new BigDecimal("0");
		for (Data data : pri_5.getShow_res_body().getDatas()) {
			for (BigDecimal b : data.getData()) {
				big_5 = big_5.add(b);
			}
		}
		d2[0] = big_5.toString();
		BigDecimal big_5_10 = new BigDecimal("0");
		for (Data data : pri_5_10.getShow_res_body().getDatas()) {
			for (BigDecimal b : data.getData()) {
				big_5_10 = big_5_10.add(b);
			}
		}
		d2[1] = big_5_10.toString();
		BigDecimal big_10_20 = new BigDecimal("0");
		for (Data data : pri_10_20.getShow_res_body().getDatas()) {
			for (BigDecimal b : data.getData()) {
				big_10_20 = big_10_20.add(b);
			}
		}
		d2[2] = big_10_20.toString();
		BigDecimal big_20_30 = new BigDecimal("0");
		for (Data data : pri_20_30.getShow_res_body().getDatas()) {
			for (BigDecimal b : data.getData()) {
				big_20_30 = big_20_30.add(b);
			}
		}
		d2[3] = big_20_30.toString();
		BigDecimal big_30 = new BigDecimal("0");
		for (Data data : pri_30.getShow_res_body().getDatas()) {
			for (BigDecimal b : data.getData()) {
				big_30 = big_30.add(b);
			}
		}
		d2[4] = big_30.toString();
		JSONObject json = new JSONObject();
		json.put("t2", t2);
		json.put("d2", d2);
		renderJson(json);
	}

	// 当天的数据量查询有点慢独立出来
	public void mapData() {
		/*** 地图 ****/
		List<BaStore> storeList = StoreService.me.findAll();
		String[] t7 = { "广东", "河南", "山西", "广西" };
		// 省份对应市
		String[] d7 = new String[t7.length];
		String[] d8 = new String[t7.length];
		List<Record> cords = ContractService.me.findLoanAmounts();
		List<Record> daCords = ContractService.me.findDemandAmounts();
		for (int i = 0; i < d7.length; i++) {
			BigDecimal sumD7 = new BigDecimal("0");
			BigDecimal sumD8 = new BigDecimal("0");
			for (BaStore store : storeList) {
				if (store.getProvince().indexOf(t7[i]) != -1) {
					// 第一次全部拿属于广东省的市 门店
					for (Record r : cords) {
						if (r.getInt("store_id") == store.getId()) {
							sumD7 = sumD7.add(r.getBigDecimal("loan_amount"));
						}
					}
					for (Record r : daCords) {
						if (r.getInt("store_id") == store.getId()) {
							sumD8 = sumD8.add(r.getBigDecimal("demand_amount"));
						}
					}
				}
			}
			// 汇总一个省的数据
			d7[i] = sumD7.toString();
			d8[i] = sumD8.toString();
		}
		JSONObject json = new JSONObject();
		json.put("t7", t7);
		json.put("d7", d7);
		json.put("d8", d8);
		renderJson(json);
	}

	// 当天的数据量查询有点慢独立出来
	public void currentData() {
		String pro = getPara("prov");
		List<ListContent> contents = new ArrayList<>();
		List<BaStore> storeList = StoreService.me.findAll();
		List<Record> cords = ContractService.me.findLoanAmounts();
		List<Record> daCords = ContractService.me.findDemandAmounts();
		List<Record> record_5ws = ContractService.me.find_5w();
		List<Record> record_5_10ws = ContractService.me.find_5_10w();
		List<Record> record_10_20ws = ContractService.me.find_10_20w();
		List<Record> record_20_30ws = ContractService.me.find_20_30w();
		List<Record> record_30ws = ContractService.me.find_30w();

		for (BaStore store : storeList) {
			if (store.getProvince().indexOf(pro) != -1) {
				ListContent content = new ListContent();
				content.setCategories(store.getShortName());
				BigDecimal loan_amount = new BigDecimal("0");
				for (Record r : cords) {
					if (r.getInt("store_id") == store.getId()) {
						loan_amount = loan_amount.add(r.getBigDecimal("loan_amount"));
					}
				}
				content.setData1(loan_amount);
				BigDecimal demand_amount = new BigDecimal("0");
				for (Record r : daCords) {
					if (r.getInt("store_id") == store.getId()) {
						demand_amount = demand_amount.add(r.getBigDecimal("demand_amount"));
					}
				}
				content.setData2(demand_amount);
				long cimal_record_5w = 0;
				for (Record r : record_5ws) {
					if (r.getInt("store_id") == store.getId()) {
						cimal_record_5w += r.getLong("principal");
					}
				}
				content.setData3(cimal_record_5w);

				long cimal_record_5_10w = 0;
				for (Record r : record_5_10ws) {
					if (r.getInt("store_id") == store.getId()) {
						cimal_record_5_10w += r.getLong("principal");
					}
				}
				content.setData4(cimal_record_5_10w);

				long cimal_record_10_20w = 0;
				for (Record r : record_10_20ws) {
					if (r.getInt("store_id") == store.getId()) {
						cimal_record_10_20w += r.getLong("principal");
					}
				}
				content.setData5(cimal_record_10_20w);

				long cimal_record_20_30w = 0;
				for (Record r : record_20_30ws) {
					if (r.getInt("store_id") == store.getId()) {
						cimal_record_20_30w += r.getLong("principal");
					}
				}
				content.setData5(cimal_record_20_30w);

				long cimal_record_30w = 0;
				for (Record r : record_30ws) {
					if (r.getInt("store_id") == store.getId()) {
						cimal_record_30w += r.getLong("principal");
					}
				}
				content.setData6(cimal_record_30w);
				contents.add(content);
			}
		}
		renderJson(JsonKit.toJson(contents));
	}

}
