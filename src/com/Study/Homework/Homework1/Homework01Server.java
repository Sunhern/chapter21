package com.Study.Homework.Homework1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author sunhern
 * @version 1.0
 * (1)使用字符流的方式，编写一个客户端程序和服务器端程序，
 * (2)客户端发送"name",服务器端接收到后，返回"我是nova",nova是你自己的名字
 * (3)客户端发送"hobby",服务器端接收到后，返回"编写java程序”
 * (4)不是这两个问题，回复"你说啥呢”
 * 问题：目前，我们只能问一次，就退出了，怎么可以问多次？->while->聊天
 */
public class Homework01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器端在9999号 端口正在监听。。。。");
        Socket socket = serverSocket.accept();

        BufferedReader bR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true){
            String question = bR.readLine();
            if(!(question.equals("name")||question.equals("hobby"))){
                bW.write("你说啥呢");
                bW.newLine();
                bW.flush();
                break;
            }
            if(question.equals("name")){
                bW.write("我是孙贺");
                bW.newLine();
                bW.flush();
            }
            if(question.equals("hobby")){
                bW.write("编写java程序");
                bW.newLine();
                bW.flush();
            }
        }


        //关闭流
        bR.close();
        bW.close();
        socket.close();
        serverSocket.close();


    }
}
