package com.jomolangma.hadoop.compress;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * Created by ZhangLijun on 2015/2/15.
 */
public class HadoopCompressTest {
    public static void compress(String method) throws IOException, ClassNotFoundException {
        File fileIn = new File("W:\\GitHub\\jomolangma\\app-hadoop\\src\\main\\resources\\README.txt");
        InputStream in = new FileInputStream(fileIn);

        Class<?> codecClass = Class.forName(method);

        Configuration conf = new Configuration();
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass,conf);
        File fileOut = new File("W:\\GitHub\\jomolangma\\app-hadoop\\src\\main\\resources\\README.txt" + codec.getDefaultExtension());
        fileOut.delete();

        OutputStream out = new FileOutputStream(fileOut);
        CompressionOutputStream cout = codec.createOutputStream(out);

        IOUtils.copy(in,out,4096);

        in.close();
        cout.close();

    }

    public static void main(String[] args){
        try {
            compress("org.apache.hadoop.io.compress.BZip2Codec");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
