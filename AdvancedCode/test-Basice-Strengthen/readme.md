## 1.类加载器

### 1.1类加载器【理解】

+ 作用

  负责将.class文件（存储的物理文件）加载在到内存中

  ![01_类加载器](img/01_类加载器.png)

### 1.2类加载的过程【理解】

+ 类加载时机

  + 创建类的实例（对象）
  + 调用类的类方法
  + 访问类或者接口的类变量，或者为该类变量赋值
  + 使用反射方式来强制创建某个类或接口对应的java.lang.Class对象
  + 初始化某个类的子类
  + 直接使用java.exe命令来运行某个主类

+ 类加载过程

  1. 加载

     + 通过包名 + 类名，获取这个类，准备用流进行传输
     + 在这个类加载到内存中
     + 加载完毕创建一个class对象

     ![02_类加载过程加载](img/02_类加载过程加载.png)

  2. 链接

     + 验证

       确保Class文件字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身安全

       (文件中的信息是否符合虚拟机规范有没有安全隐患)

     ![03_类加载过程验证](img/03_类加载过程验证.png)

     + 准备

       负责为类的类变量（被static修饰的变量）分配内存，并设置默认初始化值

       (初始化静态变量)

     ![04_类加载过程准备](img/04_类加载过程准备.png)

     + 解析

       将类的二进制数据流中的符号引用替换为直接引用

       (本类中如果用到了其他类，此时就需要找到对应的类)

     ![05_类加载过程解析](img/05_类加载过程解析.png)

  3. 初始化

     根据程序员通过程序制定的主观计划去初始化类变量和其他资源

     (静态变量赋值以及初始化其他资源)

     ![06_类加载过程初始化](img/06_类加载过程初始化.png)

+ 小结

  + 当一个类被使用的时候，才会加载到内存
  + 类加载的过程: 加载、验证、准备、解析、初始化

### 1.3类加载的分类【理解】

+ 分类

  + Bootstrap class loader：虚拟机的内置类加载器，通常表示为null ，并且没有父null
  + Platform class loader：平台类加载器,负责加载JDK中一些特殊的模块
  + System class loader：系统类加载器,负责加载用户类路径上所指定的类库

+ 类加载器的继承关系

  + System的父加载器为Platform
  + Platform的父加载器为Bootstrap

+ 代码演示

  ```java
  public class ClassLoaderDemo1 {
      public static void main(String[] args) {
          //获取系统类加载器
          ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
  
          //获取系统类加载器的父加载器 --- 平台类加载器
          ClassLoader classLoader1 = systemClassLoader.getParent();
  
          //获取平台类加载器的父加载器 --- 启动类加载器
          ClassLoader classLoader2 = classLoader1.getParent();
  
          System.out.println("系统类加载器" + systemClassLoader);
          System.out.println("平台类加载器" + classLoader1);
          System.out.println("启动类加载器" + classLoader2);
  
      }
  }
  ```

### 1.4双亲委派模型【理解】

+ 介绍

  如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行，如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器，如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派模式

  ![07_双亲委派模型](img/07_双亲委派模型.png)


### 1.5ClassLoader 中的两个方法【应用】

- 方法介绍

  | 方法名                                              | 说明               |
  | --------------------------------------------------- | ------------------ |
  | public static ClassLoader getSystemClassLoader()    | 获取系统类加载器   |
  | public InputStream getResourceAsStream(String name) | 加载某一个资源文件 |

- 示例代码

  ```java
  public class ClassLoaderDemo2 {
      public static void main(String[] args) throws IOException {
          //static ClassLoader getSystemClassLoader() 获取系统类加载器
          //InputStream getResourceAsStream(String name)  加载某一个资源文件
  
          //获取系统类加载器
          ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
  
          //利用加载器去加载一个指定的文件
          //参数：文件的路径（放在src的根目录下，默认去那里加载）
          //返回值：字节流。
          InputStream is = systemClassLoader.getResourceAsStream("prop.properties");
  
          Properties prop = new Properties();
          prop.load(is);
  
          System.out.println(prop);
  
          is.close();
      }
  }
  ```

## 2.反射

### 2.1反射的概述【理解】

+ 反射机制

  是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
  对于任意一个对象，都能够调用它的任意属性和方法；
  这种动态获取信息以及动态调用对象方法的功能称为Java语言的反射机制。

### 2.2获取Class类对象的三种方式【应用】

+ 三种方式分类

  + 类名.class属性

  + 对象名.getClass()方法

  + Class.forName(全类名)方法

    

  ![08_获取Class对象的三种方式](img/08_获取Class对象的三种方式.png)

+ 示例代码

  ```java
  public class Student {
      private String name;
      private int age;
  
      public Student() {
      }
  
      public Student(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      public void study(){
          System.out.println("学生在学习");
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  }
  public class ReflectDemo1 {
      public static void main(String[] args) throws ClassNotFoundException {
          //1.Class类中的静态方法forName("全类名")
              //全类名:包名 + 类名
          Class clazz = Class.forName("com.itheima.myreflect2.Student");
          System.out.println(clazz);
  
          //2.通过class属性来获取
          Class clazz2 = Student.class;
          System.out.println(clazz2);
  
          //3.利用对象的getClass方法来获取class对象
          //getClass方法是定义在Object类中.
          Student s = new Student();
          Class clazz3 = s.getClass();
          System.out.println(clazz3);
  
          System.out.println(clazz == clazz2);
          System.out.println(clazz2 == clazz3);
      }
  }
  ```

### 2.3反射获取构造方法并使用【应用】

#### 2.3.1Class类获取构造方法对象的方法

- 方法介绍

  | 方法名                                                       | 说明                           |
  | ------------------------------------------------------------ | ------------------------------ |
  | Constructor<?>[] getConstructors()                           | 返回所有公共构造方法对象的数组 |
  | Constructor<?>[] getDeclaredConstructors()                   | 返回所有构造方法对象的数组     |
  | Constructor<T> getConstructor(Class<?>... parameterTypes)    | 返回单个公共构造方法对象       |
  | Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) | 返回单个构造方法对象           |

- 示例代码

  ```java
  public class Student {
      private String name;
      private int age;
  
      //私有的有参构造方法
      private Student(String name) {
          System.out.println("name的值为:" + name);
          System.out.println("private...Student...有参构造方法");
      }
  
      //公共的无参构造方法
      public Student() {
          System.out.println("public...Student...无参构造方法");
      }
  
      //公共的有参构造方法
      public Student(String name, int age) {
          System.out.println("name的值为:" + name + "age的值为:" + age);
          System.out.println("public...Student...有参构造方法");
      }
  }
  public class ReflectDemo1 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
          //method1();
          //method2();
          //method3();
          //method4();
      }
  
      private static void method4() throws ClassNotFoundException, NoSuchMethodException {
          //        Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)：
  //                                      返回单个构造方法对象
          //1.获取Class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
          Constructor constructor = clazz.getDeclaredConstructor(String.class);
          System.out.println(constructor);
      }
  
      private static void method3() throws ClassNotFoundException, NoSuchMethodException {
          //        Constructor<T> getConstructor(Class<?>... parameterTypes)：
  //                                      返回单个公共构造方法对象
          //1.获取Class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
          //小括号中,一定要跟构造方法的形参保持一致.
          Constructor constructor1 = clazz.getConstructor();
          System.out.println(constructor1);
  
          Constructor constructor2 = clazz.getConstructor(String.class, int.class);
          System.out.println(constructor2);
  
          //因为Student类中,没有只有一个int的构造,所以这里会报错.
          Constructor constructor3 = clazz.getConstructor(int.class);
          System.out.println(constructor3);
      }
  
      private static void method2() throws ClassNotFoundException {
          //        Constructor<?>[] getDeclaredConstructors()：
  //                                      返回所有构造方法对象的数组
          //1.获取Class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
  
          Constructor[] constructors = clazz.getDeclaredConstructors();
          for (Constructor constructor : constructors) {
              System.out.println(constructor);
          }
      }
  
      private static void method1() throws ClassNotFoundException {
          //        Constructor<?>[] getConstructors()：
  //                                      返回所有公共构造方法对象的数组
          //1.获取Class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
          Constructor[] constructors = clazz.getConstructors();
          for (Constructor constructor : constructors) {
              System.out.println(constructor);
          }
      }
  }
  ```

#### 2.3.2Constructor类用于创建对象的方法

+ 方法介绍

  | 方法名                           | 说明                        |
  | -------------------------------- | --------------------------- |
  | T newInstance(Object...initargs) | 根据指定的构造方法创建对象  |
  | setAccessible(boolean flag)      | 设置为true,表示取消访问检查 |

+ 示例代码

  ```java
  // Student类同上一个示例,这里就不在重复提供了
  public class ReflectDemo2 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
          //T newInstance(Object... initargs)：根据指定的构造方法创建对象
          //method1();
          //method2();
          //method3();
          //method4();
  
      }
  
      private static void method4() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
          //获取一个私有的构造方法并创建对象
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
  
          //2.获取一个私有化的构造方法.
          Constructor constructor = clazz.getDeclaredConstructor(String.class);
  
          //被private修饰的成员,不能直接使用的
          //如果用反射强行获取并使用,需要临时取消访问检查
          constructor.setAccessible(true);
  
          //3.直接创建对象
          Student student = (Student) constructor.newInstance("zhangsan");
  
          System.out.println(student);
      }
  
      private static void method3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
          //简写格式
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
  
          //2.在Class类中,有一个newInstance方法,可以利用空参直接创建一个对象
          Student student = (Student) clazz.newInstance();//这个方法现在已经过时了,了解一下
  
          System.out.println(student);
      }
  
      private static void method2() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
  
          //2.获取构造方法对象
          Constructor constructor = clazz.getConstructor();
  
          //3.利用空参来创建Student的对象
          Student student = (Student) constructor.newInstance();
  
          System.out.println(student);
      }
  
      private static void method1() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect3.Student");
  
          //2.获取构造方法对象
          Constructor constructor = clazz.getConstructor(String.class, int.class);
  
          //3.利用newInstance创建Student的对象
          Student student = (Student) constructor.newInstance("zhangsan", 23);
  
          System.out.println(student);
      }
  }
  ```

#### 2.3.3小结

+ 获取class对象

  三种方式: Class.forName(“全类名”), 类名.class, 对象名.getClass()

+ 获取里面的构造方法对象

  getConstructor (Class<?>... parameterTypes)
  getDeclaredConstructor (Class<?>... parameterTypes)

+ 如果是public的，直接创建对象

  newInstance(Object... initargs)

+ 如果是非public的，需要临时取消检查，然后再创建对象

  setAccessible(boolean)  暴力反射

### 2.4反射获取成员变量并使用【应用】

#### 2.4.1Class类获取成员变量对象的方法

- 方法分类

  | 方法名                              | 说明                           |
  | ----------------------------------- | ------------------------------ |
  | Field[] getFields()                 | 返回所有公共成员变量对象的数组 |
  | Field[] getDeclaredFields()         | 返回所有成员变量对象的数组     |
  | Field getField(String name)         | 返回单个公共成员变量对象       |
  | Field getDeclaredField(String name) | 返回单个成员变量对象           |

- 示例代码

  ```java
  public class Student {
  
      public String name;
  
      public int age;
  
      public String gender;
  
      private int money = 300;
  
      @Override
      public String toString() {
          return "Student{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  ", gender='" + gender + '\'' +
                  ", money=" + money +
                  '}';
      }
  }
  public class ReflectDemo1 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
         // method1();
          //method2();
          //method3();
          //method4();
  
      }
  
      private static void method4() throws ClassNotFoundException, NoSuchFieldException {
          //        Field getDeclaredField(String name)：返回单个成员变量对象
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
    
          //2.获取money成员变量
          Field field = clazz.getDeclaredField("money");
    
          //3.打印一下
          System.out.println(field);
      }
    
      private static void method3() throws ClassNotFoundException, NoSuchFieldException {
          //        Field getField(String name)：返回单个公共成员变量对象
          //想要获取的成员变量必须是真实存在的
          //且必须是public修饰的.
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
    
          //2.获取name这个成员变量
          //Field field = clazz.getField("name");
          //Field field = clazz.getField("name1");
          Field field = clazz.getField("money");
    
          //3.打印一下
          System.out.println(field);
      }
    
      private static void method2() throws ClassNotFoundException {
          //        Field[] getDeclaredFields()：返回所有成员变量对象的数组
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
    
          //2.获取所有的Field对象
          Field[] fields = clazz.getDeclaredFields();
    
          //3.遍历
          for (Field field : fields) {
              System.out.println(field);
          }
      }
    
      private static void method1() throws ClassNotFoundException {
          //        Field[] getFields()：返回所有公共成员变量对象的数组
    
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
    
          //2.获取Field对象.
          Field[] fields = clazz.getFields();
    
          //3.遍历
          for (Field field : fields) {
              System.out.println(field);
          }
      }
  }
  ```

#### 2.4.2Field类用于给成员变量赋值的方法

+ 方法介绍

  | 方法名                             | 说明   |
  | ---------------------------------- | ------ |
  | void set(Object obj, Object value) | 赋值   |
  | Object get(Object obj)             | 获取值 |

+ 示例代码

  ```java
  // Student类同上一个示例,这里就不在重复提供了
  public class ReflectDemo2 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
  //        Object get(Object obj) 返回由该 Field表示的字段在指定对象上的值。
          //method1();
          //method2();
  
      }
  
      private static void method2() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
  
          //2.获取成员变量Field的对象
          Field field = clazz.getDeclaredField("money");
  
          //3.取消一下访问检查
          field.setAccessible(true);
  
          //4.调用get方法来获取值
          //4.1创建一个对象
          Student student = (Student) clazz.newInstance();
          //4.2获取指定对象的money的值
          Object o = field.get(student);
  
          //5.打印一下
          System.out.println(o);
      }
  
      private static void method1() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
          //        void set(Object obj, Object value)：给obj对象的成员变量赋值为value
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect4.Student");
  
          //2.获取name这个Field对象
          Field field = clazz.getField("name");
  
          //3.利用set方法进行赋值.
          //3.1先创建一个Student对象
          Student student = (Student) clazz.newInstance();
          //3.2有了对象才可以给指定对象进行赋值
          field.set(student,"zhangsan");
  
          System.out.println(student);
      }
  }
  ```

### 2.5反射获取成员方法并使用【应用】

#### 2.5.1Class类获取成员方法对象的方法

- 方法分类

  | 方法名                                                       | 说明                                       |
  | ------------------------------------------------------------ | ------------------------------------------ |
  | Method[] getMethods()                                        | 返回所有公共成员方法对象的数组，包括继承的 |
  | Method[] getDeclaredMethods()                                | 返回所有成员方法对象的数组，不包括继承的   |
  | Method getMethod(String name, Class<?>... parameterTypes)    | 返回单个公共成员方法对象                   |
  | Method getDeclaredMethod(String name, Class<?>... parameterTypes) | 返回单个成员方法对象                       |

- 示例代码

  ```java
  public class Student {
  
      //私有的，无参无返回值
      private void show() {
          System.out.println("私有的show方法，无参无返回值");
      }
  
      //公共的，无参无返回值
      public void function1() {
          System.out.println("function1方法，无参无返回值");
      }
  
      //公共的，有参无返回值
      public void function2(String name) {
          System.out.println("function2方法，有参无返回值,参数为" + name);
      }
  
      //公共的，无参有返回值
      public String function3() {
          System.out.println("function3方法，无参有返回值");
          return "aaa";
      }
  
      //公共的，有参有返回值
      public String function4(String name) {
          System.out.println("function4方法，有参有返回值,参数为" + name);
          return "aaa";
      }
  }
  public class ReflectDemo1 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
          //method1();
          //method2();
          //method3();
          //method4();
          //method5();
      }
  
      private static void method5() throws ClassNotFoundException, NoSuchMethodException {
          //        Method getDeclaredMethod(String name, Class<?>... parameterTypes)：
  //                                返回单个成员方法对象
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
          //2.获取一个成员方法show
          Method method = clazz.getDeclaredMethod("show");
          //3.打印一下
          System.out.println(method);
      }
    
      private static void method4() throws ClassNotFoundException, NoSuchMethodException {
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
          //2.获取一个有形参的方法function2
          Method method = clazz.getMethod("function2", String.class);
          //3.打印一下
          System.out.println(method);
      }
    
      private static void method3() throws ClassNotFoundException, NoSuchMethodException {
          //        Method getMethod(String name, Class<?>... parameterTypes) ：
  //                                返回单个公共成员方法对象
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
          //2.获取成员方法function1
          Method method1 = clazz.getMethod("function1");
          //3.打印一下
          System.out.println(method1);
      }
    
      private static void method2() throws ClassNotFoundException {
          //        Method[] getDeclaredMethods()：
  //                                返回所有成员方法对象的数组，不包括继承的
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
    
          //2.获取Method对象
          Method[] methods = clazz.getDeclaredMethods();
          //3.遍历一下数组
          for (Method method : methods) {
              System.out.println(method);
          }
      }
    
      private static void method1() throws ClassNotFoundException {
          //        Method[] getMethods()：返回所有公共成员方法对象的数组，包括继承的
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
          //2.获取成员方法对象
          Method[] methods = clazz.getMethods();
          //3.遍历
          for (Method method : methods) {
              System.out.println(method);
          }
      }
  }
  ```

#### 2.5.2Method类用于执行方法的方法

+ 方法介绍

  | 方法名                                    | 说明     |
  | ----------------------------------------- | -------- |
  | Object invoke(Object obj, Object... args) | 运行方法 |

  参数一: 用obj对象调用该方法

  参数二: 调用方法的传递的参数(如果没有就不写)

  返回值: 方法的返回值(如果没有就不写)

+ 示例代码

  ```java
  public class ReflectDemo2 {
      public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
  //        Object invoke(Object obj, Object... args)：运行方法
  //        参数一：用obj对象调用该方法
  //        参数二：调用方法的传递的参数（如果没有就不写）
  //        返回值：方法的返回值（如果没有就不写）
  
          //1.获取class对象
          Class clazz = Class.forName("com.itheima.myreflect5.Student");
          //2.获取里面的Method对象  function4
          Method method = clazz.getMethod("function4", String.class);
          //3.运行function4方法就可以了
          //3.1创建一个Student对象,当做方法的调用者
          Student student = (Student) clazz.newInstance();
          //3.2运行方法
          Object result = method.invoke(student, "zhangsan");
          //4.打印一下返回值
          System.out.println(result);
      }
  }
  ```



## 3.xml

### 3.1概述【理解】

+ 万维网联盟(W3C)

  万维网联盟(W3C)创建于1994年，又称W3C理事会。1994年10月在麻省理工学院计算机科学实验室成立。
  建立者： Tim Berners-Lee (蒂姆·伯纳斯·李)。
  是Web技术领域最具权威和影响力的国际中立性技术标准机构。
  到目前为止，W3C已发布了200多项影响深远的Web技术标准及实施指南，

  + 如广为业界采用的超文本标记语言HTML（标准通用标记语言下的一个应用）、

  + 可扩展标记语言XML（标准通用标记语言下的一个子集）

  + 以及帮助残障人士有效获得Web信息的无障碍指南（WCAG）等

    ![01_w3c概述](img/01_w3c概述.png)

+ xml概述

  XML的全称为(EXtensible Markup Language)，是一种可扩展的标记语言
  标记语言: 通过标签来描述数据的一门语言(标签有时我们也将其称之为元素)
  可扩展：标签的名字是可以自定义的,XML文件是由很多标签组成的,而标签名是可以自定义的

+ 作用

  + 用于进行存储数据和传输数据
  + 作为软件的配置文件

+ 作为配置文件的优势

  + 可读性好
  + 可维护性高

### 3.2标签的规则【应用】

+ 标签由一对尖括号和合法标识符组成

  ```java
  <student>
  ```

+ 标签必须成对出现

  ```java
  <student> </student>
  前边的是开始标签，后边的是结束标签
  ```

+ 特殊的标签可以不成对,但是必须有结束标记

  ```java
  <address/>
  ```

+ 标签中可以定义属性,属性和标签名空格隔开,属性值必须用引号引起来

  ```java
  <student id="1"> </student>
  ```

+ 标签需要正确的嵌套

  ```java
  这是正确的: <student id="1"> <name>张三</name> </student>
  这是错误的: <student id="1"><name>张三</student></name>
  ```

### 3.3语法规则【应用】

+ 语法规则

  + XML文件的后缀名为：xml

  + 文档声明必须是第一行第一列

    <?xml version="1.0" encoding="UTF-8" standalone="yes”?>
    version：该属性是必须存在的
    encoding：该属性不是必须的

    ​	打开当前xml文件的时候应该是使用什么字符编码表(一般取值都是UTF-8)

    standalone: 该属性不是必须的，描述XML文件是否依赖其他的xml文件，取值为yes/no

  + 必须存在一个根标签，有且只能有一个

  + XML文件中可以定义注释信息

  + XML文件中可以存在以下特殊字符

    ```java
    &lt; < 小于
    &gt; > 大于
    &amp; & 和号
    &apos; ' 单引号
    &quot; " 引号
    ```

  + XML文件中可以存在CDATA区

    <![CDATA[ …内容… ]]>

+ 示例代码

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!--注释的内容-->
  <!--本xml文件用来描述多个学生信息-->
  <students>
  
      <!--第一个学生信息-->
      <student id="1">
          <name>张三</name>
          <age>23</age>
          <info>学生&lt; &gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;&gt;的信息</info>
          <message> <![CDATA[内容 <<<<<< >>>>>> ]]]></message>
      </student>
  
      <!--第二个学生信息-->
      <student id="2">
          <name>李四</name>
          <age>24</age>
      </student>
  
  </students>
  ```

### 3.4xml解析【应用】

+ 概述

  xml解析就是从xml中获取到数据

+ 常见的解析思想

  DOM(Document Object Model)文档对象模型:就是把文档的各个组成部分看做成对应的对象。
  会把xml文件全部加载到内存,在内存中形成一个树形结构,再获取对应的值

  ![02_dom解析概述](img/02_dom解析概述.png)

+ 常见的解析工具

  + JAXP: SUN公司提供的一套XML的解析的API
  + JDOM: 开源组织提供了一套XML的解析的API-jdom
  + DOM4J: 开源组织提供了一套XML的解析的API-dom4j,全称：Dom For Java
  + pull: 主要应用在Android手机端解析XML

+ 解析的准备工作

  1. 我们可以通过网站：https://dom4j.github.io/ 去下载dom4j

     今天的资料中已经提供,我们不用再单独下载了,直接使用即可

  2. 将提供好的dom4j-1.6.1.zip解压,找到里面的dom4j-1.6.1.jar

  3. 在idea中当前模块下新建一个libs文件夹,将jar包复制到文件夹中

  4. 选中jar包 -> 右键 -> 选择add as library即可

+ 需求

  + 解析提供好的xml文件
  + 将解析到的数据封装到学生对象中
  + 并将学生对象存储到ArrayList集合中
  + 遍历集合

+ 代码实现

  ```java
  <?xml version="1.0" encoding="UTF-8" ?>
  <!--注释的内容-->
  <!--本xml文件用来描述多个学生信息-->
  <students>
  
      <!--第一个学生信息-->
      <student id="1">
          <name>张三</name>
          <age>23</age>
      </student>
  
      <!--第二个学生信息-->
      <student id="2">
          <name>李四</name>
          <age>24</age>
      </student>
  
  </students>
  
  // 上边是已经准备好的student.xml文件
  public class Student {
      private String id;
      private String name;
      private int age;
  
      public Student() {
      }
  
      public Student(String id, String name, int age) {
          this.id = id;
          this.name = name;
          this.age = age;
      }
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "id='" + id + '\'' +
                  ", name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  }
  
  /**
   * 利用dom4j解析xml文件
   */
  public class XmlParse {
      public static void main(String[] args) throws DocumentException {
          //1.获取一个解析器对象
          SAXReader saxReader = new SAXReader();
          //2.利用解析器把xml文件加载到内存中,并返回一个文档对象
          Document document = saxReader.read(new File("myxml\\xml\\student.xml"));
          //3.获取到根标签
          Element rootElement = document.getRootElement();
          //4.通过根标签来获取student标签
          //elements():可以获取调用者所有的子标签.会把这些子标签放到一个集合中返回.
          //elements("标签名"):可以获取调用者所有的指定的子标签,会把这些子标签放到一个集合中并返回
          //List list = rootElement.elements();
          List<Element> studentElements = rootElement.elements("student");
          //System.out.println(list.size());
  
          //用来装学生对象
          ArrayList<Student> list = new ArrayList<>();
  
          //5.遍历集合,得到每一个student标签
          for (Element element : studentElements) {
              //element依次表示每一个student标签
    
              //获取id这个属性
              Attribute attribute = element.attribute("id");
              //获取id的属性值
              String id = attribute.getValue();
  
              //获取name标签
              //element("标签名"):获取调用者指定的子标签
              Element nameElement = element.element("name");
              //获取这个标签的标签体内容
              String name = nameElement.getText();
  
              //获取age标签
              Element ageElement = element.element("age");
              //获取age标签的标签体内容
              String age = ageElement.getText();
  
  //            System.out.println(id);
  //            System.out.println(name);
  //            System.out.println(age);
  
              Student s = new Student(id,name,Integer.parseInt(age));
              list.add(s);
          }
          //遍历操作
          for (Student student : list) {
              System.out.println(student);
          }
      }
  }
  ```

### 3.5DTD约束【理解】

+ 什么是约束

  用来限定xml文件中可使用的标签以及属性

+ 约束的分类

  + DTD
  + schema

+ 编写DTD约束

  + 步骤

    1. 创建一个文件，这个文件的后缀名为.dtd

    2. 看xml文件中使用了哪些元素

       <!ELEMENT> 可以定义元素

    3. 判断元素是简单元素还是复杂元素

       简单元素：没有子元素。
       复杂元素：有子元素的元素；

  + 代码实现

    ```java
    <!ELEMENT persons (person)>
    <!ELEMENT person (name,age)>
    <!ELEMENT name (#PCDATA)>
    <!ELEMENT age (#PCDATA)>
    ```

  

+ 引入DTD约束

  + 引入DTD约束的三种方法

    + 引入本地dtd

      <!DOCTYPE 根元素名称 SYSTEM ‘DTD文件的路径'>

    + 在xml文件内部引入

      <!DOCTYPE 根元素名称 [ dtd文件内容 ]>

    + 引入网络dtd

      <!DOCTYPE 根元素的名称 PUBLIC "DTD文件名称" "DTD文档的URL">

  + 代码实现

    + 引入本地DTD约束

      ```xml
      // 这是persondtd.dtd文件中的内容,已经提前写好
      <!ELEMENT persons (person)>
      <!ELEMENT person (name,age)>
      <!ELEMENT name (#PCDATA)>
      <!ELEMENT age (#PCDATA)>
      
      // 在person1.xml文件中引入persondtd.dtd约束
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE persons SYSTEM 'persondtd.dtd'>
      
      <persons>
          <person>
              <name>张三</name>
              <age>23</age>
          </person>
      
      </persons>
      ```

    + 在xml文件内部引入

      ```xml
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE persons [
              <!ELEMENT persons (person)>
              <!ELEMENT person (name,age)>
              <!ELEMENT name (#PCDATA)>
              <!ELEMENT age (#PCDATA)>
              ]>
      
      <persons>
          <person>
              <name>张三</name>
              <age>23</age>
          </person>
      
      </persons>
      ```

    + 引入网络dtd

      ```xml
      <?xml version="1.0" encoding="UTF-8" ?>
      <!DOCTYPE persons PUBLIC "dtd文件的名称" "dtd文档的URL">
      
      <persons>
          <person>
              <name>张三</name>
              <age>23</age>
          </person>
      
      </persons>
      ```

+ DTD语法

  + 定义元素

    定义一个元素的格式为：<!ELEMENT 元素名 元素类型>
    简单元素：

    ​	EMPTY: 表示标签体为空

    ​	ANY: 表示标签体可以为空也可以不为空

    ​	PCDATA: 表示该元素的内容部分为字符串

    复杂元素：
    ​	直接写子元素名称. 多个子元素可以使用","或者"|"隔开；
    ​	","表示定义子元素的顺序 ; "|": 表示子元素只能出现任意一个
    ​	"?"零次或一次, "+"一次或多次, "*"零次或多次;如果不写则表示出现一次

    ![03_DTD语法定义元素](img/03_DTD语法定义元素.png)


  + 定义属性

    格式

    定义一个属性的格式为：<!ATTLIST 元素名称 属性名称 属性的类型 属性的约束>
    属性的类型：
    ​	CDATA类型：普通的字符串

    属性的约束:

    ​	// #REQUIRED： 必须的
    ​	// #IMPLIED： 属性不是必需的
    ​	// #FIXED value：属性值是固定的

  + 代码实现

    ```java
    <!ELEMENT persons (person+)>
    <!ELEMENT person (name,age)>
    <!ELEMENT name (#PCDATA)>
    <!ELEMENT age (#PCDATA)>
    <!ATTLIST person id CDATA #REQUIRED>
    
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE persons SYSTEM 'persondtd.dtd'>
    
    <persons>
        <person id="001">
            <name>张三</name>
            <age>23</age>
        </person>
    
        <person id = "002">
            <name>张三</name>
            <age>23</age>
        </person>
    
    </persons>
    ​```
    ```

### 3.6schema约束【理解】

+ schema和dtd的区别

  1. schema约束文件也是一个xml文件，符合xml的语法，这个文件的后缀名.xsd
  2. 一个xml中可以引用多个schema约束文件，多个schema使用名称空间区分（名称空间类似于java包名）
  3. dtd里面元素类型的取值比较单一常见的是PCDATA类型，但是在schema里面可以支持很多个数据类型
  4. schema 语法更加的复杂

  ![04_schema约束介绍](img/04_schema约束介绍.png)

+ 编写schema约束

  + 步骤

    1，创建一个文件，这个文件的后缀名为.xsd。
    2，定义文档声明
    3，schema文件的根标签为： <schema>
    4，在<schema>中定义属性：
    ​	xmlns=http://www.w3.org/2001/XMLSchema
    5，在<schema>中定义属性 ：
    ​	targetNamespace =唯一的url地址，指定当前这个schema文件的名称空间。
    6，在<schema>中定义属性 ：
    ​	elementFormDefault="qualified“，表示当前schema文件是一个质量良好的文件。
    7，通过element定义元素
    8，判断当前元素是简单元素还是复杂元素

    ![05_schema约束编写](img/05_schema约束编写.png)

  + 代码实现

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    <schema
        xmlns="http://www.w3.org/2001/XMLSchema"
        targetNamespace="http://www.itheima.cn/javase"
        elementFormDefault="qualified"
    >
    
        <!--定义persons复杂元素-->
        <element name="persons">
            <complexType>
                <sequence>
                    <!--定义person复杂元素-->
                    <element name = "person">
                        <complexType>
                            <sequence>
                                <!--定义name和age简单元素-->
                                <element name = "name" type = "string"></element>
                                <element name = "age" type = "string"></element>
                            </sequence>
                            
                        </complexType>
                    </element>
                </sequence>
            </complexType>
    
        </element>
    
    </schema>
    
    ```

+ 引入schema约束

  + 步骤

    1，在根标签上定义属性xmlns="http://www.w3.org/2001/XMLSchema-instance"
    2，通过xmlns引入约束文件的名称空间
    3，给某一个xmlns属性添加一个标识，用于区分不同的名称空间
    ​	格式为: xmlns:标识=“名称空间地址” ,标识可以是任意的，但是一般取值都是xsi
    4，通过xsi:schemaLocation指定名称空间所对应的约束文件路径
    ​	格式为：xsi:schemaLocation = "名称空间url 文件路径“

  + 代码实现

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    
    <persons
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.itheima.cn/javase"
        xsi:schemaLocation="http://www.itheima.cn/javase person.xsd"
    >
        <person>
            <name>张三</name>
            <age>23</age>
        </person>
    
    </persons>
    ​```
    ```

+ schema约束定义属性

  + 代码示例

    ```java
    <?xml version="1.0" encoding="UTF-8" ?>
    <schema
        xmlns="http://www.w3.org/2001/XMLSchema"
        targetNamespace="http://www.itheima.cn/javase"
        elementFormDefault="qualified"
    >
    
        <!--定义persons复杂元素-->
        <element name="persons">
            <complexType>
                <sequence>
                    <!--定义person复杂元素-->
                    <element name = "person">
                        <complexType>
                            <sequence>
                                <!--定义name和age简单元素-->
                                <element name = "name" type = "string"></element>
                                <element name = "age" type = "string"></element>
                            </sequence>
                            
                            <!--定义属性，required( 必须的)/optional( 可选的)-->
                            <attribute name="id" type="string" use="required"></attribute>
                        </complexType>
                        
                    </element>
                </sequence>
            </complexType>
        </element>
        
    </schema>
    
    <?xml version="1.0" encoding="UTF-8" ?>
    <persons
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.itheima.cn/javase"
        xsi:schemaLocation="http://www.itheima.cn/javase person.xsd"
    >
        <person id="001">
            <name>张三</name>
            <age>23</age>
        </person>
    
    </persons>
    ​```
    ```

## 4.枚举

### 4.1概述【理解】

为了间接的表示一些固定的值，Java就给我们提供了枚举
是指将变量的值一一列出来,变量的值只限于列举出来的值的范围内

### 4.2定义格式【应用】

+ 格式

  ```java
  public enum s {   
  	枚举项1,枚举项2,枚举项3;
  }
  注意: 定义枚举类要用关键字enum
  ```

+ 示例代码

  ```java
  // 定义一个枚举类，用来表示春，夏，秋，冬这四个固定值
  public enum Season {
      SPRING,SUMMER,AUTUMN,WINTER;
  }
  ```

### 4.3枚举的特点【理解】

+ 特点

  + 所有枚举类都是Enum的子类

  + 我们可以通过"枚举类名.枚举项名称"去访问指定的枚举项

  + 每一个枚举项其实就是该枚举的一个对象

  + 枚举也是一个类，也可以去定义成员变量

  + 枚举类的第一行上必须是枚举项，最后一个枚举项后的分号是可以省略的，但是如果枚举类有其他的东西，这个分号就不能省略。建议不要省略

  + 枚举类可以有构造器，但必须是private的，它默认的也是private的。

    枚举项的用法比较特殊：枚举("");

  + 枚举类也可以有抽象方法，但是枚举项必须重写该方法

+ 示例代码

  ```java
  public enum Season {
  
      SPRING("春"){
  
          //如果枚举类中有抽象方法
          //那么在枚举项中必须要全部重写
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
  
      //空参构造
      //private Season(){}
    
      //有参构造
      private Season(String name){
          this.name = name;
      }
    
      //抽象方法
      public abstract void show();
  }
  
  public class EnumDemo {
      public static void main(String[] args) {
          /*
          1.所有枚举类都是Enum的子类
          2.我们可以通过"枚举类名.枚举项名称"去访问指定的枚举项
          3.每一个枚举项其实就是该枚举的一个对象
          4.枚举也是一个类，也可以去定义成员变量
          5.枚举类的第一行上必须是枚举项，最后一个枚举项后的分号是可以省略的，
            但是如果枚举类有其他的东西，这个分号就不能省略。建议不要省略
          6.枚举类可以有构造器，但必须是private的，它默认的也是private的。
            枚举项的用法比较特殊：枚举("");
          7.枚举类也可以有抽象方法，但是枚举项必须重写该方法
      */
    
          //第二个特点的演示
          //我们可以通过"枚举类名.枚举项名称"去访问指定的枚举项
          System.out.println(Season.SPRING);
          System.out.println(Season.SUMMER);
          System.out.println(Season.AUTUMN);
          System.out.println(Season.WINTER);
    
          //第三个特点的演示
          //每一个枚举项其实就是该枚举的一个对象
          Season spring = Season.SPRING;
      }
  }
  ```

### 4.4枚举的方法【应用】

+ 方法介绍

  | 方法名                                            | 说明                                 |
  | ------------------------------------------------- | ------------------------------------ |
  | String name()                                     | 获取枚举项的名称                     |
  | int ordinal()                                     | 返回枚举项在枚举类中的索引值         |
  | int compareTo(E  o)                               | 比较两个枚举项，返回的是索引值的差值 |
  | String toString()                                 | 返回枚举常量的名称                   |
  | static <T> T  valueOf(Class<T> type,String  name) | 获取指定枚举类中的指定名称的枚举值   |
  | values()                                          | 获得所有的枚举项                     |

+ 示例代码

  ```java
  public enum Season {
      SPRING,SUMMER,AUTUMN,WINTER;
  }
  
  public class EnumDemo {
      public static void main(String[] args) {
  //        String name() 获取枚举项的名称
          String name = Season.SPRING.name();
          System.out.println(name);
          System.out.println("-----------------------------");
  
  //        int ordinal() 返回枚举项在枚举类中的索引值
          int index1 = Season.SPRING.ordinal();
          int index2 = Season.SUMMER.ordinal();
          int index3 = Season.AUTUMN.ordinal();
          int index4 = Season.WINTER.ordinal();
          System.out.println(index1);
          System.out.println(index2);
          System.out.println(index3);
          System.out.println(index4);
          System.out.println("-----------------------------");
  
  //        int compareTo(E o) 比较两个枚举项，返回的是索引值的差值
          int result = Season.SPRING.compareTo(Season.WINTER);
          System.out.println(result);//-3
          System.out.println("-----------------------------");
  
  //        String toString()   返回枚举常量的名称
          String s = Season.SPRING.toString();
          System.out.println(s);
          System.out.println("-----------------------------");
  
  //        static <T> T valueOf(Class<T> type,String name)
  //        获取指定枚举类中的指定名称的枚举值
          Season spring = Enum.valueOf(Season.class, "SPRING");
          System.out.println(spring);
          System.out.println(Season.SPRING == spring);
          System.out.println("-----------------------------");
  
  //        values()       获得所有的枚举项
          Season[] values = Season.values();
          for (Season value : values) {
              System.out.println(value);
          }
      }
  }
  ```

## 5.注解

### 5.1概述【理解】

+ 概述

  对我们的程序进行标注和解释

+ 注解和注释的区别

  + 注释: 给程序员看的
  + 注解: 给编译器看的

+ 使用注解进行配置的优势

  代码更加简洁,方便

### 5.2自定义注解【理解】

+ 格式

  public @interface 注解名称 {

  ​	public 属性类型 属性名() default 默认值 ;

  }

+ 属性类型

  + 基本数据类型
  + String
  + Class
  + 注解
  + 枚举
  + 以上类型的一维数组

+ 代码演示

  ```java
  public @interface Anno2 {
  }
  
  public enum Season {
      SPRING,SUMMER,AUTUMN,WINTER;
  }
  
  public @interface Anno1 {
  
      //定义一个基本类型的属性
      int a () default 23;
  
      //定义一个String类型的属性
      public String name() default "itheima";
  
      //定义一个Class类型的属性
      public Class clazz() default Anno2.class;
  
      //定义一个注解类型的属性
      public Anno2 anno() default @Anno2;
  
      //定义一个枚举类型的属性
      public Season season() default Season.SPRING;
  
      //以上类型的一维数组
      //int数组
      public int[] arr() default {1,2,3,4,5};
  
      //枚举数组
      public Season[] seasons() default {Season.SPRING,Season.SUMMER};
  
      //value。后期我们在使用注解的时候，如果我们只需要给注解的value属性赋值。
      //那么value就可以省略
      public String value();
  
  }
  
  //在使用注解的时候如果注解里面的属性没有指定默认值。
  //那么我们就需要手动给出注解属性的设置值。
  //@Anno1(name = "itheima")
  @Anno1("abc")
  public class AnnoDemo {
  }
  ```

+ 注意

  如果只有一个属性需要赋值，并且属性的名称是value，则value可以省略，直接定义值即可

+ 自定义注解案例

  + 需求

    自定义一个注解@Test,用于指定类的方法上,如果某一个类的方法上使用了该注解,就执行该方法

  + 实现步骤

    1. 自定义一个注解Test,并在类中的某几个方法上加上注解
    2. 在测试类中,获取注解所在的类的Class对象
    3. 获取类中所有的方法对象
    4. 遍历每一个方法对象,判断是否有对应的注解

  + 代码实现

    ```java
    //表示Test这个注解的存活时间
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Test {
    }
    
    public class UseTest {
    
        //没有使用Test注解
        public void show(){
            System.out.println("UseTest....show....");
        }
    
        //使用Test注解
        @Test
        public void method(){
            System.out.println("UseTest....method....");
        }
    
        //没有使用Test注解
        @Test
        public void function(){
            System.out.println("UseTest....function....");
        }
    }
    
    public class AnnoDemo {
        public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
            //1.通过反射获取UseTest类的字节码文件对象
            Class clazz = Class.forName("com.itheima.myanno3.UseTest");
    
            //创建对象
            UseTest useTest = (UseTest) clazz.newInstance();
    
            //2.通过反射获取这个类里面所有的方法对象
            Method[] methods = clazz.getDeclaredMethods();
    
            //3.遍历数组，得到每一个方法对象
            for (Method method : methods) {
                //method依次表示每一个方法对象。
                //isAnnotationPresent(Class<? extends Annotation> annotationClass)
                //判断当前方法上是否有指定的注解。
                //参数：注解的字节码文件对象
                //返回值：布尔结果。  true 存在  false 不存在
                if(method.isAnnotationPresent(Test.class)){
                    method.invoke(useTest);
                }
            }
        }
    }
    ```

### 5.3元注解【理解】

+ 概述

  元注解就是描述注解的注解

+ 元注解介绍

  | 元注解名    | 说明                                    |
  | ----------- | --------------------------------------- |
  | @Target     | 指定了注解能在哪里使用                  |
  | @Retention  | 可以理解为保留时间(生命周期)            |
  | @Inherited  | 表示修饰的自定义注解可以被子类继承      |
  | @Documented | 表示该自定义注解，会出现在API文档里面。 |

+ 示例代码

  ```java
  @Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})  //指定注解使用的位置（成员变量，类，方法）
  @Retention(RetentionPolicy.RUNTIME) //指定该注解的存活时间
  //@Inherited //指定该注解可以被继承
  public @interface Anno {
  }
  
  @Anno
  public class Person {
  }
  
  public class Student extends Person {
      public void show(){
          System.out.println("student.......show..........");
      }
  }
  
  public class StudentDemo {
      public static void main(String[] args) throws ClassNotFoundException {
          //获取到Student类的字节码文件对象
          Class clazz = Class.forName("com.itheima.myanno4.Student");
  
          //获取注解。
          boolean result = clazz.isAnnotationPresent(Anno.class);
          System.out.println(result);
      }
  }
  
  ```

