package com.jomolangma.hadoop.rpc.study;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

/**
 * Created by zhanglijun on 7/11/15.
 */
public class MyClientProtocolImpl implements MyClientProtocol {

    @Override
    public String echo(String value) throws IOException {
        return value;
    }

    @Override
    public int add(int v1, int v2) throws IOException {
        return v1 + v2;
    }
}
