package com.mine.thread;

import java.util.concurrent.Semaphore;

/**
 * created by xulp
 * on 2021/11/19
 * 演示线程同步的7种方法之一
 * 使用信号量机制
 * 就是一个允许实现设置好的令牌。也许有1个，也许有10个或更多。
 * 谁拿到令牌(acquire)就可以去执行了，如果没有令牌则需要等待。
 * 执行完毕，一定要归还(release)令牌，否则令牌会被很快用光，别的线程就无法获得令牌而执行下去了。
 */
public class SemaphoreDemo {

    //// 操作信号量的对象, 这里设置的1,表示只有一个临界资源，同时只允许一个线程进入临界区
    static final Semaphore semaphore = new Semaphore(1);

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
        private volatile int account = 1;

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

                try {
                    semaphore.acquire();
                    System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是---" + bank.getAccount());
                    bank.add(1);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }


                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }
            }
        }
    }
}
