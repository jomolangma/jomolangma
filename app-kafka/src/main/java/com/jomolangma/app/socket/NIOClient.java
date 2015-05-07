package com.jomolangma.app.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangLijun on 2015/5/5.
 */
public class NIOClient {
    public static void main(String[] args) {
        while(true){
            try {
                SocketChannel socketChannel= SocketChannel.open(new InetSocketAddress("127.0.0.1", 12112));
                socketChannel.configureBlocking(false);
                // 打开并注册选择器到信道
                Selector selector = Selector.open();
                socketChannel.register(selector, SelectionKey.OP_READ);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put("versionTest".getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

                if (selector.select() > 0) {
                    // 遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {

                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            // 使用NIO读取Channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();
                            sc.read(buffer);
                            buffer.flip();

                            // 将字节转化为为UTF-16的字符串
                            String receivedString= new String(buffer.array());

                            if (receivedString.equals("versionTest")){
                                System.out.println("version check passed");
                            }


                            // 为下一次读取作准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }

                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                    }
                }

                buffer.flip();
                buffer.put("Test".getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

                while (selector.select() > 0) {
                    // 遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {

                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            // 使用NIO读取Channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();
                            sc.read(buffer);
                            buffer.flip();

                            String receivedString= new String(buffer.array());
                            System.out.println(receivedString);
                            buffer.clear();
                            buffer.put(receivedString.getBytes());
                            buffer.flip();

                            sc.write(buffer);

                            // 为下一次读取作准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }

                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
