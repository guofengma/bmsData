//package com.jyd.controller;
//
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
// * 未结清合同
// * 
// * @author aa
// *
// */
//public class NoSettleContractController extends Controller {
//	private StoreService storeS = StoreService.me;
//	private ContractSercice contractS = ContractSercice.me;
//
//	public void index() {
//		render("/contract/noSettleContract3.html");
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
//	 * 根据门店查询当天未结清合同各类型的数值, 返回一个数组 MySQL
//	 * 
//	 * @throws TException
//	 */
//	public void findTypes() throws TException {
//		int storeId = getParaToInt("storeId");
//		String[] types = { "uncleared_num", "uncleared_num_1_3", "uncleared_num_4_6", "uncleared_num_7_12",
//				"uncleared_num_12" };
//		List<Long> typeList = new ArrayList<>();
//		
//		Long count1 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND store_id = ? ", storeId);
//		Long count2 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND repayment_stage_id IN (1, 2, 3) AND store_id = ? ", storeId);
//		Long count3 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND repayment_stage_id IN (12, 10, 4) AND store_id = ? ", storeId);
//		Long count4 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND repayment_stage_id IN (13, 14, 8, 5) AND store_id = ? ", storeId);
//		Long count5 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND repayment_stage_id IN (6, 7, 9, 11, 15, 16, 17, 18) AND store_id = ? ", storeId);
//		typeList.add(count1 != null ? count1 : 0);
//		typeList.add(count2 != null ? count2 : 0);
//		typeList.add(count3 != null ? count3 : 0);
//		typeList.add(count4 != null ? count4 : 0);
//		typeList.add(count5 != null ? count5 : 0);
//
//		for (int i = 0; i < types.length; i++) {
//			setAttr(types[i], typeList.get(i));
//		}
//		renderJson();
//	}
//
//	public void findTypes2() throws TException {
//		String storeId = getPara("storeId");
//
//		List<String> dateList = new ArrayList<String>();
//		dateList.add("20180929");
//
//		List<HbaseMapType> mapTypeList = storeS.findSumDataByStoreId(String.valueOf(storeId), dateList);
//
//		for (HbaseMapType map : mapTypeList) {
//			setAttr(map.getKeyName(), map.getValue());
//		}
//
//		renderJson();
//	}
//
//	/**
//	 * 时势查询 hbase
//	 * 
//	 * @throws ParseException
//	 * @throws TException
//	 */
//	public void findUnclearedNumTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		// 类型数组 "uncleared_num", "uncleared_num_1_3", "uncleared_num_4_6",
//		// "uncleared_num_7_12"
//		String[] types = { "uncleared_num" };
//
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = null;
//		if (typeId == 1) {
//			dataMap = storeS.findByStoreIdAndTypes(storeId, dateList, types);
//		}
//
//		if (dataMap != null) {
//			// 组织5个类型时间长度的数组
//			for (String type : types) {
//				List<String> list = new ArrayList<>();
//				for (String dateKey : dateList) {
//					List<HbaseMapType> typeList = dataMap.get(dateKey);
//					if (!typeList.isEmpty()) {
//						for (HbaseMapType hbaseMapType : typeList) {
//							if (hbaseMapType.getKeyName().equals(type)) {
//								list.add(hbaseMapType.getValue());
//							}
//						}
//					} else {
//						list.add("0");
//					}
//				}
//				setAttr("data", list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		renderJson();
//	}
//
//	public void findUnclearedNum_1_3Trend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		// 类型数组
//		String[] types = { "uncleared_num_1_3" };
//
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = null;
//		if (typeId == 1) {
//			dataMap = storeS.findByStoreIdAndTypes(storeId, dateList, types);
//		}
//
//		if (dataMap != null) {
//			// 组织5个类型时间长度的数组
//			for (String type : types) {
//				List<String> list = new ArrayList<>();
//				for (String dateKey : dateList) {
//					List<HbaseMapType> typeList = dataMap.get(dateKey);
//					if (!typeList.isEmpty()) {
//						for (HbaseMapType hbaseMapType : typeList) {
//							if (hbaseMapType.getKeyName().equals(type)) {
//								list.add(hbaseMapType.getValue());
//							}
//						}
//					} else {
//						list.add("0");
//					}
//				}
//				setAttr("data", list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		renderJson();
//	}
//
//	public void findUnclearedNum_4_6Trend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		// 类型数组
//		String[] types = { "uncleared_num_4_6" };
//
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = null;
//		if (typeId == 1) {
//			dataMap = storeS.findByStoreIdAndTypes(storeId, dateList, types);
//		}
//
//		if (dataMap != null) {
//			// 组织5个类型时间长度的数组
//			for (String type : types) {
//				List<String> list = new ArrayList<>();
//				for (String dateKey : dateList) {
//					List<HbaseMapType> typeList = dataMap.get(dateKey);
//					if (!typeList.isEmpty()) {
//						for (HbaseMapType hbaseMapType : typeList) {
//							if (hbaseMapType.getKeyName().equals(type)) {
//								list.add(hbaseMapType.getValue());
//							}
//						}
//					} else {
//						list.add("0");
//					}
//				}
//				setAttr("data", list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		renderJson();
//	}
//
//	public void findUnclearedNum_7_12Trend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		// 类型数组
//		String[] types = { "uncleared_num_7_12" };
//
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = null;
//		if (typeId == 1) {
//			dataMap = storeS.findByStoreIdAndTypes(storeId, dateList, types);
//		}
//
//		if (dataMap != null) {
//			// 组织5个类型时间长度的数组
//			for (String type : types) {
//				List<String> list = new ArrayList<>();
//				for (String dateKey : dateList) {
//					List<HbaseMapType> typeList = dataMap.get(dateKey);
//					if (!typeList.isEmpty()) {
//						for (HbaseMapType hbaseMapType : typeList) {
//							if (hbaseMapType.getKeyName().equals(type)) {
//								list.add(hbaseMapType.getValue());
//							}
//						}
//					} else {
//						list.add("0");
//					}
//				}
//				setAttr("data", list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		renderJson();
//	}
//
//	public void findUnclearedNum_12Trend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		// 类型数组
//		String[] types = { "uncleared_num_12" };
//
//		// 返回时间key 的数据集合
//		Map<String, List<HbaseMapType>> dataMap = null;
//		if (typeId == 1) {
//			dataMap = storeS.findByStoreIdAndTypes(storeId, dateList, types);
//		}
//
//		if (dataMap != null) {
//			// 组织5个类型时间长度的数组
//			for (String type : types) {
//				List<String> list = new ArrayList<>();
//				for (String dateKey : dateList) {
//					List<HbaseMapType> typeList = dataMap.get(dateKey);
//					if (!typeList.isEmpty()) {
//						for (HbaseMapType hbaseMapType : typeList) {
//							if (hbaseMapType.getKeyName().equals(type)) {
//								list.add(hbaseMapType.getValue());
//							}
//						}
//					} else {
//						list.add("0");
//					}
//				}
//				setAttr("data", list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		renderJson();
//	}
//
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
//	 * 查看未结清合同的详情
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
//	/**
//	 * 本周数据
//	 */
//	public void week1() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date date = new Date();
//		String endDate = sdf.format(date);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, -7);
//		String beginDate = sdf.format(calendar.getTime());
//		
//		
//		Integer type = getParaToInt("type");
//		Integer value = getParaToInt("value");
//		String key = getPara("key");
//		
//		
//		redirect("/api/single?begin="+beginDate+"&end="+endDate+"&type="+type+"&value="+value+"&key="+key);
//	}
//	
//	
//	public void weekAndMonth() {
//		Integer type = getParaToInt("type");
//		Integer value = getParaToInt("value");
//		String selected = getPara("selected");
//		String beginDate = null;
//		String endDate = null;
//		
//		if(selected != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			Date date = new Date();
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			if(selected.equals("week")) {
//				calendar.add(Calendar.DATE, -1);
//				endDate = sdf.format(calendar.getTime());
//				calendar.add(Calendar.DATE, -6);
//				beginDate = sdf.format(calendar.getTime());
//			}
//			if(selected.equals("month")) {
//				calendar.add(Calendar.DATE, -1);
//				endDate = sdf.format(calendar.getTime());
//				calendar.add(Calendar.MONTH, -1);
//				beginDate = sdf.format(calendar.getTime());
//			}
//			
//		}
//		
//		String[] keys = { "uncleared_num", "uncleared_num_1_3", "uncleared_num_4_6", "uncleared_num_7_12",
//		"uncleared_num_12" };
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
//			setAttr("list", list);
//			renderJson();
//		}
//		/******** 成功返回json *********/
//	}
//	
//	public void day() {
//		int storeId = getParaToInt("value");
//		List<List<Record>> typeList = new ArrayList<>();
//		
//		List<Record> count1 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.store_id = ? ", storeId);
//		List<Record> count2 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.repayment_stage_id IN (1, 2, 3) AND a.store_id = ? ", storeId);
//		List<Record> count3 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.repayment_stage_id IN (12, 10, 4) AND a.store_id = ? ", storeId);
//		List<Record> count4 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.repayment_stage_id IN (13, 14, 8, 5) AND a.store_id = ? ", storeId);
//		List<Record> count5 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.repayment_stage_id IN (6, 7, 9, 11, 15, 16, 17, 18) AND a.store_id = ? ", storeId);
//		typeList.add(count1);
//		typeList.add(count2);
//		typeList.add(count3);
//		typeList.add(count4);
//		typeList.add(count5);
//		
//		for (List<Record> list : typeList) {
//			for (Record obj : list) {
//				String idcard = obj.getStr("cus_id_no").replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
//				String phone = obj.getStr("cus_phone").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//				obj.set("cus_id_no", idcard);
//				obj.set("cus_phone", phone);
//			}
//		}
//		
//		setAttr("list", typeList);
////		for (int i = 0; i < types.length; i++) {
////			setAttr(types[i], typeList.get(i));
////		}
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String date = sdf.format(new Date());
//		setAttr("date", date);
//		
//		renderJson();
//	
//	}
//	
//	
//
//}
