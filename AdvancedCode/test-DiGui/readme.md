## 递归

部分笔记

### 概念

- 以编程的角度来看，递归指的是方法定义中调用方法本身的现象
- 把一个复杂的问题层层转化为一个与原问题相似的规模较小的问题来求解
- 递归策略只需少量的程序就可描述出解题过程所需要的多次重复计算

### 代码实现

```java
public class MyFactorialDemo2 {
    public static void main(String[] args) {
        int sum = getSum(100);
        System.out.println(sum);
    }

    private static int getSum(int i) {
        //1- 100之间的和
            //100 + (1-99之间的和)
                    // 99 + (1- 98之间的和)
                        //....
                            //1
        //方法的作用: 求 1- i 之间和
        if(i == 1){
            return 1;
        }else{
            return i + getSum(i -1);
        }
    }
}    
```



### 注意事项

- 递归一定要有出口，否则无限循环后，内存会溢出
- 递归虽然有出口，但是递归的次数也不宜过多。否则内存溢出

### 小应用 - 递归求阶乘

- 代码实现

```java
public class DiGuiDemo01 {
    public static void main(String[] args) {
        //调用方法
        int result = jc(5);
        //输出结果
        System.out.println("5的阶乘是：" + result);
    }

    //定义一个方法，用于递归求阶乘，参数为一个int类型的变量
    public static int jc(int n) {
        //在方法内部判断该变量的值是否是1
        if(n == 1) {
            //是：返回1
            return 1;
        } else {
            //不是：返回n*(n-1)!
            return n*jc(n-1);
        }
    }
}
```

