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

public class FundPayController extends Controller {

	private StoreService storeS = StoreService.me;
	private ContractSercice contractS = ContractSercice.me;

	public void index() {
		render("/contract/fundPay.html");
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
	 * 根据门店查询入职各类型的数值
	 * 
	 * @throws TException
	 */

	public void findTypes() throws TException {
		int storeId = getParaToInt("storeId");
		String[] types = { "entry_num", "position_num" };
		List<Long> typeList = new ArrayList<>();

		//
		Long count1 = Db.queryLong(
				"SELECT count(1) as entry_num FROM hr_employee e,hr_department d where e.department_id= d.id and e.state= 0 and date_format(e.create_date,'%Y%m%d')= date_format(SYSDATE(),'%Y%m%d') and d.store_id = ?",
				storeId);
		Long count2 = Db.queryLong("SELECT MAX(total_actual_nums) FROM hr_department where  store_id=?", storeId);
		typeList.add(count1 != null ? count1 : 0);
		typeList.add(count2 != null ? count2 : 0);
		for (int i = 0; i < types.length; i++) {
			setAttr(types[i], typeList.get(i));
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

		String[] keys = { "fundPay_total_amount", "principal", "interest" };

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
		String[] types = { "fundPay_total_amount", "principal", "interest" };
		List<List<Record>> typeList = new ArrayList<>();
		List<Record> count1 = Db.find(
				"SELECT c.short_name,b.department_name,a.name,d.position_type,a.contact_information,a.entry_date FROM hr_employee a,hr_department b, ba_store c,ba_position_type d WHERE a.department_id= b.id and b.store_id=c.id and a.position_id=d.id and a.state= 0 AND date_format(a.create_date,'%Y%m%d')= date_format(SYSDATE(),'%Y%m%d') AND b.store_id = ? order by d.position_type ",
				storeId);
		List<Record> count2 = Db.find(
				"SELECT c.short_name,b.department_name,a.name,d.position_type,a.contact_information,a.entry_date FROM hr_employee a,hr_department b, ba_store c,ba_position_type d WHERE a.department_id= b.id and b.store_id=c.id and a.position_id=d.id and a.state= 0 AND b.store_id = ? order by d.position_type  ",
				storeId);

		// typeList.add(count1);
		// typeList.add(count2);
		for (List<Record> list : typeList) {
			for (Record obj : list) {
				String phone = obj.getStr("contact_information").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
				String date = obj.getStr("entry_date");
				date = date.split(" ")[0]; // yyyy-MM-dd
				obj.set("contact_information", phone);
				obj.set("entry_date", date);
			}
		}

		setAttr("list", typeList);

		// List<Record> count3 = Db.find("select b.position_type ,count(a.id_no) as num
		// from hr_employee a,ba_position_type b,hr_department c where
		// a.position_id=b.id and a.department_id=c.id and a.state=0 and c.store_id=?
		// group by b.position_type ", storeId);
		List<Record> count3 = new ArrayList<>();
		setAttr("list2", count3);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		setAttr("date", date);

		renderJson();

	}

}