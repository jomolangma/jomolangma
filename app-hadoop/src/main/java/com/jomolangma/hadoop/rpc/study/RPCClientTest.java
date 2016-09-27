package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by zhanglijun on 8/25/18.
 */
public class RPCClientTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        InetSocketAddress address = new InetSocketAddress("localhost",9000);
        MyTestingProtocol proxy = (MyTestingProtocol) RPC.getProxy(MyTestingProtocol.class, MyTestingProtocol.versionID,
               address ,conf);

        System.out.println(proxy.add(5, 6));
    }
}
