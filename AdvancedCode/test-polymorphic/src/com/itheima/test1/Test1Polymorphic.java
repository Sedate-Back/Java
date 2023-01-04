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
