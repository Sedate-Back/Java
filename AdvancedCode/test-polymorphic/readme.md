## 多态

### 概述

同一个对象，在不同时刻表现出来的不同形态

举例:猫
我们可以说猫是猫: 

​			猫 cat = new 猫();

我们也可以说猫是动物:

​			动物 animal = new 猫();

这里猫在不同的时刻表现出来了不同的形态，这就是多态

### 多态的前提和体现

- 有继承/实现关系
- 有方法重写
- 有父类引用指向子类对象

#### 实例

```java
package com.itheima.test1;

public class Test1Polymorphic {
    // 多态的前提：1. 要有继承或实现关系
    //           2. 要有方法的重写
    //           3. 要有父类引用，指向子类对象
    public static void main(String[] args) {
        // 当前事物是一只猫
        Cat cat = new Cat();
        // 当前事物是动物
        Animal animal = new Cat();
    }
}
class Animal{
    public void eat() {
        System.out.println("动物吃饭");
    }
}
 class Cat extends Animal{
    @Override
    public void eat(){
        System.out.println("猫吃鱼");
    }
}
```

### 多态中成员访问特点

- 构造方法:同继承一样，子类会通过 super访问父类构造方法
- 成员变量:编译看左边(父类)，执行看左边(父类)
- 成员方法:编译看左边(父类)，执行看右边(子类)

为什么成员变量和成员方法的访问不一样呢?

- 因为成员方法有重写，而成员变量没有

#### 实例

```java
package com.itheima.test2;

public class Test1Polymorphic {
    public static void main(String[] args) {
        Fu fu = new Zi();
        System.out.println(fu.num);
        f.method();
    }
}

class Fu{
    int num = 10;
    
    public void method(){
        System.out.println("Fu ... method");
    }
}

class Zi extends Fu{
    int num = 20;
    
    public void method(){
        System.out.println("Zi ... method");
    }
}

// 运行结果
10
Zi ... method
```

### 多态的好处和弊端

- 多态的好处:提高了程序的扩展性
  - 具体体现:定义方法的时候，使用父类型作为参数，该方法就可以接收这父类的任意子类对象
- 多态的弊端:不能使用子类的特有功能

### 多态中的转型

- 向上转型
  - 从子到父
  - 父类引用指向子类对象
- 向下转型
  - 从父到子
  - 父类引用转为子类对象

#### 多态中的转型存在的风险

- 概述:如果被转的引用类型变量，对应的实际类型和目标类型不是同一种类型，那么在转换的时候就会出现ClassCastException

#### 避免强转出现的问题

- 关键字instanceof
- 使用格式:
  - 变量名 instanceof 类型
  - 通俗的理解: 判断关键字左边的变量，是否是右边的类型，返回boolean类型结果

#### 代码实例

```java
package com.itheima.test3;

public class Test3Polymorpic {
    public static void main(String[] args) {
        useAnimal(new Dog());
        useAnimal(new Cat());
    }
    public static void useAnimal(Animal animal){
        animal.eat();

        if (animal instanceof Dog){
            Dog dog = (Dog) animal;
            dog.watchHome();
        }
    }
}

abstract class Animal{
    public abstract void eat();
}

class Dog extends Animal{
    public void eat(){
        System.out.println("狗吃肉");
    }
    public void watchHome(){
        System.out.println("狗看门");
    }
}
class Cat extends Animal{
    public void eat(){
        System.out.println("猫吃鱼");
    }
}
```

