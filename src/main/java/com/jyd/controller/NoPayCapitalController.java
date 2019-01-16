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
// * 未还本金利息
// * 
// * @author aa
// *
// */
//public class NoPayCapitalController extends Controller {
//
//	private StoreService storeS = StoreService.me;
//	private ContractSercice contractS = ContractSercice.me;
//
//	public void index() {
//		render("/contract/noPayCapital.html");
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
//	 * 根据门店查询未还本金利息各类型的数值
//	 * 
//	 * @throws TException
//	 */
//	public void findTypes() throws TException {
//		int storeId = getParaToInt("storeId");
//		String[] types = { "uncleared_num", "principal_5w", "principal_5_10w", "principal_10_20w", "principal_20_30w",
//				"principal_30w" };
//		String[] types2 = { "uncleared_contract_principal", "uncleared_interest" };
//		List<Long> typeList = new ArrayList<>();
//		List<Double> typeList2 = new ArrayList<>();
//
//		// 数量
//		Long count1 = Db.queryLong("SELECT count(1) FROM cus_contract WHERE state IN (0,3) AND store_id = ? ", storeId);
//		Long count2 = Db.queryLong(
//				"SELECT COUNT(1) FROM cus_contract WHERE state IN (0,3) AND principal <= 50000 AND store_id = ?",
//				storeId);
//		Long count3 = Db.queryLong(
//				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (-1, 1, 5)  AND principal > 50000 AND principal <= 100000 AND store_id = ?",
//				storeId);
//		Long count4 = Db.queryLong(
//				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (-1, 1, 5)  AND principal > 100000 AND principal <= 200000 AND store_id = ?",
//				storeId);
//		Long count5 = Db.queryLong(
//				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (-1, 1, 5)  AND principal > 200000 AND principal <= 300000 AND store_id = ?",
//				storeId);
//		Long count6 = Db.queryLong(
//				"SELECT COUNT(1) FROM cus_contract WHERE state NOT IN (-1, 1, 5)  AND principal > 300000 AND store_id = ?",
//				storeId);
//		typeList.add(count1 != null ? count1 : 0);
//		typeList.add(count2 != null ? count2 : 0);
//		typeList.add(count3 != null ? count3 : 0);
//		typeList.add(count4 != null ? count4 : 0);
//		typeList.add(count5 != null ? count5 : 0);
//		typeList.add(count6 != null ? count6 : 0);
//		for (int i = 0; i < types.length; i++) {
//			setAttr(types[i], typeList.get(i));
//		}
//
//		// 未还本金
//		Double count7 = Db.queryDouble(
//				"SELECT SUM(principal) FROM cus_contract WHERE state NOT IN (-1, 1, 5) AND store_id = ?", storeId);
//		typeList2.add(count7 != null ? count7 : 0);
//		// 应还利息
//		Double must_interest = Db.queryDouble(
//				"SELECT ROUND(SUM((SELECT SUM(CONVERT(interest, DECIMAL(10, 2))) FROM cus_contract_stage WHERE cus_contract_id = c.id))) FROM cus_contract c WHERE c.state NOT IN ( - 1, 5 ) AND store_id = ?",
//				storeId);
//		// 已还利息
//		Double alr_interest = Db.queryDouble(
//				"SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE c.state NOT IN (-1, 5) AND c.store_id =?",
//				storeId);
//		// 未还利息
//		Double uncleared_interest = (must_interest != null ? must_interest : 0.0)
//				- (alr_interest != null ? alr_interest : 0.0);
//		typeList2.add(uncleared_interest);
//		for (int i = 0; i < types2.length; i++) {
//			setAttr(types2[i], typeList2.get(i));
//		}
//
//		renderJson();
//	}
//
//	/**
//	 * 时势查询
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
//	public void findPrincipal_5wTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "principal_5w" };
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
//	public void findPrincipal_5_10wTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "principal_5_10w" };
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
//	public void findPrincipal_10_20wTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "principal_10_20w" };
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
//	public void findPrincipal_20_30wTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "principal_20_30w" };
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
//	public void findPrincipal_30wTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "principal_30w" };
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
//	public void findUncleared_contract_principalTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "uncleared_contract_principal" };
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
//		// for (int i = 0; i < types.length; i++) {
//		// removeAttr(types[i]);
//		// }
//
//		renderJson();
//	}
//
//	public void findUncleared_interestTrend() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "must_interest", "alr_interest" };
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
//				setAttr(type, list);
//			}
//			setAttr("dataMap", dataMap);
//		}
//
//		List<String> must_interestList = getAttr(types[0]);
//		List<String> alr_interestList = getAttr(types[1]);
//		if (must_interestList.size() == alr_interestList.size()) {
//			List<Double> list = new ArrayList<>();
//			for (int i = 0; i < must_interestList.size(); i++) {
//				list.add(Double.parseDouble(must_interestList.get(i)) - Double.parseDouble(alr_interestList.get(i)));
//			}
//			setAttr("data", list);
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
//		String[][] keys = { { "uncleared_num", "principal_5w", "principal_5_10w", "principal_10_20w",
//				"principal_20_30w", "principal_30w" },
//				{ "must_capital", "alr_capital", "must_interest", "alr_interest" } };// ,"surplus_capital",
//																						// "surplus_interest"
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
//			for (String key : keys[0]) {
//				Map para = new HashMap();
//				para.put("begin", beginDate);
//				para.put("end", endDate);
//				para.put("type", type);
//				para.put("value", value);
//				para.put("key", key);
//				list.add(ApiService.me.single(para));
//			}
//			List<String> list1 = new ArrayList<>();
//			for (String key : keys[1]) {
//				Map para = new HashMap();
//				para.put("begin", beginDate);
//				para.put("end", endDate);
//				para.put("type", type);
//				para.put("value", value);
//				para.put("key", key);
//				list1.add(ApiService.me.single(para));
//			}
//			NormalDetail must_capital = JSONObject.parseObject(list1.get(0), NormalDetail.class);
//			NormalDetail alr_capital = JSONObject.parseObject(list1.get(1), NormalDetail.class);
//			NormalDetail must_interest = JSONObject.parseObject(list1.get(2), NormalDetail.class);
//			NormalDetail alr_interest = JSONObject.parseObject(list1.get(3), NormalDetail.class);
//
//			if (must_capital.getShow_res_body().getData().length == alr_capital.getShow_res_body().getData().length) {
//				BigDecimal[] data = must_capital.getShow_res_body().getData();
//				BigDecimal[] data1 = alr_capital.getShow_res_body().getData();
//				BigDecimal[] unclear_capital = new BigDecimal[data.length];
//				for (int i = 0; i < data.length; i++) {
//					unclear_capital[i] = data[i].subtract(data1[i]);
//				}
//				must_capital.getShow_res_body().setData(unclear_capital);
//				must_capital.getShow_res_body().setType("未还本金");
//				must_capital.getShow_req_para().setKey("unclear_capital");
//				list.add(JsonKit.toJson(must_capital));
//			}
//			if (must_interest.getShow_res_body().getData().length == alr_interest.getShow_res_body().getData().length) {
//				BigDecimal[] data = must_interest.getShow_res_body().getData();
//				BigDecimal[] data1 = alr_interest.getShow_res_body().getData();
//				BigDecimal[] unclear_interest = new BigDecimal[data.length];
//				for (int i = 0; i < data.length; i++) {
//					unclear_interest[i] = data[i].subtract(data1[i]);
//				}
//				must_interest.getShow_res_body().setData(unclear_interest);
//				must_interest.getShow_res_body().setType("未还利息");
//				must_interest.getShow_req_para().setKey("unclear_interest");
//				list.add(JsonKit.toJson(must_interest));
//			}
//
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
//		List<Record> count1 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.store_id = ? ",
//				storeId);
//		List<Record> count2 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3) AND a.principal <= 50000 AND a.store_id = ?",
//				storeId);
//		List<Record> count3 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (-1, 1, 5)  AND a.principal > 50000 AND a.principal <= 100000 AND a.store_id = ?",
//				storeId);
//		List<Record> count4 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (-1, 1, 5)  AND a.principal > 100000 AND a.principal <= 200000 AND a.store_id = ?",
//				storeId);
//		List<Record> count5 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (-1, 1, 5)  AND a.principal > 200000 AND a.principal <= 300000 AND a.store_id = ?",
//				storeId);
//		List<Record> count6 = Db.find(
//				"SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state NOT IN (-1, 1, 5)  AND a.principal > 300000 AND a.store_id = ?",
//				storeId);
//		typeList.add(count1);
//		typeList.add(count2);
//		typeList.add(count3);
//		typeList.add(count4);
//		typeList.add(count5);
//		typeList.add(count6);
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
//
//		List<Double> moneys = new ArrayList<>();
//		// 未还本金
//		Double count7 = Db.queryDouble(
//				"SELECT SUM(principal) FROM cus_contract WHERE state NOT IN (-1, 1, 5) AND store_id = ?", storeId);
//		moneys.add(count7 != null ? count7 : 0.0);
//		// 应还利息
//		Double must_interest = Db.queryDouble(
//				"SELECT ROUND(SUM((SELECT SUM(CONVERT(interest, DECIMAL(10, 2))) FROM cus_contract_stage WHERE cus_contract_id = c.id))) FROM cus_contract c WHERE c.state NOT IN ( - 1, 5 ) AND store_id = ?",
//				storeId);
//		// 已还利息
//		Double alr_interest = Db.queryDouble(
//				"SELECT SUM(( SELECT SUM(( SELECT SUM(CONVERT( interest , DECIMAL (10 , 2 ))) FROM cus_contract_repayment WHERE cus_contract_stage_id = s.id )) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id )) FROM cus_contract c WHERE c.state NOT IN (-1, 5) AND c.store_id =?",
//				storeId);
//		// 未还利息
//		Double uncleared_interest = ((must_interest != null ? must_interest : 0.0)
//				- (alr_interest != null ? alr_interest : 0.0));
//		moneys.add(uncleared_interest);
//		setAttr("moneys", moneys);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String date = sdf.format(new Date());
//		setAttr("date", date);
//
//		renderJson();
//	}
//
//}
