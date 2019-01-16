package com.jyd.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDAOImpl implements HbaseDAO {

	@Override
	public Map<String, String> getRowsWithColumns(String tableName, String rowKey, List<String> columns)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();

		// config create
		Configuration config = HBaseConfiguration.create();
		HTable table = new HTable(config, tableName);

		// instantiate Get class
		Get g = new Get(Bytes.toBytes(rowKey));
		g.setMaxVersions();

		// get the Result object
		Result result = table.get(g);

		// read values from Result class object
		for (String column : columns) {
			byte[] temp = result.getValue(Bytes.toBytes("master"), Bytes.toBytes(column));
			map.put(column, Bytes.toString(temp));
		}
		// close HTable instance
		table.close();
		return map;
	}

	@Override
	public List<Map<String, String>> getRowsWithColumns(String tableName, List<String> rowKeys, List<String> columns)
			throws IOException {

		// config create
		Configuration config = HBaseConfiguration.create();
		HTable table = new HTable(config, tableName);

		// instantiate Get class
		List<Get> gs = new ArrayList<>();
		for (String rowkey : rowKeys) {
			Get g = new Get(Bytes.toBytes(rowkey));
			g.setMaxVersions();
			gs.add(g);
		}

		// get the Result object
		Result[] results = table.get(gs);

		// read values from Result class object
		List<Map<String, String>> list = new ArrayList<>();
		for (Result result : results) {
			Map<String, String> map = new HashMap<String, String>();
			for (String column : columns) {
				byte[] temp = result.getValue(Bytes.toBytes("master"), Bytes.toBytes(column));
				map.put(column, Bytes.toString(temp));
			}
			list.add(map);
		}

		// close HTable instance
		table.close();
		return list;
	}

	@Override
	public void update(String tableName, String rowKey, Map<String, String> fieldNameValues) throws IOException {
		// instantiate Configuration class
		Configuration config = HBaseConfiguration.create();

		// instantiate HTable class
		HTable hTable = new HTable(config, tableName);

		// instantiate Put class
		Put p = new Put(Bytes.toBytes(rowKey));

		// add values using add() method
		for (Map.Entry<String, String> entry : fieldNameValues.entrySet()) {
			p.add(Bytes.toBytes("master"), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
		}

		// add the put Instance to the HTable.
		hTable.put(p);
		System.out.println("data inserted successfully");

		// close HTable instance
		hTable.close();
	}

}
