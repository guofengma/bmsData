package com.jyd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.HbaseMapType;
import com.jyd.common.model.HrEmployee;

/**
 * 
 * @author mjy
 *
 */
public class EmployeeService {
	public static final EmployeeService me = new EmployeeService();
	private HrEmployee empDAO = new HrEmployee().dao();
	private HbaseMapType typeDAO = new HbaseMapType().dao();

	public HrEmployee getById(int id) {
		return empDAO.findById(id);
	}

	public List<Map<String, Object>> find(String d) {
		List<HbaseMapType> typeList = new ArrayList<>();
		typeList = findTypeList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (HrEmployee e : findEmployeeAll()) {
			Map<String, Object> map = new HashMap<>();
			for (HbaseMapType h : typeList) {
				if (h.getType() == 0) {
					map.put("id", e.getId());
					map.put("department_id", e.getDepartmentId());
					map.put(h.getKeyName(), get(e.getId(), d, h));
				}
			}
			System.err.println("emp sql :" + e.getId() + "");
			mapList.add(map);
		}
		return mapList;
	}

	public int findTypeCount() {
		String sql = "select count(1) as count from hbase_map_type where type =0";
		return Db.findFirst(sql).getInt("count");
	}

	public List<HbaseMapType> findTypeList() {
		String sql = "select * from hbase_map_type";
		return typeDAO.find(sql);
	}

	public List<HrEmployee> findEmployeeAll() {
		String hql = "select * from hr_employee";
		return empDAO.find(hql);
	}

	public List<HrEmployee> findEmployeeByDeptId(int deptId) {
		String hql = "select * from hr_employee where department_id=" + deptId + "";
		return empDAO.find(hql);
	}

	public BigDecimal get(int id, String date, HbaseMapType h) {
		if (h.getValue() == null) {
			return new BigDecimal(0);
		}
		String hql = h.getValue() + id;
		Record cord = Db.findFirst(hql);
		Object temp = cord.get(h.getKeyName());
		temp = temp == null ? 0 : temp;
		BigDecimal value = new BigDecimal(0);
		if (temp instanceof Double) {
			value = new BigDecimal((double) temp);
		}
		if (temp instanceof Integer) {
			value = new BigDecimal((int) temp);
		}
		if (temp instanceof Long) {
			value = new BigDecimal((long) temp);
		}
		if (temp instanceof BigDecimal) {
			value = (BigDecimal) temp;
		}
		return value;
	}
}
