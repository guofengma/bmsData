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
//import com.jyd.common.model.BaStore;
//import com.jyd.common.model.HbaseMapType;
//import com.jyd.common.model.dto.NormalDetail;
//import com.jyd.common.model.dto.Show_req_para;
//import com.jyd.service.StoreService;
//import com.jyd.service.front.ContractSercice;
//import com.jyd.tool.ApiService;
//
//public class SettleContractController extends Controller {
//
//	private StoreService storeS = StoreService.me;
//	private ContractSercice contractS = ContractSercice.me;
//	
//	public void index() {
//		render("/contract/settleContract.html");
//	}
//	
//	/**
//	 * 初始化加载门店
//	 */
//	public void initStores() {
//		//查找所有门店
//		List<BaStore> stores=storeS.findAll();
//
//		 //筛选门店
//		for(int i=0;i<stores.size();i++){
//		    if(stores.get(i).getShortName().equals("联融公司"))
//		    	stores.remove(i);
//		    if(stores.get(i).getShortName().equals("河南分中心"))
//		    	stores.remove(i);
//		    if(stores.get(i).getShortName().equals("广州分中心"))
//		    	stores.remove(i);
//		    if(stores.get(i).getShortName().equals("p2p"))
//		    	stores.remove(i);
//		    if(stores.get(i).getShortName().equals("总部"))
//		    	stores.remove(i);
//	    	if(stores.get(i).getShortName().equals("东莞分中心"))
//		    	stores.remove(i);
//		}
//
//
//		setAttr("stores", stores);
//		renderJson();
//	}
//	
//	/**
//	 * 
//	 * 
//	 * @throws TException
//	 */
//	public void findTypes() throws TException {
//		int storeId = getParaToInt("storeId");
//		String[] types = { "must_settle_contract_num", "alr_settle_contract_num" ,"uncleared_num"};
//		String[] types2 = {"must_settle_contract_principal","alr_settle_contract_principal","uncleared_contract_principal" };
//		List<Long> typeList = new ArrayList<>();
//		List<Double> typeList2 = new ArrayList<>();
//		// 数量
//		Long count1 = Db.queryLong("SELECT COUNT(1) FROM cus_contract WHERE  end_date < SYSDATE() AND state NOT IN (-1, 5) AND store_id = ? ",storeId);
//		Long count2 = Db.queryLong("SELECT COUNT(1) FROM cus_contract WHERE state = 1 AND store_id = ? ", storeId);
//		Long count3 = Db.queryLong("SELECT COUNT(1) as uncleared_num FROM cus_contract WHERE state IN (0,3) AND store_id = ? ", storeId);
//		typeList.add(count1 != null ? count1 : 0);
//		typeList.add(count2 != null ? count2 : 0);
//		typeList.add(count3 != null ? count3 : 0);
//		for (int i = 0; i < types.length; i++) {
//			setAttr(types[i], typeList.get(i));
//		}
//
//		// 应结清
//		Double  count4 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE end_date < SYSDATE() AND state NOT IN (-1, 5) AND store_id = ? ", storeId);
//		// 已结清
//		Double count5 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE state = 1 AND store_id = ? ", storeId);
//		// 未结清
////		Double  count5 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE state NOT IN (-1, 1, 5) AND store_id = ? ", storeId);
//		Double count6 = (count4 != null ? count4 : 0.0) - (count5 != null ? count5 : 0.0);
//		
//		typeList2.add(count4 != null ? count4 : 0.0);
//		typeList2.add(count5 != null ? count5 : 0.0);
//		typeList2.add(count6 != null ? count6 : 0.0);
//		for (int i = 0; i < types2.length; i++) {
//			setAttr(types2[i], typeList2.get(i));
//		}
//
//		renderJson();
//	}
//
//
//	/**
//	 * 时势查询 hbase
//	 * 
//	 * @throws ParseException
//	 * @throws TException
//	 */
//	public void findMustSettleContractNum() throws ParseException, TException {
//		int typeId = getParaToInt("type");
//		String storeId = getPara("value");
//		String startDate = getPara("startDate").replace("-", "");
//		String endDate = getPara("endDate").replace("-", "");
//
//		// 组织 时间 key
//		List<String> dateList = daysInterval(startDate, endDate);
//		setAttr("dateList", dateList);
//
//		String[] types = { "must_settle_contract_num" };
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
//	public void findAlrSettleContractNum() throws ParseException, TException {
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
//		String[] types = { "alr_settle_contract_num" };
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
//	public void findMustSettleContractPrincipal() throws ParseException, TException {
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
//		String[] types = { "must_settle_contract_principal" };
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
//	public void findAlrSettleContractPrincipal() throws ParseException, TException {
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
//		String[] types = { "alr_settle_contract_principal" };
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
//	public void findUnclearedContractPrincipal() throws ParseException, TException {
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
//		renderJson();
//	}
//
//	
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
//		int days = (int) ((calendar2.getTime().getTime() - calendar1.getTime().getTime())/24/60/60/1000);
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
//	/**int storeId = getParaToInt("storeId");
//	 * 查看未结清合同的详情
//	 */
//	public void findContractDesc() {
//		String storeId = getPara("storeId");
//		String type = getPara("type");
//		int pageNumber = getParaToInt("page");
//		int pageSize = getParaToInt("rows");
//		
////		Page<CusContract> pages = contractS.paginateFindAll(pageNumber, pageSize);
////		PageBean pageBean = new PageBean(pages.getTotalRow(), pages.getList());
//		
//		
//		//未结清合同数量
//		if(type.equals("1")) {
//			Page<Record> pages =  contractS.unclearedNum(pageNumber, pageSize, storeId);
//		}
//		//1-3期
//		if(type.equals("2")) {
//			
//		}
//		//4-6期
//		if(type.equals("3")) {
//			
//		}
//		//7-12期
//		if(type.equals("4")) {
//			
//		}
//		//12期以上
//		if(type.equals("5")) {
//			
//		}
//		
//		renderJson();
//	}
//	
//	
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
//		String[] keys = { "must_settle_contract_num", "alr_settle_contract_num" ,"uncleared_num",
//				"must_settle_contract_principal","alr_settle_contract_principal","uncleared_contract_principal"};
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
//		
//		List<List<Record>> typeList = new ArrayList<>();
//																																											
//		List<Record> count1 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.end_date < SYSDATE() and a.state NOT IN (-1,5) AND a.store_id = ? ", storeId);
//		List<Record> count2 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state=1  AND a.store_id = ? ", storeId);
//		List<Record> count3 = Db.find("SELECT b.short_name, a.cus_contract_no, a.cus_name, a.cus_id_no, a.cus_phone, a.borrowing_amount FROM cus_contract a, ba_store b WHERE a.store_id = b.id and a.state IN (0,3)  AND a.store_id = ? ", storeId);
//
//		typeList.add(count1);
//		typeList.add(count2);
//		typeList.add(count3);
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
//		
//		
//		List<Double> moneys = new ArrayList<>();
//		// 应结清
//		Double  count4 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE end_date < SYSDATE() AND state NOT IN (-1, 5) AND store_id = ? ", storeId);
//		// 已结清
//		Double count5 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE state = 1 AND store_id = ? ", storeId);
//		// 未结清
////		Double  count5 = Db.queryDouble("SELECT SUM(principal) FROM cus_contract WHERE state NOT IN (-1, 1, 5) AND store_id = ? ", storeId);
//		Double count6 = (count4 != null ? count4 : 0.0) - (count5 != null ? count5 : 0.0);
//		moneys.add(count4 != null ? count4 : 0.0);
//		moneys.add(count5 != null ? count5 : 0.0);
//		moneys.add(count6);
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
//	
//	
//}
//>>>>>>> branch 'master' of code@code-server:bmsData.git
