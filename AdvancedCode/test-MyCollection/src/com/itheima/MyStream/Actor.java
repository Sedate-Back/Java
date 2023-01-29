package com.itheima.MyStream;

public class Actor {
    private String name;

    public Actor(String name) {
        this.name = name;
    }

    public Actor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }
}
