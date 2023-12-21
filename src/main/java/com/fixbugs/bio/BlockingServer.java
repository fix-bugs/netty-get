package com.fixbugs.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Blocking Server
 *
 * @author fix-bugs
 * @date 2023/12/20 17:06
 */
public class BlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        while (true) {
            System.out.println("等待客户端连接...");
            Socket clientSocket = server.accept();
            System.out.println("新客户端已连接。当前线程ID：" + Thread.currentThread().getId() + "，线程名称：" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            try(InputStream inputStream = clientSocket.getInputStream();) {
                while (true) {
                    int read = inputStream.read(bytes);
                    if (read != -1) {
                        System.out.println("客户端请求数据：" + new String(bytes, 0, read));
                    } else {
                        break;
                    }
                }
            } finally {
                System.out.println("关闭客户端。当前线程ID：" + Thread.currentThread().getId() + "，线程名称：" + Thread.currentThread().getName());
                clientSocket.close();
            }
        }
    }
}
