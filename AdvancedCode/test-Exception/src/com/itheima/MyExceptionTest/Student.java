package com.itheima.MyExceptionTest;

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18 && age <= 25){
            this.age = age;
        }else{
            // 当年龄不合法，抛出异常
//            throw new RuntimeException("年龄超出了范围，请重新输入");
            // 如果JAVA中提供的异常不能满足我们的需求,我们可以使用自定义的异常
            throw new AgeOutOfBoundsException("年龄超出了范围");
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
