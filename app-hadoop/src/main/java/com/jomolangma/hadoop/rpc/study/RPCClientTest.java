package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by zhanglijun on 7/11/15.
 */
public class RPCClientTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        InetSocketAddress address = new InetSocketAddress("localhost",9000);
        MyClientProtocol proxy = (MyClientProtocol) RPC.getProxy(MyClientProtocol.class,MyClientProtocol.versionID,
               address ,conf);

        System.out.println(proxy.add(5, 6));
    }
}
