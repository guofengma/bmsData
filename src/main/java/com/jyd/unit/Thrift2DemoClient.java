package com.jyd.unit;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.thrift2.generated.TColumnValue;
import org.apache.hadoop.hbase.thrift2.generated.TGet;
import org.apache.hadoop.hbase.thrift2.generated.THBaseService;
import org.apache.hadoop.hbase.thrift2.generated.TIOError;
import org.apache.hadoop.hbase.thrift2.generated.TPut;
import org.apache.hadoop.hbase.thrift2.generated.TResult;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Thrift2DemoClient {
	public static void main(String[] args) throws TIOError, TException {
		System.out.println("Thrift2 Demo");
		System.out.println("Usage: DemoClient [host=localhost] [port=9090]");
		System.out.println(
				"This demo assumes you have a table called \"example\" with a column family called \"family1\"");

		String host = "code-server";
		int port = 9090;

		// use passed in arguments instead of defaults
		if (args.length >= 1) {
			host = args[0];
		}
		if (args.length >= 2) {
			port = Integer.parseInt(args[1]);
		}

		int timeout = 10000;
		boolean framed = false;

		TTransport transport = new TSocket(host, port, timeout);
		if (framed) {
			transport = new TFramedTransport(transport);
		}
		TProtocol protocol = new TBinaryProtocol(transport);
		// This is our thrift client.
		THBaseService.Iface client = new THBaseService.Client(protocol);

		// open the transport
		transport.open();

		ByteBuffer table = ByteBuffer.wrap("employee".getBytes());

		TPut put = new TPut();
		put.setRow("0000".getBytes());

		TColumnValue columnValue = new TColumnValue();
		columnValue.setFamily("master".getBytes());
		columnValue.setQualifier("title,".getBytes());
		columnValue.setValue("change thirft".getBytes());
		List<TColumnValue> columnValues = new ArrayList<TColumnValue>();
		columnValues.add(columnValue);
		put.setColumnValues(columnValues);

		client.put(table, put);

		TGet get = new TGet();
		get.setRow("0001".getBytes());

		TResult result = client.get(table, get);

		System.out.print("row = " + new String(result.getRow()));
		for (TColumnValue resultColumnValue : result.getColumnValues()) {
			System.out.print(",family = " + new String(resultColumnValue.getFamily()));
			System.out.print(",qualifier = " + new String(resultColumnValue.getFamily()));
			System.out.print(",value = " + new String(resultColumnValue.getValue()));
			System.out.print(",timestamp = " + resultColumnValue.getTimestamp());
		}

		transport.close();
	}
}