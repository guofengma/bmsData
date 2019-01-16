package com.jyd.service;

import com.jyd.common.model.HbaseMapType;

public class HbaseMapTypeService {
	public static final HbaseMapTypeService me = new HbaseMapTypeService();
	private HbaseMapType dao = new HbaseMapType();

	public HbaseMapType findByKey(String key) {
		return dao.findFirst("select * from hbase_map_type where key_name ='" + key + "'");
	}
}
