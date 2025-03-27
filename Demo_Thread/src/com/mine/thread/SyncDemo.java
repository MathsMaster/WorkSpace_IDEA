package com.mine.thread;

/**
 * created by xulp
 * on 2021/11/17
 * 演示线程同步的8种方法：
 * synchronized , lock()和unLock() , 信号量都类似同一种机制，谁持有锁，就能继续执行。
 * wait和notify相当于另一种机制，把自己阻塞，让别人来唤醒自己。
 *
 * 1,使用synchronized关键字
 * 2,wait和notify (wait和notify必须是同一个监视器对象在调用,且都在同步代码块里调用(不在同步代码块内执行就会报异常)
 * ,A线程在同步代码块里调用了wait()后,会进入监视器对象的等待队列，并且释放锁,自己也就阻塞在同步代码块里了,后面也就不会继续走下去了;
 * B线程在获取到锁之后，就可以进入同步代码块里了，并且B进程在同步代码块里调用了监视器的notify(),虽然唤醒了A，但并不会立刻释放锁，而是等到B的同步代码块执行完后，
 * B才会释放锁，此时的A才能继续执行下去 ）
 * 3,lock()和unLock()
 * 4,使用特殊域变量volatile实现线程同步
 * 5,使用局部变量来实现线程同步(类似信号量机制)
 * 6,使用阻塞队列实现线程同步(类似管程机制)
 * 7,使用原子变量实现线程同步
 * 8,信号量机制
 */
public class SyncDemo {

    public static void main(String[] args) {

        userThread();
    }

    public static void userThread() {
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
                //方法1，使用synchronized时，需要使用同一个对象作为锁
//                synchronized (bank) {
//                    System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是---" + bank.getAccount());
//                    bank.add(1);
//                }

                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }
            }
        }
    }
}
