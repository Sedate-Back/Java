package com.itheima.ThreadAtom1;
// count++ 的流程其实是满足原子性的，但是加入了volatile关键字后，无法保证程序原子性的进行
// 所以需要加锁，目前有两种锁，一种是悲观锁，一种是乐观锁

// 悲观锁(synchronized) 就是认为，每次线程调用共享数据的时候，都会去修改共享数据的值，所以当一个线程访问共享数据的时候，会把访问权限锁起来，这样其他的线程无法修改；
//   当线程访问结束后，悲观锁会每一次都去刷新共享数据的值，不管有没有发送改变

import java.util.concurrent.atomic.AtomicInteger;

// 乐观锁（CAS）， 认为每次共享数据被读取的时候，都不会上锁，当其他线程修改了共享数据，才会检测一下，如果修改了就改共享数据，如果没修改就保持不变；
public class ThreadAtom1 {
    public static void main(String[] args) {
        MyAtomThread atom = new MyAtomThread();

        for (int i = 0; i < 100; i++) {
            new Thread(atom).start();
        }
    }
}
