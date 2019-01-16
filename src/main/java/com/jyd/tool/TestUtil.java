package com.jyd.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class TestUtil {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(new TestUtil().new A());
			t.start();
		}
	}

	class A extends Thread {
		@Override
		public void run() {
			// ApplicationContext context = new
			// ClassPathXmlApplicationContext("applicationContext-client.xml");
			// UserServiceClient us = context.getBean(UserServiceClient.class);
			// us.invoke();
			try {
				Configuration config = HBaseConfiguration.create();
				HTable table = new HTable(config, "hr_store");

				// instantiate Get class
				Get g = new Get(Bytes.toBytes("row2001"));

				// get the Result object
				Result result = table.get(g);

				// read values from Result class object
				byte[] name = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("name"));
				byte[] age = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("age"));
				byte[] city = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("city"));
				byte[] country = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("country"));
				byte[] email = result.getValue(Bytes.toBytes("master"), Bytes.toBytes("email"));

				System.out.println("name: " + Bytes.toString(name));
				System.out.println("age: " + Bytes.toString(age));
				System.out.println("city: " + Bytes.toString(city));
				System.out.println("country: " + Bytes.toString(country));
				System.out.println("email: " + Bytes.toString(email));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println(this.getName());
		}
	}
}
