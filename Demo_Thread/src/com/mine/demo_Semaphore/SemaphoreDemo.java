package com.mine.demo_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * created by xulp
 * on 2022/10/11
 *
 * 使用信号量的方式来控制同时只有多个线程进入临界区
 *
 * 这里的信号量和 synchronized 以及互斥锁Locker还不一样,针对的问题不同，
 *  synchronized 以及互斥锁Locker:
 *      一次只让一个线程进入临界区执行代码
 *  Semaphore:
 *      能同时让多个线程进入临界区执行代码。Semaphore中的信号量数值 s 表示，还能让s个线程进入（也就是表示还剩下 s 个资源可用）
 *      当 资源数 s <=0 后,其他线程就会进入队列阻塞。
 *      当 资源被释放后，s > 0 后，就会唤醒队列中阻塞的一个资源。
 *
 *      当信号量的数值s初值设为1时，正好可以用来控制线程并发，一次只允许一个线程进入临界区
 *
 */
public class SemaphoreDemo {

    public static void main(String [] args)
    {

        System.out.println("Hello World !   -----SemaphoreDemo");

        //java中的表示信号量的对象,传进去的参数 3,作为信号量的数值，表示还有3个临界资源，可以让3个线程同时进入临界区
        Semaphore semaphore = new Semaphore(3);


        MyRunnable my1 = new MyRunnable(semaphore);
        Thread th1 = new Thread(my1);
        MyRunnable my2 = new MyRunnable(semaphore);
        Thread th2 = new Thread(my2);
        MyRunnable my3 = new MyRunnable(semaphore);
        Thread th3 = new Thread(my3);
        MyRunnable my4 = new MyRunnable(semaphore);
        Thread th4 = new Thread(my4);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}
