//package com.jyd.controller;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.thrift.TException;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jfinal.core.Controller;
//import com.jfinal.kit.JsonKit;
//import com.jfinal.kit.StrKit;
//import com.jfinal.plugin.activerecord.Db;
//import com.jfinal.plugin.activerecord.Page;
//import com.jfinal.plugin.activerecord.Record;
//import com.jyd.common.model.HbaseMapType;
//import com.jyd.common.model.dto.NormalDetail;
//import com.jyd.common.model.dto.Show_req_para;
//import com.jyd.service.StoreService;
//import com.jyd.service.front.ContractSercice;
//import com.jyd.tool.ApiService;
//
///**
// * 进件、已通过
// * 
// * @author aa
// *
// */
//public class IncomingController extends Controller {
//
//	private StoreService storeS = StoreService.me;
//	private ContractSercice contractS = ContractSercice.me;
//
//	public void index() {
//		render("/contract/incoming.html");
//	}
//
//	/**
//	 * 初始化加载门店下拉框
//	 */
//	public void initStores() {
//		// 查找所有门店
//		setAttr("stores", storeS.findAll());
//		renderJson();
//	}
//
//	/**
//	 * 根据门店查询进件各类型的数值
//	 * 
//	 * @throws TException
//	 */
//	public void findTypes() throws TException {
//		int storeId = getParaToInt("storeId");
//
//		String[] types = { "entry_contract_num", "pass_contract_num", "demand_amount", "loan_amount", "principal",
//				"average_value" };
//		List<Long> typeList = new ArrayList<>();
//
//		Long count1 = Db.queryLong(
//				"SELECT COUNT(1) as entry_contract_num FROM cus_contract_schedule_track WHERE DATE_FORMAT(create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		// TODO要改动 count2
//		Long count2 = Db.queryLong(
//				"SELECT COUNT(1) as pass_contract_num FROM cus_contract WHERE state NOT IN (5, -1) AND DATE_FORMAT(create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		Long count3 = Db.queryLong(
//				"SELECT SUM(exp_loan_amonut) as demand_amount FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') =date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		Long count4 = Db.queryLong(
//				"SELECT SUM(borrowing_amount) as loan_amount FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		Long count5 = Db.queryLong(
//				"SELECT SUM(principal) as principal FROM cus_contract WHERE  state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id=?",
//				storeId);
//		typeList.add(count1 != null ? count1 : 0);
//		typeList.add(count2 != null ? count2 : 0);
//		typeList.add(count3 != null ? count3 : 0);
//		typeList.add(count4 != null ? count4 : 0);
//		typeList.add(count5 != null ? count5 : 0);
//		if (count2 != null && count2 != 0 && count5 != null) {
//			typeList.add(count5 / count2);
//		} else {
//			typeList.add(0L);
//		}
//
//		for (int i = 0; i < types.length; i++) {
//			setAttr(types[i], typeList.get(i));
//		}
//		renderJson();
//	}
//
//	/**
//	 * 时势查询
//	 * 
//	 * @throws ParseException
//	 * @throws TException
//	 */
//	public void findTrend() throws ParseException, TException {
//		String storeId = getPara("storeId");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = storeS.findByStoreId(storeId, dateList);
//		setAttr("dataMap", dataMap);
//
//		// 组织5个类型时间长度的数组
//		String[] types = { "entry_contract_num", "pass_contract_num", "demand_amount", "loan_amount", "principal",
//				"average_value" };
//
//		for (String type : types) {
//			List<String> list = new ArrayList<>();
//			for (String dateKey : dateList) {
//				List<HbaseMapType> typeList = dataMap.get(dateKey);
//				if (!typeList.isEmpty()) {
//					for (HbaseMapType hbaseMapType : typeList) {
//						if (hbaseMapType.getKeyName().equals(type)) {
//							list.add(hbaseMapType.getValue());
//						}
//					}
//				} else {
//					list.add("0");
//				}
//			}
//			setAttr(type, list);
//		}
//
//		renderJson();
//	}
//
//	/**
//	 * 天数间隔集合
//	 * 
//	 * @param startDate
//	 * @param endDate
//	 * @return
//	 * @throws ParseException
//	 */
//	private List<String> daysInterval(String startDate, String endDate) throws ParseException {
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Calendar calendar1 = Calendar.getInstance();
//		Calendar calendar2 = Calendar.getInstance();
//
//		calendar1.setTime(sdf.parse(startDate));
//		calendar2.setTime(sdf.parse(endDate));
//
//		int days = (int) ((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / 24 / 60 / 60 / 1000);
//		List<String> list = new ArrayList<>();
//		for (int i = 0; i <= days; i++) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(sdf.parse(startDate));
//			calendar.add(Calendar.DAY_OF_YEAR, i);
//			list.add(sdf.format(calendar.getTime()));
//		}
//
//		return list;
//	}
//
//	/**
//	 * 合同的详情
//	 */
//	public void findContractDesc() {
//		String storeId = getPara("storeId");
//		String type = getPara("type");
//		int pageNumber = getParaToInt("page");
//		int pageSize = getParaToInt("rows");
//
//		// Page<CusContract> pages = contractS.paginateFindAll(pageNumber, pageSize);
//		// PageBean pageBean = new PageBean(pages.getTotalRow(), pages.getList());
//
//		// 未结清合同数量
//		if (type.equals("1")) {
//			Page<Record> pages = contractS.unclearedNum(pageNumber, pageSize, storeId);
//		}
//		// 1-3期
//		if (type.equals("2")) {
//
//		}
//		// 4-6期
//		if (type.equals("3")) {
//
//		}
//		// 7-12期
//		if (type.equals("4")) {
//
//		}
//		// 12期以上
//		if (type.equals("5")) {
//
//		}
//
//		renderJson();
//	}
//
//	public void weekAndMonth() {
//		Integer type = getParaToInt("type");
//		Integer value = getParaToInt("value");
//		String selected = getPara("selected");
//		String beginDate = null;
//		String endDate = null;
//
//		if (selected != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			Date date = new Date();
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			if (selected.equals("week")) {
//				calendar.add(Calendar.DATE, -1);
//				endDate = sdf.format(calendar.getTime());
//				calendar.add(Calendar.DATE, -6);
//				beginDate = sdf.format(calendar.getTime());
//			}
//			if (selected.equals("month")) {
//				calendar.add(Calendar.DATE, -1);
//				endDate = sdf.format(calendar.getTime());
//				calendar.add(Calendar.MONTH, -1);
//				beginDate = sdf.format(calendar.getTime());
//			}
//
//		}
//
//		String[] keys = { "entry_contract_num", "pass_contract_num", "demand_amount", "loan_amount", "principal",
//				"average_value" };
//
//		/********** 构造json类 ***********/
//		NormalDetail detail = new NormalDetail();
//		Show_req_para reqPara = new Show_req_para();
//		reqPara.setBegin_date(beginDate);
//		reqPara.setEnd_date(endDate);
//		detail.setShow_req_para(reqPara);
//		detail.setShow_res_error("参数有误!");
//		detail.setShow_res_code("-1");
//		detail.setShow_res_id(StrKit.getRandomUUID());
//
//		/******** 失败返回json *********/
//		if (beginDate == null || endDate == null || type == 0 || value == 0) {
//			renderJson(JsonKit.toJson(detail));
//		} else {
//			List<String> list = new ArrayList<>();
//			for (String key : keys) {
//				Map para = new HashMap();
//				para.put("begin", beginDate);
//				para.put("end", endDate);
//				para.put("type", type);
//				para.put("value", value);
//				para.put("key", key);
//				list.add(ApiService.me.single(para));
//			}
//			NormalDetail pass_contract_num = JSONObject.parseObject(list.get(1), NormalDetail.class);
//			NormalDetail loan_amount = JSONObject.parseObject(list.get(3), NormalDetail.class);
//			NormalDetail average_value = JSONObject.parseObject(list.get(5), NormalDetail.class);
//
//			if (pass_contract_num.getShow_res_code().equals("0") && loan_amount.getShow_res_code().equals("0")
//					&& average_value.getShow_res_code().equals("0")) {
//				if (pass_contract_num.getShow_res_body().getData().length == loan_amount.getShow_res_body()
//						.getData().length) {
//					BigDecimal[] data = pass_contract_num.getShow_res_body().getData();
//					BigDecimal[] data1 = loan_amount.getShow_res_body().getData();
//					BigDecimal[] data2 = average_value.getShow_res_body().getData();
//					for (int i = 0; i < data.length; i++) {
//						if (data[i].doubleValue() != 0.0) {
//							data2[i] = data1[i].divide(data[i], 2, BigDecimal.ROUND_HALF_UP);
//						}
//					}
//
//					list.set(5, JsonKit.toJson(average_value));
//				}
//			}
//
//			setAttr("list", list);
//			renderJson();
//		}
//		/******** 成功返回json *********/
//	}
//
//	public void day() {
//
//		int storeId = getParaToInt("value");
//		List<List<Record>> typeList = new ArrayList<>();
//
//		List<Record> count1 = Db.find(
//				"SELECT b.short_name, a.cus_name, a.cus_id_no, a.cus_phone, a.create_user FROM cus_contract_schedule_track a, ba_store b WHERE a.store_id=b.id and DATE_FORMAT(a.create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and a.store_id =?",
//				storeId);
//		List<Record> count2 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (5, -1) AND DATE_FORMAT(a.create_date, '%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and a.store_id =?",
//				storeId);
//		typeList.add(count1);
//		typeList.add(count2);
//		for (List<Record> list : typeList) {
//			for (Record obj : list) {
//				String idcard = obj.getStr("cus_id_no").replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
//				String phone = obj.getStr("cus_phone").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//				obj.set("cus_id_no", idcard);
//				obj.set("cus_phone", phone);
//			}
//		}
//		setAttr("list", typeList);
//
//		List<Double> moneys = new ArrayList<>();
//
//		Double count3 = Db.queryDouble(
//				"SELECT SUM(exp_loan_amonut) as demand_amount FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') =date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		Double count4 = Db.queryDouble(
//				"SELECT SUM(borrowing_amount) as loan_amount FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id =?",
//				storeId);
//		Double count5 = Db.queryDouble(
//				"SELECT SUM(principal) as principal FROM cus_contract WHERE  state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') and store_id=?",
//				storeId);
//		moneys.add(count3 != null ? count3 : 0.0);
//		moneys.add(count4 != null ? count4 : 0.0);
//		moneys.add(count5 != null ? count5 : 0.0);
//		if (count2 != null && count2.size() > 0) {
//			moneys.add(count4 / count2.size());
//		} else {
//			moneys.add(0.00);
//		}
//		setAttr("moneys", moneys);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String date = sdf.format(new Date());
//		setAttr("date", date);
//
//		renderJson();
//
//	}
//
//}
