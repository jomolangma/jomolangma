package com.jomolangma.app.hbase.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ZhangLijun on 2015/6/10.
 */
public class HadoopUtils {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
    }
}
