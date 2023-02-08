package com.itheima.MyInetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Demo1 {
    public static void main(String[] args) throws UnknownHostException {
        String ipAdd = "172.16.2.155";
        InetAddress address = InetAddress.getByName("127.0.0.1");

        String name = address.getHostName();
        String ip = address.getHostAddress();

        System.out.println(name);
        System.out.println(ip);
    }
}
