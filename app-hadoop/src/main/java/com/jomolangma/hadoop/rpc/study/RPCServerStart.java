package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

/**
 * Created by zhanglijun on 7/11/15.
 */
public class RPCServerStart {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        Server server = new RPC.Builder(conf).setProtocol(MyClientProtocol.class)
                .setInstance(new MyClientProtocolImpl())
                .setBindAddress("localhost").setPort(9000).setNumHandlers(3).build();
        server.start();

    }
}
