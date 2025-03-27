package com.mine.thread;

/**
 * created by xulp
 * on 2021/11/19
 * 演示线程同步的7种方法之一 (实际上这里打印出来的结果还是乱的)
 *   b.使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新
 *   c.因此每次使用该域就要重新计算，而不是使用寄存器中的值
 *   d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量
 */
public class VolatileDemo {

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

                System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是---" + bank.getAccount());
                bank.add(1);

                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }
            }
        }
    }
}
