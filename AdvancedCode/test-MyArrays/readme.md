## 数组的高级操作

### 二分查找

#### 概念

- 查找指定元素在数组中的位置时,以前的方式是通过遍历,逐个获取每个元素,看是否是要查找的元素,这种方式当数组元素较多时,查找的效率很低
- 二分查找也叫折半查找,每次可以去掉一半的查找范围,从而提高查找的效率



#### 小案例

- 需求
  - 在数组{1,2,3,4,5,6,7,8,9,10}中,查找某个元素的位置
- 实现步骤

1. 定义两个变量，表示要查找的范围。默认min = 0 ，max = 最大索引
2. 循环查找，但是min <= max
3. 计算出mid的值
4. 判断mid位置的元素是否为要查找的元素，如果是直接返回对应索引
5. 如果要查找的值在mid的左半边，那么min值不变，max = mid -1.继续下次循环查找
6. 如果要查找的值在mid的右半边，那么max值不变，min = mid + 1.继续下次循环查找
7. 当min > max 时，表示要查找的元素在数组中不存在，返回-1.

- 代码实现

```java
public class MyBinarySearchDemo {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        int number = 11;

        //1,我现在要干嘛? --- 二分查找
        //2.我干这件事情需要什么? --- 数组 元素
        //3,我干完了,要不要把结果返回调用者 --- 把索引返回给调用者
        int index = binarySearchForIndex(arr,number);
        System.out.println(index);
    }

    private static int binarySearchForIndex(int[] arr, int number) {
        //1,定义查找的范围
        int min = 0;
        int max = arr.length - 1;
        //2.循环查找 min <= max
        while(min <= max){
            //3.计算出中间位置 mid
            int mid = (min + max) >> 1;
            //mid指向的元素 > number
            if(arr[mid] > number){
                //表示要查找的元素在左边.
                max = mid -1;
            }else if(arr[mid] < number){
                //mid指向的元素 < number
                //表示要查找的元素在右边.
                min = mid + 1;
            }else{
                //mid指向的元素 == number
                return mid;
            }
        }
        //如果min大于了max就表示元素不存在,返回-1.
        return -1;
    }
  
}
```



#### 注意事项

- 有一个前提条件，数组内的元素一定要按照大小顺序排列，如果没有大小顺序，是不能使用二分查找法的



### 冒泡排序

#### 概念

一种排序的方式，对要进行排序的数据中相邻的数据进行两两比较，将较大的数据放在后面，依次对所有的数据进行操作，直至所有数据按要求完成排序

如果有n个数据进行排序，总共需要比较n-1次

每一次比较完毕，下一次的比较就会少一个数据参与



#### 代码实现

```java
public class MyBubbleSortDemo2 {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 4};
        //1 2 3 4 5
        bubbleSort(arr);
    }

    private static void bubbleSort(int[] arr) {
        //外层循环控制的是次数 比数组的长度少一次.
        for (int i = 0; i < arr.length -1; i++) {
            //内存循环就是实际循环比较的
            //-1 是为了让数组不要越界
            //-i 每一轮结束之后,我们就会少比一个数字.
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
```



### 快速排序

#### 概念

冒泡排序算法中,一次循环结束,就相当于确定了当前的最大值,也能确定最大值在数组中应存入的位置

快速排序算法中,每一次递归时以第一个数为基准数,找到数组中所有比基准数小的.再找到所有比基准数大的.小的全部放左边,大的全部放右边,确定基准数的正确位置



#### 核心步骤

1. 从右开始找比基准数小的
2. 从左开始找比基准数大的
3. 交换两个值的位置
4. 红色继续往左找，蓝色继续往右找，直到两个箭头指向同一个索引为止
5. 基准数归位



#### 代码实现

```java
public class MyQuiteSortDemo2 {
    public static void main(String[] args) {
//        1，从右开始找比基准数小的
//        2，从左开始找比基准数大的
//        3，交换两个值的位置
//        4，红色继续往左找，蓝色继续往右找，直到两个箭头指向同一个索引为止
//        5，基准数归位
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        quiteSort(arr,0,arr.length-1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quiteSort(int[] arr, int left, int right) {
     	// 递归结束的条件
        if(right < left){
            return;
        }

        int left0 = left;
        int right0 = right;

        //计算出基准数
        int baseNumber = arr[left0];

        while(left != right){
//        1，从右开始找比基准数小的
            while(arr[right] >= baseNumber && right > left){
                right--;
            }
//        2，从左开始找比基准数大的
            while(arr[left] <= baseNumber && right > left){
                left++;
            }
//        3，交换两个值的位置
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        //基准数归位
        int temp = arr[left];
        arr[left] = arr[left0];
        arr[left0] = temp;
      
		// 递归调用自己,将左半部分排好序
        quiteSort(arr,left0,left-1);
      	// 递归调用自己,将右半部分排好序
        quiteSort(arr,left +1,right0);

    }
}
```



**总结：上述的方法都是通过原理去实现，也可以通过API来调用实现**



### Arrays ------ 数组的操作API

- Arrays的常用方法

  | 方法名                                           | 说明                               |
  | ------------------------------------------------ | ---------------------------------- |
  | public static String toString(int[] a)           | 返回指定数组的内容的字符串表示形式 |
  | public static void sort(int[] a)                 | 按照数字顺序排列指定的数组         |
  | public static int binarySearch(int[] a, int key) | 利用二分查找返回指定元素的索引     |

- 示例代码

```java
public class MyArraysDemo {
      public static void main(String[] args) {
 
  			int [] arr = {3,2,4,6,7};
  			System.out.println(Arrays.toString(arr));

  
  			int [] arr = {3,2,4,6,7};
 	        Arrays.sort(arr);
  	        System.out.println(Arrays.toString(arr));


          int [] arr = {1,2,3,4,5,6,7,8,9,10};
          int index = Arrays.binarySearch(arr, 0);
          System.out.println(index);
          //1,数组必须有序
          //2.如果要查找的元素存在,那么返回的是这个元素实际的索引
          //3.如果要查找的元素不存在,那么返回的是 (-插入点-1)
              //插入点:如果这个元素在数组中,他应该在哪个索引上.
      }
  }
```

