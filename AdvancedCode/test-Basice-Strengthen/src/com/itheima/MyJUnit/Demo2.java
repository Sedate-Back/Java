package com.itheima.MyJUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Demo2 {



    @After
    public void after(){
        System.out.println("after");
    }



    @Test
    public void test(){
        System.out.println("test");
    }

    @Before
    public void before(){
        System.out.println("before");
    }

}

// @Before 会在@Test之前运行， After会在之后运行，无论代码环境怎么编译