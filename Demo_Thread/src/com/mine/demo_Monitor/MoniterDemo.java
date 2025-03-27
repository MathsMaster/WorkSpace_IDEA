package com.mine.demo_Monitor;


/**
 * created by xulp
 * on 2022/10/11
 *
 * 管程:
 *  java里的 synchronized 关键字及 wait()、notify()、notifyAll() 这三个方法都是基于管程的机制
 *
 *  synchronized:
 *      自带一个入口等待队列，一个条件变量的等待队列
 *
 *      object.wait() : 在临界区内调用，放弃当前线程持有的锁，并且进入object的条件变量阻塞队列
 *      object.notify() : 随机唤醒一个object的条件阻塞队列中的线程,并获得锁
 *      object.notifyAll() : 唤醒一个object的条件阻塞队列中的所有线程
 */
public class MoniterDemo {

    public static void main(String [] args)
    {
        System.out.println("Hello World !   -----MoniterDemo");


        BankBean bank = new BankBean();//作为临界资源，被两个线程持有
        Object obj = new Object();

        MyRunnable my1 = new MyRunnable(bank,obj);
        Thread th1 = new Thread(my1);
        MyRunnable my2 = new MyRunnable(bank,obj);
        Thread th2 = new Thread(my2);
        th1.start();
        th2.start();
    }
}
