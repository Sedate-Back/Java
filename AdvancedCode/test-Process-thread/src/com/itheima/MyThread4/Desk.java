package com.itheima.MyThread4;
/*
    对比上一个Desk，把参数变成代参构造，变成标准的Java been类

 */
public class Desk {
    private boolean flag;

    private int count;

    private final Object lock = new Object();

    public Desk() {
        this(false, 10);
        // 空参构造的时候，对成员变量进行赋值
    }

    public Desk(boolean flag, int count) {
        this.flag = flag;
        this.count = count;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Desk{" +
                "flag=" + flag +
                ", count=" + count +
                ", lock=" + lock +
                '}';
    }
}
