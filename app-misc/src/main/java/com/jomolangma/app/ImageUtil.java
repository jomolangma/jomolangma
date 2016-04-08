package com.jomolangma.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by ZhangLijun on 4/8/16.
 */
public class ImageUtil {
    /*
 * 根据图片的网络地址，将图片转化成byte[]
 */
    public byte[] getImageToBytes(String imgPath) {

        byte[] bytes = null;

        imgPath = "http://127.0.0.1:8080/upload/"+ imgPath;
        System.out.println(imgPath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            //创建URL
            URL url = new URL(imgPath);
            //得到连接
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            //得到连接地址的输入流
            InputStream in = urlConn.getInputStream();

            int size;
            //缓冲值
            bytes = new byte[1024];
            if(in != null){
                //循环读输入流至read返回-1为止，并写到缓存中
                while((size=in.read(bytes)) != -1){
                    out.write(bytes, 0, size);
                }
            }
            out.close();//关闭输出流
            in.close();//关闭输入流
            urlConn.disconnect();//断开连接

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
    /*
     * 将byte[]数组转成image存到本地
     */
    public void bytesToImgSave(byte[] b,String imgFileType) throws Exception{
        //UUID序列号作为保存图片的名称
        String name = UUID.randomUUID().toString();

        File f = new File("E:\\upload");

        //是否存在该目录，如果不存在则创建
        if(!f.isDirectory()){
            f.mkdirs();
        }

        OutputStream os = new FileOutputStream(new File(f.getAbsolutePath()+"\\"+name+"."+imgFileType));
        os.write(b);
        os.flush();
        os.close();
    }
}
