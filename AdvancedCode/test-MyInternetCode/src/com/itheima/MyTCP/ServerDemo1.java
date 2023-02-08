package com.itheima.MyTCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(10086);

        Socket s = ss.accept();
        InputStream is = s.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        String data = new String(bytes, 0 ,len);
        System.out.println(data);

        s.close();
        ss.close();

    }
}
