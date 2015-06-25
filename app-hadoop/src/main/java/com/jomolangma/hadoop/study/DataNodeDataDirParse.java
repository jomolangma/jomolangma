package com.jomolangma.hadoop.study;

import org.apache.hadoop.hdfs.server.datanode.StorageLocation;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by ZhangLijun on 2015/6/9.
 */
public class DataNodeDataDirParse {
    public static void main(String[] args) throws IOException {

        String valueString = "file:///data/dfs/dn,file:///data1,file:///data2,file:///data3,file:///data4,file:///data5,file:///data6";

        Collection<String> dirs =  StringUtils.getTrimmedStringCollection(valueString);

        System.out.println(dirs);

        for (String dir:dirs){
            StorageLocation.parse(dir);
        }
    }
}
