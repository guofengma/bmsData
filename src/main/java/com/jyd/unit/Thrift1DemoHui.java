package com.jyd.unit;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.hadoop.hbase.thrift.generated.AlreadyExists;
import org.apache.hadoop.hbase.thrift.generated.IOError;
import org.apache.hadoop.hbase.thrift.generated.IllegalArgument;
import org.apache.hadoop.hbase.thrift.generated.TRowResult;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

public class Thrift1DemoHui {

	private static final String CHARSET = "UTF-8";
	static DecimalFormat formatter = new DecimalFormat("00");
	private final HBaseThrift1DAO client;
	private String table = "";

	public void setTable(String table) {
		this.table = table;
	}

	public Thrift1DemoHui(String host, int port) {
		client = new HBaseThrift1DAOImpl(host, port);
		try {
			client.open();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public Thrift1DemoHui() {
		this("code-server", 7070);
	}

	static String randomlyBirthday() {
		Random r = new Random();
		int year = 1900 + r.nextInt(100);
		int month = 1 + r.nextInt(12);
		int date = 1 + r.nextInt(30);
		return String.valueOf(year + "-" + formatter.format(month) + "-" + formatter.format(date));
	}

	static String randomlyGender() {
		Random r = new Random();
		int flag = r.nextInt(2);
		return flag == 0 ? "M" : "F";
	}

	static String randomlyUserType() {
		Random r = new Random();
		int flag = 1 + r.nextInt(10);
		return String.valueOf(flag);
	}

	static ByteBuffer wrap(String value) {
		ByteBuffer bb = null;
		try {
			bb = ByteBuffer.wrap(value.getBytes(CHARSET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bb;
	}

	static DecimalFormat rowKeyFormatter = new DecimalFormat("0000");

	public void createTable(String table) throws IOError, IllegalArgument, AlreadyExists, TException {
		client.createTable(table);
	}

	public void caseForUpdate() throws TException {
		boolean writeToWal = false;
		Map<String, String> attributes = new HashMap<String, String>(0);
		String table = setTable();
		// put kv pairs
		String rowKey = "111";
		Map<String, String> fieldNameValues = new HashMap<String, String>();
		fieldNameValues.put("master:20180905_total", "12");
		fieldNameValues.put("master:20180906_total", "13");
		fieldNameValues.put("master:20180907_total", "15");
		client.update(table, rowKey, writeToWal, fieldNameValues, attributes);
	}

	public void caseForDeleteCells() throws TException {
		boolean writeToWal = false;
		Map<String, String> attributes = new HashMap<String, String>(0);
		String table = setTable();
		// put kv pairs
		for (long i = 5; i < 10; i++) {
			String rowKey = rowKeyFormatter.format(i);
			List<String> columns = new ArrayList<String>(0);
			columns.add("info:birthday");
			client.deleteCells(table, rowKey, writeToWal, columns, attributes);
		}
	}

	private String setTable() {
		return table;
	}

	public void caseForDeleteRow() throws TException {
		String rowKey = "18";
		Map<String, String> attributes = new HashMap<String, String>(0);
		String table = setTable();
		// delete rows
		client.deleteRow(table, rowKey, attributes);
	}

	protected byte[] decode(ByteBuffer buffer) {
		byte[] bytes = new byte[buffer.limit()];
		for (int i = 0; i < buffer.limit(); i++) {
			bytes[i] = buffer.get();
		}
		return bytes;
	}

	public void caseForScan() throws TException {
		Map<String, String> attributes = new HashMap<String, String>(0);
		String table = setTable();
		String startRow = "1";
		String stopRow = "9000";
		List<String> columns = new ArrayList<String>(0);
		int id = client.scannerOpen(table, startRow, stopRow, columns, attributes);
		int nbRows = 2;
		List<TRowResult> results = client.scannerGetList(id, nbRows);
		while (results != null && !results.isEmpty()) {
			for (TRowResult result : results) {
				client.iterateResults(result);
			}
			results = client.scannerGetList(id, nbRows);
		}
		client.scannerClose(id);
	}

	public void caseForGet() throws TException {
		Map<String, String> attributes = new HashMap<String, String>();
		String table = setTable();
		List<String> rows = new ArrayList<String>(0);
		// rows.add("12");
		List<String> columns = new ArrayList<String>(0);
		columns.add("master:20181025_principal_5_10w");
		columns.add("master:20181025_principal");
		columns.add("master:20181025_must_capital");

		List<TRowResult> results = client.getRowsWithColumns(table, rows, columns, attributes);
		for (TRowResult result : results) {
			client.iterateResults(result);
		}
	}

	public void caseForGetHui() throws TException {
		Map<String, String> attributes = new HashMap<String, String>();
		String table = setTable();
		List<String> rows = new ArrayList<String>(0);

		for (int i = 1; i <= 18; i++) {
			rows.add(String.valueOf(i));
		}

		List<String> columns = new ArrayList<String>(0);
		// columns.add("master:20181025_principal_5_10w");
		// columns.add("master:20181025_principal");
		// columns.add("master:20181025_must_capital");

		String[] date = { "20181025", "20181026", "20181027", "20181028" };

		for (String value : date) {

			columns.add("master:" + value + "_uncleared_num");
			columns.add("master:" + value + "_uncleared_num_1_3");
			columns.add("master:" + value + "_uncleared_num_4_6");
			columns.add("master:" + value + "_uncleared_num_7_12");
			columns.add("master:" + value + "_uncleared_num_12");
		}

		List<TRowResult> results = client.getRowsWithColumns(table, rows, columns, attributes);
		for (TRowResult result : results) {
			client.iterateResults(result);
		}
	}

	public void getTable() throws TException {
		for (String t : client.getTables()) {
			System.err.println(t);
		}
	}

	public static void main(String[] args) throws IOError, IllegalArgument, TException, UnsupportedEncodingException {
		Thrift1DemoHui test = new Thrift1DemoHui();
		// test.createTable("hbase_cus_contract");
		// test.createTable("hbase_hr_employee");
		// test.createTable("hbase_hr_department");
		// test.createTable("hbase_hr_store");
		// test.getTable();
		// test.caseForUpdate(); // insert or update rows/cells
		// test.caseForDelete(); // delete cells
		test.setTable("hbase_hr_store");
		// test.caseForDeleteRow(); // delete rows
		// test.setTable("hbase_hr_store");
		test.caseForScan(); // scan rows
		// test.caseForGetHui(); // get rows
	}

}