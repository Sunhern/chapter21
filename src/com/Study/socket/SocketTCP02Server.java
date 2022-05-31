package com.Study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 */
public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {
        //1、监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端，在8888 端口监听，等待连接..");
        //2.等待连接，程序会阻塞在这里
        Socket socket = serverSocket.accept();
        //3.读出客户端发过来的信息  使用socket.getInputStream();
        InputStream inputStream = socket.getInputStream();
        //4.IO读取
        byte[] buf = new byte[1024];
        int readlen = 0;
        while((readlen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readlen));
        }

        //5..服务器向客户端发送信息  获取socket 相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("你好啊，客户端".getBytes());
        // 设置结束标记
        socket.shutdownOutput();

        //6.关闭流
        serverSocket.close();
        outputStream.close();
        inputStream.close();
        socket.close();

    }
}
