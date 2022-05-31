package com.Study.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 */
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        //1、指定服务器ip和端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //2、连接上后，生成Socket, 通过socket.getOutputStream() 得到和socket 对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到数据通道
        outputStream.write("你还好吗，服务器".getBytes());
        // 设置结束标记
        socket.shutdownOutput();

        //4.从数据通道读出数据
        byte[] buf = new byte[1024];
        int readlen = 0;
        InputStream inputStream = socket.getInputStream();
        while ((readlen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readlen));
        }
        System.out.println("客户端退出、、、、");

        //5.关闭流
        socket.close();
        inputStream.close();
        outputStream.close();

    }
}
