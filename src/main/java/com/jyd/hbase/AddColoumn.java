package com.jyd.hbase;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * 表增加列族
 * @author aa
 *
 */
public class AddColoumn{

   public static void main(String args[]) throws MasterNotRunningException, IOException{

      // Instantiating configuration class.
      Configuration conf = HBaseConfiguration.create();

      // Instantiating HBaseAdmin class.
      HBaseAdmin admin = new HBaseAdmin(conf);

      // Instantiating columnDescriptor class
      HColumnDescriptor columnDescriptor = new HColumnDescriptor("master");
      
      // Adding column family
      admin.addColumn("hbase_hr_store", columnDescriptor);
      System.out.println("coloumn added");
   }
}