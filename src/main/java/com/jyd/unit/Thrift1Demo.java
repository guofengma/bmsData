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

public class Thrift1Demo {

	private static final String CHARSET = "UTF-8";
	static DecimalFormat formatter = new DecimalFormat("00");
	private HBaseThrift1DAOImpl client = new HBaseThrift1DAOImpl();
	private String table = "";

	public void setTable(String table) {
		this.table = table;
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

	public void caseForUpdate(String key) throws TException {
		boolean writeToWal = false;
		Map<String, String> attributes = new HashMap<String, String>(0);
		String table = setTable();
		// put kv pairs
		String rowKey = key;
		Map<String, String> fieldNameValues = new HashMap<String, String>();
		int i = 10000;
		int j = 1000;
		fieldNameValues.put("master:20181120_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181121_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181122_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181123_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181124_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181125_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181126_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181127_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181128_loan_amount", String.valueOf(Math.floor(Math.random() * i)));
		fieldNameValues.put("master:20181129_loan_amount", String.valueOf(Math.floor(Math.random() * i)));

		client.open();
		client.update(table, rowKey, writeToWal, fieldNameValues, attributes);
		client.close();
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
		Map<String, String> attributes = new HashMap<String, String>();
		String table = setTable();
		String startRow = "18";
		String stopRow = "18";
		List<String> columns = new ArrayList<String>();
		// columns.add("master:20181103_entry_contract_num");
		// columns.add("master:20181031_entry_contract_num");
		// columns.add("master:20181026_entry_contract_num");
		// columns.add("master:20181105_entry_contract_num");
		// columns.add("master:20181103_entry_contract_num");
		client.open();
		int id = client.scannerOpen(table, startRow, stopRow, columns, attributes);
		int nbRows = 7;
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
		for (int i = 0; i < 20; i++) {
			rows.add(i + "");
		}
		List<String> columns = new ArrayList<String>(0);
		columns.add("master:20181104_entry_contract_num");
		columns.add("master:20181103_entry_contract_num");
		columns.add("master:20181031_entry_contract_num");
		columns.add("master:20181026_entry_contract_num");
		columns.add("master:20181105_entry_contract_num");
		columns.add("master:20181103_entry_contract_num");
		client.open();
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
		Thrift1Demo test = new Thrift1Demo();
		test.setTable("hbase_hr_store");
		for (int i = 1; i <= 25; i++) {
			test.caseForUpdate(i + "");
		}
	}

}