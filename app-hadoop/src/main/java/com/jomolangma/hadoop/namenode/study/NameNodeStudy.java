package com.jomolangma.hadoop.namenode.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSUtil;
import org.apache.hadoop.hdfs.HAUtil;
import org.apache.hadoop.hdfs.HdfsConfiguration;

/**
 * Created by ZhangLijun on 2015/10/9.
 */
public class NameNodeStudy {
        public static void main(String[] args){
        Configuration conf = new HdfsConfiguration();
        String nsId = DFSUtil.getNamenodeNameServiceId(conf);
        String namenodeId = HAUtil.getNameNodeId(conf, nsId);

        System.out.println("nsId:" + nsId);
        System.out.println("namenodeId:" + namenodeId);
    }
}
