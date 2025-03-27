package com.mine.thread;

import com.mine.utils.Utils;


/**
 * created by xulp
 * on 2021/11/19
 * 演示线程同步的7种方法之一
 * 2,wait和notify (wait和notify必须是同一个监视器对象在调用,且都在同步代码块里调用(不在同步代码块内执行就会报异常)
 * ,A线程在同步代码块里调用了wait()后,会进入监视器对象的等待队列，并且释放锁,自己也就阻塞在同步代码块里了,后面也就不会继续走下去了;
 * B线程在获取到锁之后，就可以进入同步代码块里了，并且B进程在同步代码块里调用了监视器的notify(),虽然唤醒了A，但并不会立刻释放锁，而是等到B的同步代码块执行完后，
 * B才会释放锁，此时的A才能继续执行下去 ）
 */
public class WaitNotifyDemo {

    final static Object person = new Object();//作为监视器对象

    public static void main(String args[]) {

        try {
            Thread thread1 = new T1();
            Thread thread2 = new T2();
            thread1.start();
            thread2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class T1 extends Thread {
        public void run() {
            synchronized (person) {
                System.out.println(Utils.getFormatTime() + "  T1 come");
                try {
                    System.out.println(Utils.getFormatTime() + "  T1 wait");
                    person.wait();//当前线程释放锁，并且进入监视器对象的等待队列中
                } catch (InterruptedException r) {
                    r.getStackTrace();
                }
                System.out.println(Utils.getFormatTime() + "  T1 over");//最后才执行这里
            }
        }
    }

    public static class T2 extends Thread {
        public void run() {
            //防止T2先进入同步代码块
            try {
                Thread.sleep(1000);
            } catch (InterruptedException r) {
                r.getStackTrace();
            }

            synchronized (person) {
                System.out.println(Utils.getFormatTime() + "  T2 come");
                person.notify();//当前线程会唤醒监视器对象中的一个随机对象，但是不会立刻释放持有的锁，而是等到同步代码块执行完，才会释放锁
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException r) {
                    r.getStackTrace();
                }
                System.out.println(Utils.getFormatTime() + "  T2 over");//执行完这里后才会释放锁，线程1才能拿到锁继续运行
            }
        }
    }



}
