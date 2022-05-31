package com.Study.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author sunhern
 * @version 1.0
 * 发送端B ====> 也可以接收数据
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket 对象，B准备在9998 端口接收数据
        DatagramSocket dSocket = new DatagramSocket(9998);

        //2. 将需要发送的数据，封装到DatagramPacket 对象
        byte[] data = "B 说：hello A  明天吃火锅".getBytes();
        //说明: 封装的DatagramPacket 对象data (真正要发送的内容 字节数组, data.length , InetAddress.getByName() 主机(IP) , 端口号)
        DatagramPacket dPacket = new DatagramPacket(data, data.length, InetAddress.getByName("10.107.42.58"), 9999);
        dSocket.send(dPacket);


        //B端接受A的信息
        //3.=== 接收从A 端回复的信息
        //(1) 构建一个DatagramPacket 对象，准备接收数据
        // 在前面讲解UDP 协议时，老师说过一个数据包最大64k
        byte[] data1 = new byte[64];
        DatagramPacket dPacket1 = new DatagramPacket(data1, data1.length);

        //(2) 调用接收方法, 将通过网络传输的DatagramPacket 对象
        // 填充到packet 对象
        //老师提示: 当有数据包发送到本机的9998 端口时，就会接收到数据
        // 如果没有数据包发送到本机的9998 端口, 就会阻塞等待.
        dSocket.receive(dPacket1);

        //(3) 可以把packet 进行拆包，取出数据，并显示.
        int length1 = dPacket1.getLength();
        byte[] data11 = dPacket1.getData();
        String s = new String(data11,0,length1);
        System.out.println(s);

        dSocket.close();
        System.out.println("B端退出");
    }
}
