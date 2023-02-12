package com.itheima.MyJUnit;

import org.junit.Test;

public class Demo1 {
    @Test
    public void add(){
        System.out.println(2/0);
        int a = 10;
        int b = 20;
        int sum = a + b;
        System.out.println(sum);
    }
}


// 加入@Test之后，可以不需要有main方法就可以尝试运行上述代码，使得可以在编译阶段就发现bug