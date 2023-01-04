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