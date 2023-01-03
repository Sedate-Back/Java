package com.itheima.test2;

public abstract class CompositionTemplate {
    public void write(){
        System.out.println("<< My father >>");

        body();

        System.out.println("This is my father");
    }

    public abstract void body();
}
