package com.mine.demo_mutexLocker;

import java.util.concurrent.locks.ReentrantLock;

/**
 * created by xulp
 * on 2022/10/11
 * 一个线程类
 */
public class MyRunnable implements Runnable {


    private BankBean bank;//临界资源对象
    private ReentrantLock lockBean;//java自身的互斥锁对象

    public MyRunnable(BankBean bank) {
        this.bank = bank;

    }

    public MyRunnable(BankBean bank, ReentrantLock lockBean) {
        this.bank = bank;
        this.lockBean = lockBean;
    }

    @Override
    public void run() {


        for (int i = 0; i < 100; i++) {

//            muterxBean.acquire();//从互斥锁对象上获取锁
            lockBean.lock();//使用java自带的锁

            //得使用同一个锁作为对象来给线程上锁
            System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是--- " + bank.getAccount());
            bank.add(1);

//            muterxBean.release();//从互斥锁对象上释放锁
            lockBean.unlock();

            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }


        }

    }

}
