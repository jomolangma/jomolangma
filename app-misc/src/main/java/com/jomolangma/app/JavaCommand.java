package com.jomolangma.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhanglijun on 5/22/15.
 */
public class JavaCommand {
    public static void main(String []args) throws IOException, InterruptedException {
        runCommand2();

    }

    private static void runCommand1() throws IOException {
        //Linux系统命令：ls －l
        String command = "ls -l";
        //获取当前系统的环境。
        Runtime rt = Runtime.getRuntime();
        //执行
        Process p = rt.exec(command);
        //获取执行后的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String msg = null;
        //输出。
        while((msg = br.readLine())!=null){
            System.out.println(msg);
        }
        br.close();
    }

    private static void runCommand2() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("javac");
        //int exitValue = p.exitValue();
        int exitValue = p.waitFor();
        System.out.println("Process exitValue="+exitValue);
    }
}