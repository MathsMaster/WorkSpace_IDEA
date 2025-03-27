package com.mine.demo_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * created by xulp
 * on 2022/10/11
 * 一个线程类
 */
public class MyRunnable implements Runnable {


    private Semaphore semaphore;//操作信号量的对象

    public MyRunnable(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            //使用信号量来控制，同时只有s个线程进入临界区,多余的线程就要进入阻塞队列了
            semaphore.acquire();

            System.out.println("当前线程名称 : " + Thread.currentThread().getName());

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //线程离开临界区,信号量数值s+1,同时唤醒阻塞队列中的一个进程
            semaphore.release();
        }

    }

}
