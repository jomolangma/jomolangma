package com.jomolangma.app.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import java.io.IOException;

public class CreateUserGroupTable {
    public static void main(String[] args) throws IOException {
        Configuration HBASE_CONFIG = new Configuration();
        // 与hbase/conf/hbase-site.xml中hbase.zookeeper.quorum配置的值相同
        HBASE_CONFIG.set("hbase.zookeeper.quorum",
                "192.168.11.222,192.168.11.221,192.168.11.220");
        // 与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同
        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");

        Configuration conf = HBaseConfiguration.create(HBASE_CONFIG);

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("user_group_table");
        helper.createTable("user_group_table", "user", "taglibs", "hotspots", "regions", "groups");

        helper.fillUserGroupTable("user_group_table", 1, 1000, 4);
    }

}
