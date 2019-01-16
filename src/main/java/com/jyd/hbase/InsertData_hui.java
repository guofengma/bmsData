package com.jyd.hbase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.jyd.tool.DateTool;

public class InsertData_hui {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 26; i++) {
			A(i + "");
		}
	}

	public static void A(String rowkey) throws IOException {
		// instantiate Configuration class
		Configuration config = HBaseConfiguration.create();

		// instantiate HTable class
		HTable hTable = new HTable(config, "hbase_hr_store");

		// instantiate Put class
		Put p = new Put(Bytes.toBytes(rowkey));

//		String col = "principal_30w";

		int num = 10000;
		
		
		List<String> dateList = getDateList();
		Map<String, List<String>> fields = getFields();
		List<String> list = fields.get("money");
		for (String date : dateList) {
			for (String col : list) {
				p.add(Bytes.toBytes("master"), Bytes.toBytes(date+"_" + col),
						Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
			}
		}
		
		
		

		// add values using add() method
//		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181124_" + col),
//				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
//		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181125_" + col),
//				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
//		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181126_" + col),
//				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
//		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181127_" + col),
//				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
//		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181128_" + col),
//				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));

		// save the put Instance to the HTable.
		hTable.put(p);
		System.out.println("data inserted successfully");

		// close HTable instance
		hTable.close();
	}

	public static List<String> getDateList() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		Date endDate = calendar.getTime();
		calendar.add(Calendar.MONTH, -2);
		Date beginDate = calendar.getTime();

		List<Date> betweenDates = DateTool.getBetweenDates(beginDate, endDate);
		List<String> dates = new ArrayList<>();

		for (Date d : betweenDates) {
			dates.add(sdf.format(d));
		}

		return dates;

	}
	
	public static Map<String, List<String>> getFields(){
		String number = "uncleared_num\n" + 
				"uncleared_num_1_3\n" + 
				"uncleared_num_4_6\n" + 
				"uncleared_num_7_12\n" + 
				"uncleared_num_12\n" + 
				"surplus_stage_num\n" + 
				"principal_5w\n" + 
				"principal_5_10w\n" + 
				"principal_10_20w\n" + 
				"principal_20_30w\n" + 
				"principal_30w\n" + 
				"late_amount\n" + 
				"late_num\n" + 
				"must_settle_contract_num\n" + 
				"alr_settle_contract_num\n" + 
				"entry_contract_num\n" + 
				"pass_contract_num\n" + 
				"loan_contract_num\n" + 
				"entry_num\n" + 
				"position_num\n" + 
				"leave_num\n" + 
				"tuning_in_num\n" + 
				"call_out_num\n" + 
				"current_num\n" + 
				"entry_person_num\n" + 
				"product_type\n" + 
				"ba_repayment_type_id\n" + 
				"late_m\n" + 
				"late_customer_4_num\n" + 
				"current_m_customer_num\n" + 
				"alr_repayment_customer_num\n" + 
				"must_repayment_customer_num\n" + 
				"late_m_1\n" + 
				"late_m_2\n" + 
				"late_m_3\n" + 
				"loan_amount_product_1\n" + 
				"loan_amount_product_2\n" + 
				"late_m_4\n" + 
				"late_m_5\n" + 
				"late_m_6\n" + 
				"late_m_7\n" + 
				"loan_amount_product_3\n" + 
				"loan_amount_product_4";
		String money = "surplus_interest\n" + 
				"surplus_capital"+"late_amount"+"must_settle_contract_principal\n" + 
				"alr_settle_contract_principal\n" + 
				"uncleared_contract_principal\n" + "demand_amount\n" + 
				"loan_amount\n" + 
				"principal\n" + 
				"average_value\n" + "must_capital\n" + 
				"alr_capital\n" + 
				"must_interest\n" + 
				"alr_interest\n";
		
		String[] split1 = number.split("\\n");
		String[] split2 = money.split("\\n");
		List<String> asList1 = Arrays.asList(split1);
		List<String> asList2 = Arrays.asList(split2);
		
		
		Map<String, List<String>> map = new HashMap<>();
		map.put("number", asList1);
		map.put("money", asList2);
		
		return map;
		
	}
	
	

}