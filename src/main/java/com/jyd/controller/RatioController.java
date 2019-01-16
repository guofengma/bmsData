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
import com.jyd.common.model.dto.NormalDetail;
import com.jyd.common.model.dto.Show_req_para;
import com.jyd.service.StoreService;
import com.jyd.service.front.ContractSercice;
import com.jyd.tool.ApiService;

/**
 * 比率
 * @author aa
 *
 */
public class RatioController extends Controller{
	
	private StoreService storeS = StoreService.me;
	private ContractSercice contractS = ContractSercice.me;

	public void index() {
		render("/contract/ratio.html");
	}

	/**
	 * 初始化加载门店下拉框
	 */
	public void initStores() {
		// 查找所有门店
		setAttr("stores", storeS.findAll());
		renderJson();
	}

	/**
	 * 根据门店查询比率各类型的数值
	 * 
	 * @throws TException
	 */
	public void findTypes() throws TException {
		int storeId = getParaToInt("storeId");
		
		String[] types = { "must_capital", "alr_capital", "must_interest",
				"alr_interest", "ratio_capital", "ratio_interest" };
		List<Long> typeList = new ArrayList<>();
		
		Long count1 = Db.queryLong("SELECT SUM(( SELECT SUM(CONVERT( capital , DECIMAL (10 , 2 ))) as must_capital FROM cus_contract_stage WHERE cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND c.store_id =?", storeId);
		Long count2 = Db.queryLong("SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( capital , DECIMAL (10 , 2 ))) as alr_capital FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND c.store_id = ?", storeId);
		Long count3 = Db.queryLong(" SELECT ROUND(SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) FROM cus_contract_stage WHERE cus_contract_id = c.id ))) as must_interest FROM cus_contract c WHERE state NOT IN (-1, 5) and store_id = ?", storeId);
		Long count4 = Db.queryLong("SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) as alr_interest FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND store_id =?", storeId);
		typeList.add(count1 != null ? count1 : 0);
		typeList.add(count2 != null ? count2 : 0);
		typeList.add(count3 != null ? count3 : 0);
		typeList.add(count4 != null ? count4 : 0);
		for (int i = 0; i < types.length - 2; i++) {
			setAttr(types[i], typeList.get(i));
		}
		if(count1 != null && count1 != 0 && count2 != null) {
			setAttr(types[4], count2*1.0/count1);
		}else {
			setAttr(types[4], 0L);
		}
		if(count3 != null && count3 != 0 && count4 != null) {
			setAttr(types[5], count4*1.0/count3);
		}else {
			setAttr(types[5], 0L);
		}
		
		
		renderJson();
	}
	
	
	public void weekAndMonth() {
		Integer type = getParaToInt("type");
		Integer value = getParaToInt("value");
		String selected = getPara("selected");
		String beginDate = null;
		String endDate = null;
		
		if(selected != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(selected.equals("week")) {
				calendar.add(Calendar.DATE, -1);
				endDate = sdf.format(calendar.getTime());
				calendar.add(Calendar.DATE, -6);
				beginDate = sdf.format(calendar.getTime());
			}
			if(selected.equals("month")) {
				calendar.add(Calendar.DATE, -1);
				endDate = sdf.format(calendar.getTime());
				calendar.add(Calendar.MONTH, -1);
				beginDate = sdf.format(calendar.getTime());
			}
			
		}
		
		String[] keys = {"must_capital", "alr_capital", "must_interest",
				"alr_interest", "ratio_capital", "ratio_interest"};

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
//		List<List<Record>> typeList = new ArrayList<>();
//		
//		List<Record> record1 = Db.find("SELECT b.short_name, a.cus_name, a.cus_id_no, a.cus_phone, a.create_user FROM cus_contract_schedule_track a, ba_store b WHERE a.store_id=b.id and DATE_FORMAT(a.create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and a.store_id =?", storeId);
//		List<Record> record2 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) AND DATE_FORMAT(a.create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and a.store_id =?", storeId);
//		typeList.add(record1);
//		typeList.add(record2);
//		for (List<Record> list : typeList) {
//			for (Record obj : list) {
//				String idcard = obj.getStr("cus_id_no").replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
//				String phone = obj.getStr("cus_phone").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//				obj.set("cus_id_no", idcard);
//				obj.set("cus_phone", phone);
//			}
//		}
//		setAttr("list", typeList);
		
		List<Double> moneys = new ArrayList<>();
		Double count1 = Db.queryDouble("SELECT SUM(( SELECT SUM(CONVERT( capital , DECIMAL (10 , 2 ))) as must_capital FROM cus_contract_stage WHERE cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND c.store_id =?", storeId);
		Double count2 = Db.queryDouble("SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( capital , DECIMAL (10 , 2 ))) as alr_capital FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND c.store_id = ?", storeId);
		Double count3 = Db.queryDouble(" SELECT ROUND(SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) FROM cus_contract_stage WHERE cus_contract_id = c.id ))) as must_interest FROM cus_contract c WHERE state NOT IN (-1, 5) and store_id = ?", storeId);
		Double count4 = Db.queryDouble("SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) as alr_interest FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE state NOT IN (-1, 5) AND store_id =?", storeId);
		moneys.add(count1 != null ? count1 : 0.0);
		moneys.add(count2 != null ? count2 : 0.0);
		moneys.add(count3 != null ? count3 : 0.0);
		moneys.add(count4 != null ? count4 : 0.0);
		if(moneys.get(0) != 0) {
			moneys.add(moneys.get(1) / moneys.get(0));
		}else {
			moneys.add(0.00);
		}
		if(moneys.get(2) != 0) {
			moneys.add(moneys.get(3) / moneys.get(2));
		}else {
			moneys.add(0.00);
		}
//		Double[] value = {1000.0, 500.0, 12.0, 3.0};
// 		for (int i = 0; i < value.length; i++) {
// 			moneys.add(value[i]);
//		}
// 		moneys.add(value[1]/value[0]);
// 		moneys.add(value[3]/value[2]);
		
		
		setAttr("moneys", moneys);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		setAttr("date", date);
		
		renderJson();
	
	}

}
