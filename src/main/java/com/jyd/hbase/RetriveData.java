package com.jyd.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class RetriveData {

	public static void main(String[] args) throws IOException, Exception {

		Configuration config = HBaseConfiguration.create();
		HTable table = new HTable(config, "hbase_hr_store");

		// instantiate Get class
		Get g = new Get(Bytes.toBytes("18"));

		// get the Result object
		Result result = table.get(g);

		String col = "loan_contract_num";

		// read values from Result class object
		byte[] name = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("20181205_" + col));
		// byte[] age = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("age"));
		// byte[] city = result.getValue(Bytes.toBytes("master"),
		// Bytes.toBytes("city"));
		// byte[] country = result.getValue(Bytes.toBytes("master"),
		// Bytes.toBytes("country"));
		// byte[] email = result.getValue(Bytes.toBytes("master"),
		// Bytes.toBytes("email"));
		System.out.println(col + ": " + Bytes.toString(name));
		// System.out.println("age: " + Bytes.toString(age));
		// System.out.println("city: " + Bytes.toString(city));
		// System.out.println("country: " + Bytes.toString(country));
		// System.out.println("email: " + Bytes.toString(email));
	}
}