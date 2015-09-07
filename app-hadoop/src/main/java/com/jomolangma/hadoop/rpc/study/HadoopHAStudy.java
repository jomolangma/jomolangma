package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSUtil;
import org.apache.hadoop.hdfs.HdfsConfiguration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by zhanglijun on 9/6/15.
 */
public class HadoopHAStudy {

    public static void main(String[] args) throws IOException {
        Configuration conf = new HdfsConfiguration();

        Map<String, Map<String, InetSocketAddress>> newAddressMap = DFSUtil
                .getNNServiceRpcAddressesForCluster(conf);

        System.out.println();
    }
}
