package com.Study.Homework.Homework2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author sunhern
 * @version 1.0
 * (1)编写一个接收端A,和一个发送端B,使用UDP协议完成
 * (2)接收端在8888端口等待接收数据(receive)
 * (3)发送端向接收端发送数据"四大名著是哪些”
 * (4)接收端接收到发送端发送的问题后，返回"四大名著是<<红楼梦>>…”，否则返回
 * what?
 * (⑤)接收端和发送端程序退出
 */
public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket = new DatagramSocket(8888);

        //发送问题
        Scanner scanner = new Scanner(System.in);
        String question = scanner.next();
        byte[] data = question.getBytes();
        DatagramPacket dPacket = new DatagramPacket(data, data.length, InetAddress.getByName("10.107.42.58"), 9999);
        dSocket.send(dPacket);

        //接受答案
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        dSocket.receive(datagramPacket);

        int lenth = datagramPacket.getLength();
        byte[] data1 = datagramPacket.getData();
        String s = new String(data1,0,lenth);
        System.out.println(s);

        dSocket.close();


    }
}
