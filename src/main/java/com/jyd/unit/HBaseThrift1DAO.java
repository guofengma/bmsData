package com.jyd.unit;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.thrift.generated.Hbase;
import org.apache.hadoop.hbase.thrift.generated.TRowResult;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public abstract class HBaseThrift1DAO {
	protected static final String CHARSET = "UTF-8";
	public String ColumnFamily = "master";
	private String host = "code-server";
	private int port = 9090;
	private final TTransport transport;
	protected final Hbase.Client client;

	public HBaseThrift1DAO() {
		transport = new TSocket(host, port);
		TProtocol protocol = new TBinaryProtocol(transport, true, true);
		client = new Hbase.Client(protocol);
	}

	public HBaseThrift1DAO(String host, int port) {
		transport = new TSocket(host, port);
		TProtocol protocol = new TBinaryProtocol(transport, true, true);
		client = new Hbase.Client(protocol);
	}

	public void open() throws TTransportException {
		if (transport != null) {
			if (!transport.isOpen()) {
				transport.open();
			}
		}
	}

	public void close() {
		if (transport != null) {
			if (transport.isOpen()) {
				transport.close();
			}
		}
	}

	public abstract void createTable(String table) throws TException;

	public abstract List<String> getTables() throws TException;

	public abstract void update(String table, String rowKey, boolean writeToWal, String fieldName, String fieldValue,
			Map<String, String> attributes) throws TException;

	public abstract void update(String table, String rowKey, boolean writeToWal, Map<String, String> fieldNameValues,
			Map<String, String> attributes) throws TException;

	public abstract void deleteCell(String table, String rowKey, boolean writeToWal, String column,
			Map<String, String> attributes) throws TException;

	public abstract void deleteCells(String table, String rowKey, boolean writeToWal, List<String> columns,
			Map<String, String> attributes) throws TException;

	public abstract void deleteRow(String table, String rowKey, Map<String, String> attributes) throws TException;

	public abstract int scannerOpen(String table, String startRow, List<String> columns, Map<String, String> attributes)
			throws TException;

	public abstract int scannerOpen(String table, String startRow, String stopRow, List<String> columns,
			Map<String, String> attributes) throws TException;

	public abstract int scannerOpenWithPrefix(String table, String startAndPrefix, List<String> columns,
			Map<String, String> attributes) throws TException;

	public abstract int scannerOpenTs(String table, String startRow, List<String> columns, long timestamp,
			Map<String, String> attributes) throws TException;

	public abstract int scannerOpenTs(String table, String startRow, String stopRow, List<String> columns,
			long timestamp, Map<String, String> attributes) throws TException;

	public abstract List<TRowResult> scannerGetList(int id, int nbRows) throws TException;

	public abstract List<TRowResult> scannerGet(int id) throws TException;

	public abstract List<TRowResult> getRow(String table, String row, Map<String, String> attributes) throws TException;

	public abstract List<TRowResult> getRows(String table, List<String> rows, Map<String, String> attributes)
			throws TException;

	public abstract List<TRowResult> getRowsWithColumns(String table, List<String> rows, List<String> columns,
			Map<String, String> attributes) throws TException;

	public abstract void scannerClose(int id) throws TException;

	public abstract void iterateResults(TRowResult result);

	public abstract String iterateResultjson(TRowResult result);

}