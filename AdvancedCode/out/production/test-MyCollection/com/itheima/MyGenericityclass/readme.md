## 泛型

### 泛型概述【理解】

+ 泛型的介绍

  ​	泛型是JDK5中引入的特性，它提供了编译时类型安全检测机制

+ 泛型的好处

  1. 把运行时期的问题提前到了编译期间
  2. 避免了强制类型转换

+ 泛型的定义格式

  + <类型>: 指定一种类型的格式.尖括号里面可以任意书写,一般只写一个字母.例如: <E> <T>
  + <类型1,类型2…>: 指定多种类型的格式,多种类型之间用逗号隔开.例如: <E,T> <K,V>



### 泛型类【应用】

- 定义格式

  ```java
  修饰符 class 类名<类型> {  }
  ```

- 示例代码

  - 泛型类

    ```java
    public class Generic<T> {
        private T t;
    
        public T getT() {
            return t;
        }
    
        public void setT(T t) {
            this.t = t;
        }
    }
    ```

  - 测试类

    ```java
    public class GenericDemo1 {
        public static void main(String[] args) {
            Generic<String> g1 = new Generic<String>();
            g1.setT("杨幂");
            System.out.println(g1.getT());
    
            Generic<Integer> g2 = new Generic<Integer>();
            g2.setT(30);
            System.out.println(g2.getT());
    
            Generic<Boolean> g3 = new Generic<Boolean>();
            g3.setT(true);
            System.out.println(g3.getT());
        }
    }
    ```



### 泛型方法【应用】

- 定义格式

  ```java
  修饰符 <类型> 返回值类型 方法名(类型 变量名) {  }
  ```

- 示例代码

  - 带有泛型方法的类

    ```java
    public class Generic {
        public <T> void show(T t) {
            System.out.println(t);
        }
    }
    ```

  - 测试类

    ```java
    public class GenericDemo2 {
        public static void main(String[] args) {
    	    Generic g = new Generic();
            g.show("柳岩");
            g.show(30);
            g.show(true);
            g.show(12.34);
        }
    }
    ```



### 泛型接口【应用】

- 定义格式

  ```java
  修饰符 interface 接口名<类型> {  }
  ```

- 示例代码

  - 泛型接口

    ```java
    public interface Generic<T> {
        void show(T t);
    }
    ```

  - 泛型接口实现类1

    ​	定义实现类时,定义和接口相同泛型,创建实现类对象时明确泛型的具体类型

    ```java
    public class GenericImpl1<T> implements Generic<T> {
        @Override
        public void show(T t) {
            System.out.println(t);
        }
    }
    ```

  - 泛型接口实现类2

    ​	定义实现类时,直接明确泛型的具体类型

    ```java
    public class GenericImpl2 implements Generic<Integer>{
         @Override
         public void show(Integer t) {
              System.out.println(t);
         }
    }
    ```

  - 测试类

    ```java
    public class GenericDemo3 {
        public static void main(String[] args) {
            GenericImpl1<String> g1 = new GenericImpl<String>();
            g1.show("林青霞");
            GenericImpl1<Integer> g2 = new GenericImpl<Integer>();
            g2.show(30);
          
            GenericImpl2 g3 = new GenericImpl2();
          	g3.show(10);
        }
    }
    
    ```



### 类型通配符

- 类型通配符: <?>

  - ArrayList<?>: 表示元素类型未知的ArrayList,它的元素可以匹配任何的类型
  - 但是并不能把元素添加到ArrayList中了,获取出来的也是父类类型

- 类型通配符上限: <? extends 类型>

  - ArrayListList <? extends Number>: 它表示的类型是Number或者其子类型

- 类型通配符下限: <? super 类型>

  - ArrayListList <? super Number>: 它表示的类型是Number或者其父类型

- 泛型通配符的使用

  ```java
  public class GenericDemo4 {
      public static void main(String[] args) {
          ArrayList<Integer> list1 = new ArrayList<>();
          ArrayList<String> list2 = new ArrayList<>();
          ArrayList<Number> list3 = new ArrayList<>();
          ArrayList<Object> list4 = new ArrayList<>();
  
          method(list1);
          method(list2);
          method(list3);
          method(list4);
  
          getElement1(list1);
          getElement1(list2);//报错
          getElement1(list3);
          getElement1(list4);//报错
  
          getElement2(list1);//报错
          getElement2(list2);//报错
          getElement2(list3);
          getElement2(list4);
      }
    
      // 泛型通配符: 此时的泛型?,可以是任意类型
      public static void method(ArrayList<?> list){}
      // 泛型的上限: 此时的泛型?,必须是Number类型或者Number类型的子类
      public static void getElement1(ArrayList<? extends Number> list){}
      // 泛型的下限: 此时的泛型?,必须是Number类型或者Number类型的父类
      public static void getElement2(ArrayList<? super Number> list){}
  
  }
  ```



