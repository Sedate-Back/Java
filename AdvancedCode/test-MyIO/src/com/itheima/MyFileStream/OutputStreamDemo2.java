package com.itheima.MyFileStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamDemo2 {
    // 处理异常
    // try ... catch ... finally
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("a.txt");
            fos.write("hello".getBytes());

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(fos !=null){
                try{
                    fos.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
