package com.Study.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author sunhern
 * @version 1.0
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. 创建一个DatagramSocket 对象，A准备在9999 接收数据
        DatagramSocket dSocket = new DatagramSocket(9999);

        //2. 构建一个DatagramPacket 对象，准备接收数据
        // 在前面讲解UDP 协议时，老师说过一个数据包最大64k
        byte[] buf = new byte[64];
        DatagramPacket dPacket = new DatagramPacket(buf, buf.length);

        //3. 调用接收方法, 将通过网络传输的DatagramPacket 对象
        // 填充到packet 对象
        //老师提示: 当有数据包发送到本机的9999 端口时，就会接收到数据
        // 如果没有数据包发送到本机的9999 端口, 就会阻塞在dSocket.receive(dPacket);等待信息的接受.
        System.out.println("接收端A 等待接收数据..");
        dSocket.receive(dPacket);

        //4. 可以把packet 进行拆包，取出数据，并显示.
        int length = dPacket.getLength();    //实际接收到的数据字节长度
        byte[] data = dPacket.getData();      //接收到数据
        String s = new String(data, 0, length);   //将字节数组转换成字符串
        System.out.println(s);

        //A回复B
        //先构建
        //===回复信息给B 端
        //将需要发送的数据，封装到DatagramPacket 对象
        byte[] data1 = "A说： 好的  B   ，明天见".getBytes();
        //说明: 封装的DatagramPacket 对象data 内容字节数组, data.length , 主机(IP) , 端口
        DatagramPacket dPacket1 = new DatagramPacket(data1, data1.length, InetAddress.getByName("10.107.42.58"), 9998);
        dSocket.send(dPacket1);//发送


        //关闭资源
        dSocket.close();
        System.out.println("A端退出");
    }
}
