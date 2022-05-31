package com.Study.Homework.Homework1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author sunhern
 * @version 1.0
 */
public class Homework01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bR = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入你的问题、、、");
            String question = scanner.next();
            bW.write(question);
            bW.newLine();
            bW.flush();

            String s = bR.readLine();
            System.out.println(s);

            if(s.equals("你说啥呢")){
                System.out.println("我只能回答name和hobby问题，其他问题我回答不了");
                break;
            }
        }

        //关闭流
        bR.close();
        bW.close();
        socket.close();

    }
}
