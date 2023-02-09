package com.itheima.MyClassLoader;

public class ClassLoaderDemo1 {
    public static void main(String[] args) {

        // 系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        // 平台类加载器
        ClassLoader classLoader1  = systemClassLoader.getParent();

        // 启动类加载器
        ClassLoader classLoader2 = classLoader1.getParent();

        System.out.println(systemClassLoader);
        // jdk.internal.loader.ClassLoaders$AppClassLoader@251a69d7
        System.out.println(classLoader1);
        // jdk.internal.loader.ClassLoaders$PlatformClassLoader@2f92e0f4
        System.out.println(classLoader2);
        // null -> 一般启动类加载器都是null，因为没有上层了
    }
}
