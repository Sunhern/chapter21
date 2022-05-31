package com.Study.Homework.Homework3;

import com.Study.upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author sunhern
 * @version 1.0
 */
public class Homework03Client {
    public static void main(String[] args) throws Exception {
        //创建Socket
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);

        //向数据通道写数据，发送音乐名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要下载的音乐名字：");
        String query = scanner.next();

        BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bW.write(query);
        bW.newLine();
        bW.flush();

        //4.从数据通道中读取数据，
        BufferedInputStream bIS = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bIS);
        //写入到本地文件
        //5. 得到一个输出流，准备将 bytes 写入到磁盘文件
        //比如你请求的是 高山流水 => 下载的就是 高山流水.mp3
        //    请求的是 其他 => 下载的就是 无名.mp3
        String desPath = "D:\\电脑\\桌面\\IDEA_work\\chapter21\\src\\com\\Study\\Homework\\Homework3\\"+ query +".mp3";
        BufferedOutputStream bOS = new BufferedOutputStream(new FileOutputStream(desPath));
        bOS.write(bytes);
        bOS.close();
        System.out.println("音乐"+query+"下载成功");
        bIS.close();


        //关闭流
        bW.close();
        socket.close();
        System.out.println("客户端下载完毕，正确退出..");

    }
}
