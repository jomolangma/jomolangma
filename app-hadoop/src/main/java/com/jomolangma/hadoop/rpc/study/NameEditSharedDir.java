package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.server.namenode.FSNamesystem;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ZhangLijun on 2015/9/22.
 */
public class NameEditSharedDir {
    public static void main(String[] args) throws IOException {
        Configuration conf = new HdfsConfiguration();

        Collection<URI> nameDirsToFormat = FSNamesystem.getNamespaceDirs(conf);
        List<URI> sharedDirs = FSNamesystem.getSharedEditsDirs(conf);
        List<URI> dirsToPrompt = new ArrayList<URI>();
        dirsToPrompt.addAll(nameDirsToFormat);
        dirsToPrompt.addAll(sharedDirs);
        List<URI> editDirsToFormat =
                FSNamesystem.getNamespaceEditsDirs(conf);

        System.out.println(nameDirsToFormat);
    }
}
