package com.jomolangma.hadoop.rpc.study;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Created by ZhangLijun on 7/15/16.
 */
public class RPCStudy {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URI uri = new URI("qjournal://node1:8485;node2:8485;node3:8485/mycluster");
        String path = uri.getPath();

        System.out.println(path);
        System.out.println(uri.getAuthority());
        System.out.println(uri.getScheme());

    }
}
