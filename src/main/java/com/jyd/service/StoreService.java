package com.jyd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyd.common.model.BaStore;
import com.jyd.common.model.HbaseMapType;

/**
 * 门店业务层
 * 
 * @author mjy
 *
 */
public class StoreService {
	public static final StoreService me = new StoreService();
	public final HbaseMapType mapDao = new HbaseMapType().dao();
	private final BaStore dao = new BaStore().dao();
	private EmployeeService eS = EmployeeService.me;

	public List<BaStore> findAll() {
		return dao.find("select * from ba_store");
	}

	public BaStore getById(int id) {
		return dao.findById(id);
	}

	public List<Map<String, Object>> getDeptListByStoreId(int currentStoreId,
			List<Map<String, Object>> deptRecordList) {
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> dept : deptRecordList) {
			if (dept.get("store_id").equals(currentStoreId)) {
				recordList.add(dept);
			}
		}
		return recordList;
	}

	public List<Map<String, Object>> findSRListByDRList(List<Map<String, Object>> deptRecordList) {
		List<BaStore> storeList = new ArrayList<>();
		storeList = findAll();
		List<HbaseMapType> typeList = new ArrayList<>();
		typeList = eS.findTypeList();
		BigDecimal values[] = new BigDecimal[typeList.size()];
		List<Map<String, Object>> recordList = new ArrayList<>();
		for (BaStore store : storeList) {
			for (int i = 0; i < typeList.size(); i++) {
				values[i] = new BigDecimal("0");
			}
			List<Map<String, Object>> tempRecordList = new ArrayList<>();
			tempRecordList = getDeptListByStoreId(store.getId(), deptRecordList);
			for (Map<String, Object> record : tempRecordList) {
				for (int i = 0; i < typeList.size(); i++) {
					Object temp = record.get(typeList.get(i).getKeyName());
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
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", store.getId());
			map.put("short_name", store.getShortName());
			for (int i = 0; i < typeList.size(); i++) {
				map.put(typeList.get(i).getKeyName(), values[i]);
			}
			recordList.add(map);
		}
		return recordList;
	}
}