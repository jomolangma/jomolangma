package com.jomolangma.app.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.client.HTable;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zhanglijun on 1/7/15.
 */
public class HBaseApiTest {
    @Test
    public void htableTest() throws IOException {
        Configuration hbase_config = new Configuration();
        Configuration conf = HBaseConfiguration.create(hbase_config);

        HTable userInfoTable = new HTable(conf, "user_info_table");
        HRegionLocation regionLocation = userInfoTable.getRegionLocation("0");

        System.out.println(regionLocation.getHostname());
        System.out.println(regionLocation.getPort());

        HRegionInfo regionInfo = regionLocation.getRegionInfo();
        System.out.println(regionInfo.getVersion());


        System.out.println("complete");
    }
}
