package com.itheima.MyGenericityclass;

public class box<E> {
    // 定义一个泛型类
    // 格式就是在类名后 加入 <X>  这个X可以用任何大写英文字母代替，代指不清楚用户需要用什么类型的变量
    private E element;

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
