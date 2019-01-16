package com.jyd.tool;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jyd.common.Const;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.CusContract;
import com.jyd.common.model.HbaseMapType;
import com.jyd.common.model.HrDepartment;
import com.jyd.common.model.HrEmployee;
import com.jyd.common.model.dto.Data;
import com.jyd.common.model.dto.NormalAll;
import com.jyd.common.model.dto.NormalDetail;
import com.jyd.common.model.dto.Show_req_para;
import com.jyd.common.model.dto.Show_res_body;
import com.jyd.common.model.dto.Show_res_body_detail;
import com.jyd.hbase.HbaseDAOImpl;
import com.jyd.service.ContractService;
import com.jyd.service.DepartmentService;
import com.jyd.service.EmployeeService;
import com.jyd.service.HbaseMapTypeService;
import com.jyd.service.StoreService;

/**
 * 接口服务
 * 
 * @author mjy
 *
 */
public class ApiService {
	public static final ApiService me = new ApiService();

	private HbaseDAOImpl client = new HbaseDAOImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 单个值
	 * 
	 * @param para
	 * @return
	 */
	public String single(Map para) {
		/*********** 变量区 ***********/
		String errorCode = "0";
		String errorMsg = "";
		NormalDetail detail = new NormalDetail();
		Date beginDate = null, endDate = null;
		List<String> columns = new ArrayList<String>();
		List<Date> dList = new ArrayList<>();

		/*********** 参数区 ***********/
		String column = (String) para.get("key");
		String beginStr = (String) para.get("begin");
		String endStr = (String) para.get("end");
		int type = (int) para.get("type");
		int value = (int) para.get("value");

		/*********** 时间格式异常处理 ***********/
		try {
			beginDate = sdf.parse(beginStr);
			endDate = sdf.parse(endStr);
		} catch (ParseException e1) {
			errorCode = "-1";
			errorMsg = "日期格式转换异常!";
		}

		/********** 构造json ***********/
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginStr);
		reqPara.setEnd_date(endStr);
		reqPara.setKey(column);
		reqPara.setType(String.valueOf(type));
		reqPara.setValue(String.valueOf(value));
		detail.setShow_req_para(reqPara);

		/*********** 行键值 ***********/

		/*********** 时间_字段 ***********/
		List<String> dateStrList = new ArrayList<>();
		dList = DateTool.getBetweenDates(beginDate, endDate);
		Show_res_body resBody = new Show_res_body();

		/********** 时间拼凑cloumn字段 *******/
		BigDecimal[] values = new BigDecimal[dList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = new BigDecimal("0");
		}
		for (Date d : dList) {
			dateStrList.add(sdf.format(d));
			columns.add(sdf.format(d) + "_" + column);
		}
		resBody.setMonth(dateStrList);
		try {
			/*********** 打开hbase 第三方接口 ***********/
			Map<String, String> map = new HashMap<String, String>();
			map = client.getRowsWithColumns(Const.TABLES[type], String.valueOf(value), columns);

			/*********** 循环出累加值 ***********/
			BigDecimal b_value = new BigDecimal("0");
			for (int i = 0; i < values.length; i++) {
				String b_value_str = map.get(sdf.format(dList.get(i)) + "_" + column);
				b_value = new BigDecimal(b_value_str == null ? "0" : b_value_str);
				values[i] = values[i].add(b_value);
			}
			/******** 字段的中文描述 ***********/
			HbaseMapType htype = HbaseMapTypeService.me.findByKey(column);
			resBody.setType(htype.getKeyValue());
			resBody.setData(values);
		} catch (IOException e) {
			errorCode = "-1";
			errorMsg = "连接大数据失败!";
		}

		detail.setShow_res_id(StrKit.getRandomUUID());
		detail.setShow_res_body(resBody);
		detail.setShow_res_code(errorCode);
		detail.setShow_res_error(errorMsg);
		/********* 返回json数据 ****************/
		return JsonKit.toJson(detail);
	}

	public NormalDetail singleObject(Map para) {
		/*********** 变量区 ***********/
		String errorCode = "0";
		String errorMsg = "";
		NormalDetail detail = new NormalDetail();
		Date beginDate = null, endDate = null;
		List<String> columns = new ArrayList<String>();
		List<Date> dList = new ArrayList<>();

		/*********** 参数区 ***********/
		String column = (String) para.get("key");
		String beginStr = (String) para.get("begin");
		String endStr = (String) para.get("end");
		int type = (int) para.get("type");
		int value = (int) para.get("value");

		/*********** 时间格式异常处理 ***********/
		try {
			beginDate = sdf.parse(beginStr);
			endDate = sdf.parse(endStr);
		} catch (ParseException e1) {
			errorCode = "-1";
			errorMsg = "日期格式转换异常!";
		}

		/********** 构造json ***********/
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginStr);
		reqPara.setEnd_date(endStr);
		reqPara.setKey(column);
		reqPara.setType(String.valueOf(type));
		reqPara.setValue(String.valueOf(value));
		detail.setShow_req_para(reqPara);

		/*********** 行键值 ***********/

		/*********** 时间_字段 ***********/
		List<String> dateStrList = new ArrayList<>();
		dList = DateTool.getBetweenDates(beginDate, endDate);
		Show_res_body resBody = new Show_res_body();

		/********** 时间拼凑cloumn字段 *******/
		BigDecimal[] values = new BigDecimal[dList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = new BigDecimal("0");
		}
		for (Date d : dList) {
			dateStrList.add(sdf.format(d));
			columns.add(sdf.format(d) + "_" + column);
		}
		resBody.setMonth(dateStrList);
		try {
			/*********** 打开hbase 第三方接口 ***********/
			Map<String, String> map = new HashMap<String, String>();
			map = client.getRowsWithColumns(Const.TABLES[type], String.valueOf(value), columns);
			/*********** 循环出累加值 ***********/
			BigDecimal b_value = new BigDecimal("0");
			for (int i = 0; i < values.length; i++) {
				String b_value_str = map.get(sdf.format(dList.get(i)) + "_" + column);
				b_value = new BigDecimal(b_value_str == null ? "0" : b_value_str);
				values[i] = values[i].add(b_value);
			}
			/******** 字段的中文描述 ***********/
			HbaseMapType htype = HbaseMapTypeService.me.findByKey(column);
			resBody.setType(htype.getKeyValue());
			resBody.setData(values);
		} catch (IOException e) {
			errorCode = "-1";
			errorMsg = "连接大数据失败!";
		}

		detail.setShow_res_id(StrKit.getRandomUUID());
		detail.setShow_res_body(resBody);
		detail.setShow_res_code(errorCode);
		detail.setShow_res_error(errorMsg);
		return detail;
	}

	/**
	 * 汇总
	 * 
	 * @param para
	 * @return
	 */
	public String detail(Map para) {
		/*********** 变量区 ***********/
		String errorCode = "0";
		String errorMsg = "";
		NormalAll detail = new NormalAll();
		Date beginDate = null, endDate = null;
		List<String> columns = new ArrayList<String>();
		List<Date> dList = new ArrayList<>();

		/*********** 参数区 ***********/
		String column = (String) para.get("key");
		String beginStr = (String) para.get("begin");
		String endStr = (String) para.get("end");
		int type = (int) para.get("type");
		int value = (int) para.get("value");

		/*********** 判断用户取哪个表 ***********/
		try {
			beginDate = sdf.parse(beginStr);
			endDate = sdf.parse(endStr);
		} catch (ParseException e1) {
			errorCode = "-1";
			errorMsg = "日期格式转换异常!";
		}

		/*********** 构造json实体类 ***********/
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginStr);
		reqPara.setEnd_date(endStr);
		reqPara.setKey(column);
		reqPara.setType(String.valueOf(type));
		reqPara.setValue(String.valueOf(value));
		detail.setShow_req_para(reqPara);

		/*********** 行键值 ***********/
		List<String> rows = new ArrayList<String>();
		if (type == 3) {// 门店
			for (BaStore store : StoreService.me.findAll()) {
				rows.add(store.getId().toString());
			}
		} else if (type == 2) {// 部门
			for (HrDepartment dept : DepartmentService.me.findDeptByStoreId(value)) {
				rows.add(dept.getId().toString());
			}
		} else if (type == 1) {// 员工
			for (HrEmployee emp : EmployeeService.me.findEmployeeByDeptId(value)) {
				rows.add(emp.getId().toString());
			}
		} else if (type == 0) {// 合同
			for (CusContract cu : ContractService.me.findContractByEmpId(value)) {
				rows.add(cu.getId().toString());
			}
		}

		/*********** 时间_字段 ***********/
		List<String> dateStrList = new ArrayList<>();
		dList = DateTool.getBetweenDates(beginDate, endDate);
		Show_res_body_detail resBody = new Show_res_body_detail();

		/*********** 构造hbase字段 ***********/

		for (Date d : dList) {
			dateStrList.add(sdf.format(d));
			columns.add(sdf.format(d) + "_" + column);
		}

		try {
			List<Map<String, String>> results = client.getRowsWithColumns(Const.TABLES[type], rows, columns);
			List<Data> datas = new ArrayList<>();
			for (Map<String, String> map : results) {
				BigDecimal[] values = new BigDecimal[dList.size()];
				for (int i = 0; i < values.length; i++) {
					values[i] = new BigDecimal("0");
				}
				Data data = new Data();
				data.setMonth(dateStrList);
				for (int i = 0; i < values.length; i++) {
					String b_value_str = map.get(sdf.format(dList.get(i)) + "_" + column);
					BigDecimal value_decimal = new BigDecimal(b_value_str == null ? "0" : b_value_str);
					values[i] = values[i].add(value_decimal);
				}
				data.setData(values);
				datas.add(data);
			}
			resBody.setDatas(datas);
			HbaseMapType htype = HbaseMapTypeService.me.findByKey(column);
			resBody.setType(htype.getKeyValue());
		} catch (IOException e) {
			e.printStackTrace();
			errorCode = "-1";
			errorMsg = "查询异常!";
		}
		detail.setShow_res_id(StrKit.getRandomUUID());
		detail.setShow_res_body(resBody);
		detail.setShow_res_code(errorCode);
		detail.setShow_res_error(errorMsg);
		return JsonKit.toJson(detail);
	}

	/**
	 * 汇总
	 * 
	 * @param para
	 * @return
	 */
	public NormalAll detailObject(Map para) {
		/*********** 变量区 ***********/
		String errorCode = "0";
		String errorMsg = "";
		NormalAll detail = new NormalAll();
		Date beginDate = null, endDate = null;
		List<String> columns = new ArrayList<String>();

		/*********** 参数区 ***********/
		String column = (String) para.get("key");
		beginDate = (Date) para.get("begin");

		endDate = (Date) para.get("end");
		int type = (int) para.get("type");
		int value = (int) para.get("value");

		/*********** 构造json实体类 ***********/
		Show_req_para reqPara = new Show_req_para();
		reqPara.setBegin_date(beginDate.toString());
		reqPara.setEnd_date(endDate.toString());
		reqPara.setKey(column);
		reqPara.setType(String.valueOf(type));
		reqPara.setValue(String.valueOf(value));
		detail.setShow_req_para(reqPara);

		/*********** 行键值 ***********/
		List<String> rows = new ArrayList<String>();
		if (type == 3) {// 门店
			for (BaStore store : StoreService.me.findAll()) {
				rows.add(store.getId().toString());
			}
		} else if (type == 2) {// 部门
			for (HrDepartment dept : DepartmentService.me.findDeptByStoreId(value)) {
				rows.add(dept.getId().toString());
			}
		} else if (type == 1) {// 员工
			for (HrEmployee emp : EmployeeService.me.findEmployeeByDeptId(value)) {
				rows.add(emp.getId().toString());
			}
		} else if (type == 0) {// 合同
			for (CusContract cu : ContractService.me.findContractByEmpId(value)) {
				rows.add(cu.getId().toString());
			}
		}

		/*********** 时间_字段 ***********/
		List<String> dateStrList = new ArrayList<>();

		List<Date> dates = DateTool.getBetweenDates(beginDate, endDate);
		Show_res_body_detail resBody = new Show_res_body_detail();

		/*********** 构造hbase字段 ***********/
		for (Date d : dates) {
			dateStrList.add(sdf.format(d));
			columns.add(sdf.format(d) + "_" + column);
		}

		try {
			List<Map<String, String>> results = client.getRowsWithColumns(Const.TABLES[type], rows, columns);
			List<Data> datas = new ArrayList<>();
			for (Map<String, String> map : results) {
				BigDecimal[] values = new BigDecimal[dates.size()];
				for (int i = 0; i < values.length; i++) {
					values[i] = new BigDecimal("0");
				}
				Data data = new Data();
				data.setMonth(dateStrList);
				for (int i = 0; i < dates.size(); i++) {
					String b_value_str = map.get(sdf.format(dates.get(i)) + "_" + column);
					BigDecimal value_decimal = new BigDecimal(b_value_str == null ? "0" : b_value_str);
					values[i] = values[i].add(value_decimal);
				}
				data.setData(values);
				datas.add(data);
			}
			resBody.setDatas(datas);
			HbaseMapType htype = HbaseMapTypeService.me.findByKey(column);
			resBody.setType(htype.getKeyValue());
		} catch (IOException e) {
			e.printStackTrace();
			errorCode = "-1";
			errorMsg = "查询异常!";
		}
		detail.setShow_res_id(StrKit.getRandomUUID());
		detail.setShow_res_body(resBody);
		detail.setShow_res_code(errorCode);
		detail.setShow_res_error(errorMsg);
		return detail;
	}
}
