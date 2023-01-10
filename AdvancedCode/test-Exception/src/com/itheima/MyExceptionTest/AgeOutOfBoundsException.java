package com.itheima.MyExceptionTest;

public class AgeOutOfBoundsException  extends RuntimeException{
    public AgeOutOfBoundsException(){

    }

    public AgeOutOfBoundsException(String message){
        super(message);
    }
}
