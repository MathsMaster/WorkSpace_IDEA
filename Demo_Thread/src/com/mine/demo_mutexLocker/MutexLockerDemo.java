package com.mine.demo_mutexLocker;

import java.util.concurrent.locks.ReentrantLock;

/**
 * created by xulp
 * on 2022/10/11
 * 演示使用互斥锁来解决线程并发的问题
 * <p>
 * 发现实际演示时，用这种方式根本解决不了问题啊
 * <p>
 * 不得不承认，还是系统自带的互斥锁比我的好用，估计系统使用了原子操作的方式，
 */
public class MutexLockerDemo {


    public static void main(String[] args) {
        System.out.println("Hello World !   -----MutexLockerDemo");


        BankBean bank = new BankBean();//作为临界资源，被两个线程持有

        ReentrantLock lock = new ReentrantLock();//这里使用java的互斥锁


        MyRunnable my1 = new MyRunnable(bank, lock);
        Thread th1 = new Thread(my1);
        MyRunnable my2 = new MyRunnable(bank, lock);
        Thread th2 = new Thread(my2);
        th1.start();
        th2.start();
    }
}
