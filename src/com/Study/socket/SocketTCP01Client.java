package com.Study.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 * 客户端，发送"hello, server" 给服务端
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 连接服务端(ip , 端口）
        //解读: 连接本机的9999 端口, 如果连接成功，返回Socket 对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket 返回=" + socket.getClass());

        //2. 连接上后，生成Socket, 通过socket.getOutputStream()
        // 得到和socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3. 通过输出流，写入数据到数据通道
        outputStream.write("hello，Sever".getBytes());  //getBytes（）是将一个字符串转化为一个字节数组。写入此输出流。

        //4. 关闭流对象和socket, 必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");

    }
}