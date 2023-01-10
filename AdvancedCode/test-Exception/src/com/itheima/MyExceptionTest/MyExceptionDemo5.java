package com.itheima.MyExceptionTest;

import java.util.Scanner;

public class MyExceptionDemo5 {
    // 练习训练
    public static void main(String[] args) {
        // 键盘录入学生的姓名和年龄,其中年龄为 18 - 25岁,
        // 超出这个范围是异常数据不能赋值.需要重新录入,一直录到正确为止。
        Student student =  new Student();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        student.setName(name);

        while (true){
            System.out.println("请输入学生年龄：");
            String agestr = scanner.nextLine();
            try{
                int age = Integer.parseInt(agestr); // 将字符串转化为Int类型
                student.setAge(age);
                break;
            }catch (NumberFormatException e){
                System.out.println("请输入一个整数！");
                continue;
            }catch (AgeOutOfBoundsException e){
                System.out.println(e.toString());
                System.out.println("请输入一个符合范围的年龄");

                continue;
            }
        }
        System.out.println(student);
    }
}
