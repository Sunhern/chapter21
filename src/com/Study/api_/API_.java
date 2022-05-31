package com.Study.api_;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author sunhern
 * @version 1.0
 * 演示InetAddress 类的使用
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机InetAddress 对象getLocalHost
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);    //DESKTOP-50NF3I9/10.107.42.58

        //根据指定主机名/域名获取ip 地址对象getByName
        InetAddress host1 = InetAddress.getByName("DESKTOP-50NF3I9");
        System.out.println("host1 = " + host1);    //DESKTOP-50NF3I9/10.107.42.58
        System.out.println(InetAddress.getByName("www.baidu.com"));  //www.baidu.com/110.242.68.3
        System.out.println(InetAddress.getByName("www.hsp.com"));   //www.hsp.com/199.60.103.225

        //获取InetAddress 对象的主机名 getHostName 或者是域名
        String hostName = host1.getHostName();
        System.out.println(hostName);


        //获取InetAddress 对象的地址getHostAddress
        String hostAddress = host1.getHostAddress();   //IP地址
        System.out.println(hostAddress);

    }
}
