package com.itheima.MyTCP.MyPractice2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);

        Socket accept = ss.accept();

        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("a_copybyTCP.txt"));


        int b;
        while ((b = bis.read())!=-1){
            bos.write(b);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bw.write("上传成功！");
        bw.newLine();
        bw.flush();

        bw.close();

        accept.close();
        ss.close();

    }
}
