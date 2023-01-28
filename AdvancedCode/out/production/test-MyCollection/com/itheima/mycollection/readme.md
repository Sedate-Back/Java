## Collection集合



### 数组和集合的区别【理解】

+ 相同点

  都是容器,可以存储多个数据

+ 不同点

  + 数组的长度是不可变的,集合的长度是可变的

  + 数组可以存基本数据类型和引用数据类型

    集合只能存引用数据类型,如果要存基本数据类型,需要存对应的包装类

    

### 集合类体系结构【理解】

![01_集合类体系结构图](E:/java/AdvancedCode/test-MyCollection/img/01_集合类体系结构图.png)

### Collection 集合概述和使用【应用】



+ Collection集合概述

  + 是单例集合的顶层接口,它表示一组对象,这些对象也称为Collection的元素
  + JDK 不提供此接口的任何直接实现.它提供更具体的子接口(如Set和List)实现

+ 创建Collection集合的对象

  + 多态的方式
  + 具体的实现类ArrayList

+ Collection集合常用方法

  | 方法名                     | 说明                               |
  | :------------------------- | :--------------------------------- |
  | boolean add(E e)           | 添加元素                           |
  | boolean remove(Object o)   | 从集合中移除指定的元素             |
  | boolean removeIf(Object o) | 根据条件进行移除                   |
  | void   clear()             | 清空集合中的元素                   |
  | boolean contains(Object o) | 判断集合中是否存在指定的元素       |
  | boolean isEmpty()          | 判断集合是否为空                   |
  | int   size()               | 集合的长度，也就是集合中元素的个数 |



### Collection集合的遍历【应用】

+ 迭代器介绍

  + 迭代器,集合的专用遍历方式
  + Iterator<E> iterator(): 返回此集合中元素的迭代器,通过集合对象的iterator()方法得到

+ Iterator中的常用方法

  ​	boolean hasNext(): 判断当前位置是否有元素可以被取出
  ​	E next(): 获取当前位置的元素,将迭代器对象移向下一个索引位置

+ Collection集合的遍历

  ```java
  public class IteratorDemo1 {
      public static void main(String[] args) {
          //创建集合对象
          Collection<String> c = new ArrayList<>();
  
          //添加元素
          c.add("hello");
          c.add("world");
          c.add("java");
          c.add("javaee");
  
          //Iterator<E> iterator()：返回此集合中元素的迭代器，通过集合的iterator()方法得到
          Iterator<String> it = c.iterator();
  
          //用while循环改进元素的判断和获取
          while (it.hasNext()) {
              String s = it.next();
              System.out.println(s);
          }
      }
  }
  ```

+ 迭代器中删除的方法

  ​	void remove(): 删除迭代器对象当前指向的元素

  ```java
  public class IteratorDemo2 {
      public static void main(String[] args) {
          ArrayList<String> list = new ArrayList<>();
          list.add("a");
          list.add("b");
          list.add("b");
          list.add("c");
          list.add("d");
  
          Iterator<String> it = list.iterator();
          while(it.hasNext()){
              String s = it.next();
              if("b".equals(s)){
                  //指向谁,那么此时就删除谁.
                  it.remove();
              }
          }
          System.out.println(list);
      }
  }
  ```



### 增强for循环【应用】

+ 介绍

  + 它是JDK5之后出现的,其内部原理是一个Iterator迭代器
  + 实现Iterable接口的类才可以使用迭代器和增强for
  + 简化数组和Collection集合的遍历

+ 格式

  ​	for(集合/数组中元素的数据类型 变量名 :  集合/数组名) {

  ​		// 已经将当前遍历到的元素封装到变量中了,直接使用变量即可

  ​	}

+ 代码

  ```java
  public class MyCollectonDemo1 {
      public static void main(String[] args) {
          ArrayList<String> list =  new ArrayList<>();
          list.add("a");
          list.add("b");
          list.add("c");
          list.add("d");
          list.add("e");
          list.add("f");
  
          //1,数据类型一定是集合或者数组中元素的类型
          //2,str仅仅是一个变量名而已,在循环的过程中,依次表示集合或者数组中的每一个元素
          //3,list就是要遍历的集合或者数组
          for(String str : list){
              System.out.println(str);
          }
      }
  }
  ```

## 