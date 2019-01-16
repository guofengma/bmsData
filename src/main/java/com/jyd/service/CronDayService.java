package com.jyd.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jyd.common.Const;
import com.jyd.tool.HbaseAdminService;

public class CronDayService {
	public static final CronDayService me = new CronDayService();
	private HbaseAdminService hS = HbaseAdminService.me;
	private ContractService cS = ContractService.me;
	private EmployeeService eS = EmployeeService.me;
	private DepartmentService dS = DepartmentService.me;
	private StoreService sS = StoreService.me;
	private PositionService pS = PositionService.me;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public void start() {
		try {

			String day = sdf.format(new Date());
			hS.setTableName(Const.TABLES[Const.TABLES.length - 1]);
			hS.setKeys(new String[] { "id" });
			hS.setDay(day);
			hS.setRecordList(pS.findRecordList());
			hS.updateCase();

			hS.setTableName(Const.TABLES[0]);
			hS.setKeys(new String[] { "id", "cus_contract_no", "store_id", "person_responsible_id" });
			hS.setDay(day);
			hS.setRecordList(cS.findCRList());
			hS.updateCase();

			hS.setTableName(Const.TABLES[1]);
			hS.setKeys(new String[] { "id", "department_id" });
			hS.setDay(day);
			List<Map<String, Object>> eRList = new ArrayList<Map<String, Object>>();
			eRList = eS.find(day);
			hS.setRecordList(eRList);
			hS.updateCase();

			hS.setTableName(Const.TABLES[2]);
			hS.setKeys(new String[] { "id", "store_id" });
			hS.setDay(day);
			List<Map<String, Object>> dRList = new ArrayList<Map<String, Object>>();
			dRList = dS.findDRListByERList(eRList, day);
			hS.setRecordList(dRList);
			hS.updateCase();

			hS.setTableName(Const.TABLES[3]);
			hS.setKeys(new String[] { "id", "short_name" });
			hS.setDay(day);
			hS.setRecordList(sS.findSRListByDRList(dRList));
			hS.updateCase();

			System.err.println("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
