package com.Study.Homework.Homework2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author sunhern
 * @version 1.0
 */
public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket = new DatagramSocket(9999);
        System.out.println("接收端正在9999端口监听。。。。");

        //接收B发来的问题
        byte[] buf = new byte[1024];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);
        dSocket.receive(dPacket);


        int length = dPacket.getLength();
        byte[] data = dPacket.getData();
        String s = new String(data, 0, length);
        System.out.println(s);
        if(s.equals("B端说四大名著是哪些？")){
            byte[] buf1 = "A端说四大名著是《红楼梦》《三国演义》《水浒传》《西游记》".getBytes();
            DatagramPacket dPacket1 = new DatagramPacket(buf1, buf1.length, InetAddress.getByName("10.107.42.58"), 8888);
            dSocket.send(dPacket1);
        }else {
            byte[] buf1 = "A端说what?".getBytes();
            DatagramPacket dPacket1 = new DatagramPacket(buf1, buf1.length, InetAddress.getByName("10.107.42.58"), 8888);
            dSocket.send(dPacket1);
        }

        dSocket.close();


    }
}
