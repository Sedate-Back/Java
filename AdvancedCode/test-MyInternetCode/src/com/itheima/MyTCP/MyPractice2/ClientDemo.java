package com.itheima.MyTCP.MyPractice2;

import java.io.*;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10086);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.txt"));

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);

        int b;
        while ((b = bis.read())!= -1){
            bos.write(b);
        }

        socket.shutdownOutput(); // 输出流结束标记

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        os.close();
        socket.close();


    }
}
