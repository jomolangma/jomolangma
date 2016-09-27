package com.jomolangma.hadoop.namenode.study;

import com.google.common.base.Preconditions;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.server.namenode.FSNamesystem;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by ZhangLijun on 2015/10/9.
 */
public class NameNodeStudy {
    public static void main(String[] args) throws IOException {
        Configuration conf = new HdfsConfiguration();
//        String nsId = DFSUtil.getNamenodeNameServiceId(conf);
//        String namenodeId = HAUtil.getNameNodeId(conf, nsId);
//
//        System.out.println("nsId:" + nsId);
//        System.out.println("namenodeId:" + namenodeId);

        List<URI> list = FSNamesystem.getNamespaceEditsDirs(conf);
        for (URI uri : list) {
            System.out.println(uri);
            String authority = uri.getAuthority();
            System.out.println(authority);

            String path = uri.getPath();
            Preconditions.checkArgument(path != null && !path.isEmpty(),
                    "Bad URI '%s': must identify journal in path component",
                    uri);
            String journalId = path.substring(1);
        }
    }
}
