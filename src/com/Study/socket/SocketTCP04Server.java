package com.Study.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sunhern
 * @version 1.0
 * 字符读取流缓冲区-读一行的方法readLine()
 * 字符读取流缓冲区:
 * 该缓冲区提供了一个一次读一行的方法readLine()，方便与对文本数据的获取。
 * 当返回null时，表示读到文件末尾
 */
@SuppressWarnings({"all"})
public class SocketTCP04Server {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 在本机 的9999端口监听, 等待连接
        //   细节: 要求在本机没有其它服务在监听9999
        //   细节：这个 ServerSocket 可以通过 accept() 返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在9999端口监听，等待连接..");
        //2. 当没有客户端连接9999端口时，程序会 阻塞, 等待连接
        //   如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket =" + socket.getClass());

        //3. 通过socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
        InputStream inputStream = socket.getInputStream();
        //4. IO读取, 使用字符流, 老师使用 InputStreamReader 将 inputStream 转成字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        //然后我就去查了一下inputStream.read(byte[])这个方法的说明，
        // 如果read读取到流的结尾，才会返回-1，
        // 所以由于客户端管道没有执行socket.shutdownOutput()方法
        //导致管道中没有结束符，所以服务端一直读取不到结束，
        // 但是管道也没有数据所以卡在了while循环这里，
        // 那么这里的解决方法就是到客户端的输出流循环完毕后，
        // 执行一下socket.shutdownOutput()方法，此方法告诉服务端，此管道已结束数据传输

        /**
         * BufferedReader提供了按行读取方法：String readLine()
         * 连续读取若干字符，直到读取到换行符为止
         * 并将换行符之间读取到的字符以一个字符串返回
         * 若返回值为NULL，则表示读取到末尾。
         * 注意：该字符串不包含最后的换行符。
         *
         * 下面这段代码会发现写数据时每次都成功，而读数据侧却一直没有数据输出(除非把流关掉)。
         * 经过折腾，原来这里面有几个大问题需要理解：
         *
         * 误以为readLine()是读取到没有数据时就返回null(因为其它read方法当读到没有数据时返回-1)，
         * 而实际上readLine()是一个阻塞函数，当没有数据读取时，就一直会阻塞在那，而不是返回null；
         * 因为readLine()阻塞后，System.out.println(message)这句根本就不会执行到，
         * 所以在接收端就不会有东西输出。要想执行到System.out.println(message)，
         *
         * 1、一个办法是发送完数据后就关掉流，这样readLine()结束阻塞状态，而能够得到正确的结果，
         * 但显然不能传一行就关一次数据流；调用socket.shutdownOutput();方法
         *
         * 2、另外一个办法是把System.out.println(message)放到while循环体内就可以。
         * readLine()只有在数据流发生异常或者另一端被close()掉时，才会返回null值。
         *
         *
         * 小结，使用readLine()一定要注意：
         * 读入的数据要注意有/r   或/n   或/r/n
         * 没有数据时会阻塞，在数据流异常或断开时才会返回null
         * 使用socket之类的数据流时，要避免使用readLine()，以免为了等待一个换行/回车符而一直阻塞
         */

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        //5. 获取socket相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        //使用字符输出流的方式回复信息
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client 字符流\n");
        bufferedWriter.write("hello client 字符流\n");
        bufferedWriter.write("hello client 字符流\n");
        //bufferedWriter.newLine();// 插入一个换行符，表示回复内容的结束
        bufferedWriter.flush();//注意需要手动的flush
        //socket.shutdownOutput();


        //6.关闭流和socket
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();//关闭

    }
}

