package com.itheima.MyTCP.MyPractice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 10086);


        OutputStream os = s.getOutputStream();
        os.write("hello,TCP".getBytes());

        s.shutdownOutput(); // 输出流结束标记

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        os.close();
        s.close();


    }
}
