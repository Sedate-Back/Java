package com.itheima.MyTCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 10086);


        OutputStream os = s.getOutputStream();
        os.write("hello,TCP".getBytes());

        s.close();


    }
}
