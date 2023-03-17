package com.itheima;

import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class App {

    @Autowired
    private AccountService accountService;

    @Test
    public void testTransfer() throws IOException{
        accountService.transfer("Tom", "Jerry", 100d);
    }

}
