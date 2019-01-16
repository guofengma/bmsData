package com.jyd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyd.common.model.BaStore;
import com.jyd.common.model.HbaseMapType;
import com.jyd.common.model.HrDepartment;

/**
 * 
 * @author mjy
 *
 */
public class DepartmentService {
	public static final DepartmentService me = new DepartmentService();
	private HrDepartment dao = new HrDepartment().dao();
	private EmployeeService eS = EmployeeService.me;

	public HrDepartment getById(int id) {
		return dao.findById(id);
	}

	public List<HrDepartment> findDepartmentList() {
		return dao.find("select * from hr_department where state = 0");
	}

	public List<HrDepartment> findDeptByStoreId(int storeId) {
		return dao.find("select * from hr_department where store_id=" + storeId + "");
	}

	public List<Map<String, Object>> getEmpListByDeptId(int currentDeptId, List<Map<String, Object>> empRecordList) {
		List<Map<String, Object>> deptRecordList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> empRecord : empRecordList) {
			if (empRecord.get("department_id").equals(currentDeptId)) {
				deptRecordList.add(empRecord);
			}
		}
		return deptRecordList;
	}

	public List<Map<String, Object>> findDRListByERList(List<Map<String, Object>> empRecordList, String date) {
		List<HbaseMapType> typeList = new ArrayList<>();
		typeList = eS.findTypeList();
		List<Map<String, Object>> deptRecordList = new ArrayList<Map<String, Object>>();
		BigDecimal values[] = new BigDecimal[typeList.size()];
		List<HrDepartment> deptList = new ArrayList<HrDepartment>();
		deptList = findDepartmentList();

		for (HrDepartment dept : deptList) {
			for (int i = 0; i < typeList.size(); i++) {
				values[i] = new BigDecimal("0");
			}
			List<Map<String, Object>> empList = new ArrayList<>();
			empList = getEmpListByDeptId(dept.getId(), empRecordList);
			for (Map<String, Object> emp : empList) {
				for (int i = 0; i < typeList.size(); i++) {
					if (typeList.get(i).getType() == 0) {
						Object temp = emp.get(typeList.get(i).getKeyName());
						temp = temp == null ? 0 : temp;
						BigDecimal value = new BigDecimal("0");
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
						values[i] = values[i].add(value);
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dept.getId());
			map.put("store_id", dept.getStoreId());
			for (int i = 0; i < typeList.size(); i++) {
				if (typeList.get(i).getType() == 0) {
					map.put(typeList.get(i).getKeyName(), values[i]);
				} else {
					map.put(typeList.get(i).getKeyName(), eS.get(dept.getId(), date, typeList.get(i)));
				}
			}
			deptRecordList.add(map);
		}
		return deptRecordList;
	}

}
