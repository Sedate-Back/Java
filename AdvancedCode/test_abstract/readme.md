### 抽象类

![image-20230102200527158](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230102200527158.png)

抽象法怎么写

1. 需要有需要抽象的方法
2. 抽象方法需要把类也改成抽象类
3. 在方法和类中加入**abstract**关键字

子类继承抽象类，就需要重新编写父类中的抽象方法

![image-20230102201626243](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230102201626243.png)

![image-20230102202153235](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230102202153235.png)

注意事项

1. 抽象类不能创建对象
2. 抽象类中有构造方法
3. 抽象类的子类需要重写抽象方法，才不会报错
4. 抽象类中的方法可以是抽象方法或非抽象方法，但有抽象方法的类，一定是抽象类



### 模板设计模式





### final关键字

![image-20230102205749115](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230102205749115.png)

修饰类：不能被继承

修饰变量：不能被修改

final修饰int这种变量的时候，再次修改就修改不了了

```java
final int a = 10;
a = 20;   // 这句话会报错
```

final修饰调用的变量

```java
class student(){
    private string name;
    public class setname(string name){
        this.name = name;
    }
}
student stu = new student();
final stu.setname = 'zs';

stu = new student(); // 这句话会报错
```



final修饰之后变量名的命名规范需要修改

单词就大写，多个单词就大写后用_连接



### 代码块

1. 静态代码块

   1. 位置：类中方法外定义
   2. 特点:需要通过static关键字修饰，随着类的加载而加载，并且只执行一次
   3. 作用:在类加载的时候做一些数据初始化的操作

2. 构造代码块

   1. 位置:类中方法外定义

   2. 特点:每次构造方法执行的时，都会执行该代码块中的代码，并且在构造方法执行前执行

   3. 作用:将多个构造方法中相同的代码，抽取到构造代码块中，提高代码的复用性

   4. ```java
      public static void main(String[] args) {
          Student stu1 = new Student(); 
      	Student stu2 = new studeent)
      }
      class Student {
      
      System.out.printLn("我是构造代码块");
      
      public Student(){System.out.printIn("空参数构造方法");
      public Student(int a){System.out.println("带参数构造方法...........");
                           }
      ```

3. 局部代码块

   1. 位置:方法中定义

   2. 作用:限定变量的生命周期，及早释放，提高内存利用率

   3. ```java
      public class Test {
          public static void main(String[] args){
              {
                  int a = 10;
                  System.out.println(a);
              }
          System.out.println(a);// 这句话会报错，因为没有a
          }
          
      }
      ```

   4. 