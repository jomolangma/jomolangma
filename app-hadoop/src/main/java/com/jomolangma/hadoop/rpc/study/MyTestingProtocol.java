package com.jomolangma.hadoop.rpc.study;

import java.io.IOException;

/**
 * Created by zhanglijun on 8/25/18.
 */
public interface MyTestingProtocol {
    public static final long versionID = 1L;

    String echo(String value) throws IOException;

    int add(int v1, int v2) throws IOException;
}
