package com.itheima.mytreeset;

public class Student implements Comparable<Student> {
    private String name;
    private int chinese;
    private int math;
    private int english;

    private Student() {

    }

    public Student(String name, int chinese, int math, int english) {
        this.name = name;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}' + "总分为：" + getSum();
    }

    // 编写红黑树排序规则
    @Override
    public int compareTo(Student o) {
        // 这种排序方式，for循环后输出的结果是总分从小到大排序
        // 遵循红黑数从左到右的遍历顺序，如果需要把输出结果改成从大到小，就将 o.xxx - this.xxx 
        int result = this.getSum() - o.getSum();

        result = result == 0 ? this.getChinese() - o.getChinese() : result;
        result = result == 0 ? this.getMath() - o.getMath() : result;
        result = result == 0 ? this.getEnglish() - o.getEnglish() : result;

        result = result == 0 ? this.getName().compareTo(o.getName()) : result;
        return result;
    }


    private int getSum() {
        return chinese + math + english;
    }
}
