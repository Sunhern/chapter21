package com.Study.Homework.Homework3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 * (1)编写客户端程序和服务器端程序
 * (2)客户端可以输入一个音乐文件名，比如高山流水，服务端收到音乐名后，可以给
 * 客户端返回这个音乐文件，如果服务器没有这个文件，返回一个默认的音乐即可。
 * (3)客户端收到文件后，保存到本地
 * (4)提示：该程序可以使用StreamUtils..java
 * 本质：其实就是指定下载文件的应用。先自己结合老师讲的文件上传来做，
 */
public class Homework03Server {
    public static void main(String[] args) throws Exception {
        //1、监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在8888端口监听....");
        //2、等待客户端连接
        Socket socket = serverSocket.accept();


        //从数据通道读数据
        BufferedReader bR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = bR.readLine();
        System.out.println(s);

        //在服务器上有两个文件, 无名.mp3 高山流水.mp3
        //如果客户下载的是 高山流水 我们就返回该文件，否则一律返回 无名.mp3
        //做判断客户端传进来的是哪首音乐
        String resFilename;
        if(s.equals("高山流水")) {
            resFilename = "D:\\电脑\\桌面\\IDEA_work\\chapter21\\music\\高山流水.mp3";
        }else {
            resFilename = "D:\\电脑\\桌面\\IDEA_work\\chapter21\\music\\无名.mp3";
        }

        //4. 创建一个输入流，读取文件
        BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(resFilename));
        //5. 使用工具类StreamUtils ，读取文件到一个字节数组
        byte[] data = StreamUtils.streamToByteArray(bIS);   //把服务器本地文件转成字节数组

        //6.把服务器本地文件写入到数据通道
        BufferedOutputStream bOS = new BufferedOutputStream(socket.getOutputStream());
        //7. 写入到数据通道，返回给客户端
        bOS.write(data);
        bIS.close();
        socket.shutdownOutput();
        bOS.close();

        //8.关闭流
        bR.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端退出...");

    }
}
