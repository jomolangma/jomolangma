package com.jomolangma.app.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;

public class CreateUserGroupTable {
    public static void main(String[] args) throws IOException {
        Configuration HBASE_CONFIG = new Configuration();
        Configuration conf = HBaseConfiguration.create(HBASE_CONFIG);

//        HBaseHelper helper = HBaseHelper.getHelper(conf);
//        helper.dropTable("testtable2");
//        helper.createTable("testtable2", "colf");

        HTable userInfoTable = new HTable(conf,"user_info_table");
        HRegionLocation regionLocation = userInfoTable.getRegionLocation("0");

        System.out.println(regionLocation.getHostname());
        System.out.println(regionLocation.getPort());

        HRegionInfo regionInfo = regionLocation.getRegionInfo();
        System.out.println(regionInfo.getVersion());



        System.out.println("complete");
    }

}
