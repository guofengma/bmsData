package com.jyd.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class InsertData {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 26; i++) {
			new InsertData().A(i + "");
		}
	}

	public void A(String rowkey) throws IOException {
		// instantiate Configuration class
		Configuration config = HBaseConfiguration.create();

		// instantiate HTable class
		HTable hTable = new HTable(config, "hr_store");

		// instantiate Put class
		Put p = new Put(Bytes.toBytes(rowkey));

		String col = "principal_30w";

		int num = 10;

		// add values using add() method
		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181124_" + col),
				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181125_" + col),
				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181126_" + col),
				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181127_" + col),
				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));
		p.add(Bytes.toBytes("master"), Bytes.toBytes("20181128_" + col),
				Bytes.toBytes(String.valueOf(Math.floor(Math.random() * num))));

		// save the put Instance to the HTable.
		hTable.put(p);
		System.out.println("data inserted successfully");

		// close HTable instance
		hTable.close();
	}

}