package com.jyd.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class DeleteData {

	public static void main(String[] args) throws IOException {

		Configuration conf = HBaseConfiguration.create();
		HTable table = new HTable(conf, "employee");

		// instantiate Delete class
		Delete delete = new Delete(Bytes.toBytes("row2001"));
		delete.deleteColumn(Bytes.toBytes("personal"), Bytes.toBytes("age"));
		delete.deleteFamily(Bytes.toBytes("contactinfo"));

		// delete the data
		table.delete(delete);

		table.close();
		System.out.println("data deleted successfully.....");
	}
}