package com.jyd.hbase;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class ListTables {

	public static void main(String args[]) throws MasterNotRunningException, IOException {

		Configuration conf = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(conf);

		// getting the list of tables using HBaseAdmin object
		HTableDescriptor[] tableDescriptor = admin.listTables();

		System.err.print(Arrays.toString(tableDescriptor));
	}
}