package com.itheima.MyEnum;

public enum Season {
    SPRING("春"){
        @Override
        public void show() {
            System.out.println(this.name);
        }
    },

    SUMMER("夏"){
        @Override
        public void show() {
            System.out.println(this.name);
        }
    },
    AUTUMN("秋"){
        @Override
        public void show() {
            System.out.println(this.name);
        }
    },
    WINTER("冬"){
        @Override
        public void show() {
            System.out.println(this.name);
        }
    };


    public String name;

    Season() {
    }

    Season(String name) {
        this.name = name;
    }

    // 抽象方法
    public abstract void show();

}
