## 内部类

内部类:就是在一个类中定义一个类。举例:在一个A类的内部定义一个B类就被称为内部类

```java
public class outer{
    public class Inner{
        
    }
}
```

```java
package com.itheima.test1;

import javax.lang.model.type.IntersectionType;

public class Tset1Inner {
    public static void main(String[] args) {
        
        Outer.Inner inner = new Outer().new Inner();
        inner.show();
        System.out.println(inner.num);
    }
}

class Outer{
    class Inner{
        int num = 10;
        
        public void show(){
            System.out.println("inner --- show");
        }
    }
}

```



### 成员内部类

成员内部类，也属于(成员)，既然是成员就可以被一些修饰符所修饰

- private
- static

```java
// private
package com.itheima.test2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test2Innerclass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();
    }
}

class Outer{
    private class Inner{
        public void show(){
            System.out.println(" inner --- show");
        }
    }
    public void method(){
        Inner inner = new Inner();
        inner.show();
    }
}
```

```java
// static
package com.itheima.test2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test2Innerclass {
    public static void main(String[] args) {
       
        Outer2.Inner oi = new Outer2.Inner();
        oi.show();

        Outer2.Inner.method();
    }
}

class Outer2{
    static class Inner{
        public void show(){
            System.out.println(" inner --- show");
        }

        public static void method(){
            System.out.println("inner --- method");
        }
    }
}
```

### 局部内部类

局部内部类是在方法中定义的类，所以外界是无法直接使用，需要在方法内部创建对象并使用该类可以直接访问外部类的成员，也可以访问方法内的局部变量

```java
package com.itheima.test3;

public class Test3Innerclass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.method();
    }
}
class Outer{
    public void method(){

        class Inner{
            public void show(){
                System.out.println("show ---");
            }
            
        }
        Inner inner = new Inner();
        inner.show();
    }
}

```

### 匿名内部类

- 概述：匿名内部类本质上是一个特殊的局部内部类(定义在方法内部）

- 前提：需要存在一个接口或类

- 格式：

  - ```java
    new 类名/接口名(){
        重写方法；
    }；
        
    new Inner(){
        public void show(){
            
        }
    }
    ```

  

