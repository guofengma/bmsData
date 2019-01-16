package com.jyd.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyd.hbase.HbaseDAOImpl;

public class HbaseAdminService {
	public static final HbaseAdminService me = new HbaseAdminService();
	private String tableName = "";
	private HbaseDAOImpl client = new HbaseDAOImpl();
	private List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
	private String day = "";
	private String keys[];

	public List<Map<String, Object>> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Map<String, Object>> recordList) {
		this.recordList = recordList;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void updateCase() throws IOException {
		Map<String, String> fieldNameValues = new HashMap<String, String>();
		for (Map<String, Object> record : recordList) {
			String rowKey = record.get(keys[0]).toString();
			for (String key : keys) {
				String value = record.get(key) == null ? "" : record.get(key).toString();
				fieldNameValues.put("master:" + key, value);
			}
			for (Map.Entry<String, Object> e : record.entrySet()) {
				if (!existKey(keys, e.getKey())) {
					String value = e.getValue() == null ? "" : e.getValue().toString();
					fieldNameValues.put(day + "_" + e.getKey(), value);
				}
			}
			System.err.println("hbase id:" + rowKey + "\ttable :" + tableName + "");
			client.update(tableName, rowKey, fieldNameValues);
		}
	}

	private boolean existKey(String[] keys, String currentkey) {
		boolean flag = false;
		for (String key : keys) {
			if (key.equals(currentkey)) {
				flag = true;
			}
		}
		return flag;
	}
}
