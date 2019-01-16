package com.jyd.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.dto.NormalDetail;
import com.jyd.common.model.dto.Show_req_para;
import com.jyd.service.StoreService;
import com.jyd.service.front.ContractSercice;
import com.jyd.tool.ApiService;

public class LateFeeController extends Controller {

	private StoreService storeS = StoreService.me;
	private ContractSercice contractS = ContractSercice.me;

	public void index() {
		render("/contract/lateFee.html");
	}

	/**
	 * 初始化加载门店下拉框
	 */
	public void initStores() {
		//查找所有门店
		List<BaStore> stores=storeS.findAll();

        //筛选门店
		for(int i=0;i<stores.size();i++){
		    if(stores.get(i).getShortName().equals("联融公司"))
		    	stores.remove(i);
		    if(stores.get(i).getShortName().equals("河南分中心"))
		    	stores.remove(i);
		    if(stores.get(i).getShortName().equals("广州分中心"))
		    	stores.remove(i);
		    if(stores.get(i).getShortName().equals("p2p"))
		    	stores.remove(i);
		    if(stores.get(i).getShortName().equals("总部"))
		    	stores.remove(i);
	    	if(stores.get(i).getShortName().equals("东莞分中心"))
		    	stores.remove(i);
		}


		setAttr("stores", stores);
		renderJson();
	}

	/**
	 * 根据门店查询逾期费用各类型的数值
	 * 
	 * @throws TException
	 */
	public void findTypes() throws TException {
		int storeId = getParaToInt("storeId");
		String[] types = { "late_num", "principal_5w", "principal_5_10w", "principal_10_20w", "principal_20_30w",
				"principal_30w" };
		String[] types2 = { "late_amount" };
		List<Long> typeList = new ArrayList<>();

		// 数量
		Long count1 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND store_id = ? ",
				storeId);
		Long count2 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND principal <= 50000 AND store_id = ? ",
				storeId);
		Long count3 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND principal > 50000 AND principal <= 100000 AND store_id = ? ",
				storeId);
		Long count4 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND principal > 100000 AND principal <= 200000 AND store_id = ? ",
				storeId);
		Long count5 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND principal > 200000 AND principal <= 300000 AND store_id = ? ",
				storeId);
		Long count6 = Db.queryLong(
				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND late_fee > 0 AND principal > 300000 AND store_id = ? ",
				storeId);

		typeList.add(count1 != null ? count1 : 0);
		typeList.add(count2 != null ? count2 : 0);
		typeList.add(count3 != null ? count3 : 0);
		typeList.add(count4 != null ? count5 : 0);
		typeList.add(count5 != null ? count5 : 0);
		typeList.add(count6 != null ? count6 : 0);
		for (int i = 0; i < types.length; i++) {
			setAttr(types[i], typeList.get(i));
		}

		List<Double> typeList2 = new ArrayList<>();
		Double count7 = Db.queryDouble(
				"SELECT SUM(late_fee) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND store_id = ?",
				storeId);
		typeList2.add(count7 != null ? count7 : 0.0);
		for (int i = 0; i < types2.length; i++) {
			setAttr(types2[i], typeList2.get(i));
		}

		renderJson();
	}

	public void weekAndMonth() {
		Integer type = getParaToInt("type");
		Integer value = getParaToInt("value");
		String selected = getPara("selected");
		String beginDate = null;
		String endDate = null;

		if (selected != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (selected.equals("week")) {
				calendar.add(Calendar.DATE, -1);
				endDate = sdf.format(calendar.getTime());
				calendar.add(Calendar.DATE, -6);
				beginDate = sdf.format(calendar.getTime());
			}
			if (selected.equals("month")) {
				calendar.add(Calendar.DATE, -1);
				endDate = sdf.format(calendar.getTime());
				calendar.add(Calendar.MONTH, -1);
				beginDate = sdf.format(calendar.getTime());
			}

		}

		String[] keys = { "late_num", "principal_5w", "principal_5_10w", "principal_10_20w", "principal_20_30w",
				"principal_30w", "late_amount" };

		/********** 构造json类 ***********/
		NormalDetail detail = new NormalDetail();
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginDate);
		reqPara.setEnd_date(endDate);
		detail.setShow_req_para(reqPara);
		detail.setShow_res_error("参数有误!");
		detail.setShow_res_code("-1");
		detail.setShow_res_id(StrKit.getRandomUUID());

		/******** 失败返回json *********/
		if (beginDate == null || endDate == null || type == 0 || value == 0) {
			renderJson(JsonKit.toJson(detail));
		} else {
			List<String> list = new ArrayList<>();
			for (String key : keys) {
				Map para = new HashMap();
				para.put("begin", beginDate);
				para.put("end", endDate);
				para.put("type", type);
				para.put("value", value);
				para.put("key", key);
				list.add(ApiService.me.single(para));
			}
			setAttr("list", list);
			renderJson();
		}
		/******** 成功返回json *********/
	}

	public void day() {
		int storeId = getParaToInt("value");
		String[] types = { "late_num", "late_amount", "principal_5w", "principal_5_10w", "principal_10_20w",
				"principal_20_30w", "principal_30w" };
		List<List<Record>> typeList = new ArrayList<>();
		List<Record> count1 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.store_id = ? ",
				storeId);
		List<Record> count2 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.principal <= 50000 AND a.store_id = ? ",
				storeId);
		List<Record> count3 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.principal > 50000 AND a.principal <= 100000 AND a.store_id = ? ",
				storeId);
		List<Record> count4 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.principal > 100000 AND a.principal <= 200000 AND a.store_id = ? ",
				storeId);
		List<Record> count5 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.principal > 200000 AND a.principal <= 300000 AND a.store_id = ? ",
				storeId);
		List<Record> count6 = Db.find(
				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no,a.principal, a.overdue_days, a.late_fee FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) and a.late_state=1 AND a.late_fee > 0 AND a.principal > 300000 AND a.store_id = ? ",
				storeId);

		typeList.add(count1);
		typeList.add(count2);
		typeList.add(count3);
		typeList.add(count4);
		typeList.add(count5);
		typeList.add(count6);

		// for (List<Record> list : typeList) {
		// for (Record obj : list) {
		// String idcard = obj.getStr("cus_id_no").replaceAll("(\\d{4})\\d{10}(\\w{4})",
		// "$1****$2");
		// obj.set("cus_id_no", idcard);
		// }
		// }

		setAttr("list", typeList);

		List<Double> moneys = new ArrayList<>();
		// 逾期金额
		Double count7 = Db.queryDouble(
				"SELECT SUM(late_fee) FROM cus_contract WHERE state NOT IN (5, -1) and late_state=1 AND store_id = ?",
				storeId);
		moneys.add(count7 != null ? count7 : 0.0);

		setAttr("moneys", moneys);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		setAttr("date", date);

		renderJson();

	}

}