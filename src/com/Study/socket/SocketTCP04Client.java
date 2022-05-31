package com.Study.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class SocketTCP04Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 连接服务端 (ip , 端口）
        //解读: 连接本机的 9999端口, 如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 socket返回=" + socket.getClass());
        //2. 连接上后，生成Socket, 通过socket.getOutputStream()
        //   得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3. 通过输出流，写入数据到 数据通道, 使用字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //写数据
        //在写数据是一定要加上"\n"或"\r",不然服务器读数据readline因为没有换行符会一直处于阻塞状态
        bufferedWriter.write("hello, server 字符流\n");
        bufferedWriter.write("hello, server 字符流\n");
        bufferedWriter.write("hello, server 字符流\n");
        bufferedWriter.write("hello, server 字符流\n");
        //bufferedWriter.newLine();//插入一个换行符\n，表示写入的内容结束, 注意，要求对方使用readLine()!!!!
        bufferedWriter.flush();// 如果使用的字符流，需要手动刷新，否则数据不会写入数据通道 输出流不会关闭还可以继续写入数据
        bufferedWriter.write("hello, server 字符流\n");
        bufferedWriter.write("hello, server 字符流\n");
        bufferedWriter.flush();
        socket.shutdownOutput();  //使用socket.shutdownOutput(); 之后就不能在写入数据了 禁用此套接字的输出流

        //4. 获取和socket关联的输入流. 读取数据(字符)，并显示
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //String s = bufferedReader.readLine();
        //System.out.println(s);

        String line;
        while ((line = bufferedReader.readLine())!=null){
            System.out.println(line);
        }


        //5. 关闭流对象和socket, 必须关闭
        //注意：直接关闭流将会导致socket关闭，只能通过shutdownOutput/input的方式关闭流
        //另外，流关闭之后，相当于关闭底层的连接，除非新new个socket，否则和客户端的连接相当于断开
        bufferedReader.close();//关闭外层流
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出.....");
    }
}

