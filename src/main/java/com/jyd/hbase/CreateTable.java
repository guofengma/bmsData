package com.jyd.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class CreateTable {

	public static void main(String[] args) throws IOException {

		Configuration conf = HBaseConfiguration.create();

		// instantiating HbaseAdmin class by passing on configuration class
		HBaseAdmin admin = new HBaseAdmin(conf);

		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("hbase_cus_contract"));

		// adding column families to table descriptor
		tableDescriptor.addFamily(new HColumnDescriptor("mater"));

		// creating the table
		admin.createTable(tableDescriptor);
		System.out.println(" Table created ");
	}
}