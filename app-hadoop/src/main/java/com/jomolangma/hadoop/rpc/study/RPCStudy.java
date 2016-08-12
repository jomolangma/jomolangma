package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ha.NodeFencer;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.tools.DFSZKFailoverController;

import java.io.IOException;

/**
 * Created by ZhangLijun on 7/15/16.
 */
public class RPCStudy {
    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
//        DFSClient dfsClient = new DFSClient(conf);

        HdfsConfiguration hdfsConfiguration = new HdfsConfiguration(conf);
//        NodeFencer.create(hdfsConfiguration, DFSConfigKeys.DFS_HA_FENCE_METHODS_KEY);

        DFSZKFailoverController.create(hdfsConfiguration);
    }
}
