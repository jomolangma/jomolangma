package com.jomolangma.hadoop.rpc.study;

import java.io.IOException;

/**
 * Created by zhanglijun on 8/25/18.
 */
public class MyTestingProtocolImpl implements MyTestingProtocol {

    @Override
    public String echo(String value) throws IOException {
        return value;
    }

    @Override
    public int add(int v1, int v2) throws IOException {
        return v1 + v2;
    }
}
