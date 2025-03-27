package com.mine.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by xulp
 * on 2021/11/19
 * 演示线程同步的7种方法之一
 * Lock()和UnLock()
 */
public class LockUnLockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Bank bank = new Bank();//作为临界资源，被两个线程持有
        MyRunnable my1 = new MyRunnable(bank);
        Thread th1 = new Thread(my1);
        MyRunnable my2 = new MyRunnable(bank);
        Thread th2 = new Thread(my2);
        th1.start();
        th2.start();

    }

    static class Bank {
        private int account = 1;

        public int getAccount() {
            return account;
        }


        public void add(int money) {
            account += money;
        }

    }

    static class MyRunnable implements Runnable {

        private Bank bank;


        public MyRunnable(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {

                //得使用同一个锁作为对象来给线程上锁
                lock.lock();
                System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是---" + bank.getAccount());
                bank.add(1);
                lock.unlock();//下锁

                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }
            }
        }
    }
}
