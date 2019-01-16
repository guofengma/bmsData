package com.jyd.unit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.CusContractScheduleTrack;

public class AAAUnit {
	private static String url = "jdbc:mysql://rm-wz977ob0y3910692zzo.mysql.rds.aliyuncs.com/bmsover?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

	public static void main(String[] args) throws IOException {
		DruidPlugin dp = new DruidPlugin(url, "bms2018", "K$ai%faizhuaY");
		ActiveRecordPlugin arp = new ActiveRecordPlugin("aliyun", dp);
		arp.addMapping("cus_contract_schedule_track", CusContractScheduleTrack.class);
		arp.addMapping("ba_store", BaStore.class);
		dp.start();
		arp.start();

		StringBuilder str = new StringBuilder();
		String arr[] = { "201807", "201808", "201809", "201810", "201811" };
		str.append("-----------所有部门汇总-------------------");
		for (String s : arr) {
			str.append("\r\n");
			str.append("进件");
			str.append("\t");
			str.append(getEnterCount(s));
			str.append("\t");
			str.append("放款");
			str.append("\t");
			str.append(getLoanCount(s));
			str.append("\t");
			str.append("\r\n");
		}

		BaStore dao = new BaStore().dao();
		List<BaStore> listStore = dao.find("select * from ba_store");
		for (BaStore store : listStore) {
			str.append("\r\n");
			str.append("-----------" + store.getShortName() + "-------------------");
			str.append("\r\n");
			for (String s : arr) {
				str.append("\r\n");
				str.append("\t");
				str.append(s);
				str.append("\t");
				str.append("进件");
				str.append("\t");
				str.append(getEnterCount(s, store.getId()));
				str.append("\t");
				str.append("放款");
				str.append("\t");
				str.append(getLoanCount(s, store.getId()));
				str.append("\t");
				str.append("\r\n");
			}
		}
		writer(str.toString());
	}

	public static void writer(String str) throws IOException {
		File file = new File("/home/mjy/Desktop/1.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		writer.write(str);
		writer.flush();
		writer.close();
	}

	public static long getEnterCount(String s, int store) {
		String hql = "SELECT count(1) as ba_count FROM cus_contract_schedule_track where  date_format(create_date,'%Y%m') = '"
				+ s + "' and store_id =" + store;
		Record r = Db.findFirst(hql);
		return r.getLong("ba_count");
	}

	public static long getEnterCount(String s) {
		String hql = "SELECT count(1) as ba_count FROM cus_contract_schedule_track where  date_format(create_date,'%Y%m') = '"
				+ s + "'";
		Record r = Db.findFirst(hql);
		return r.getLong("ba_count");
	}

	public static long getLoanCount(String s, int store) {
		String hql = "SELECT count(1) as ba_count FROM cus_contract_schedule_track where  date_format(create_date,'%Y%m') = '"
				+ s + "' and status is not null and status !=5 and store_id=" + store;
		Record r = Db.findFirst(hql);
		return r.getLong("ba_count");
	}

	public static long getLoanCount(String s) {
		String hql = "SELECT count(1) as ba_count FROM cus_contract_schedule_track where  date_format(create_date,'%Y%m') = '"
				+ s + "' and status is not null and status !=5";
		Record r = Db.findFirst(hql);
		return r.getLong("ba_count");
	}

}
