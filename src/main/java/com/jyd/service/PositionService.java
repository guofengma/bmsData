package com.jyd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.BaPositionType;

public class PositionService {
	public final static PositionService me = new PositionService();
	private BaPositionType dao = new BaPositionType().dao();

	public List<BaPositionType> find() {
		String sql = "select * from `ba_position_type`";
		return dao.find(sql);
	}

	public List<Map<String, Object>> findRecordList() {
		String hql = "SELECT id,(select count(1) from hr_employee where position_id=bpt.id) position_num FROM ba_position_type bpt";
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Record re : Db.find(hql.toString())) {
			mapList.add(re.getColumns());
		}
		return mapList;
	}
}
