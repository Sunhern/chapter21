package com.Study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 在本机的9999 端口监听, 等待连接
        // 细节: 要求在本机没有其它服务在监听9999
        // 细节：这个ServerSocket 可以通过accept() 返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在9999 端口监听，等待连接..");

        //2. 当没有客户端连接9999 端口时，程序会阻塞在accept这里, 等待连接
        // 如果有客户端连接，则会返回Socket 对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务器端socket=" + socket.getClass());

        //3. 通过socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
        InputStream inputStream = socket.getInputStream();

        //4. IO 读取
        byte[] buf = new byte[1024];    //设置一个缓冲数组
        int readlen = 0;
        while ((readlen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf, 0,readlen)); //根据读取到的实际长度，显示内容.
        }

        //5.关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
