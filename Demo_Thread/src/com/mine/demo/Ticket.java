package com.mine.demo;

/**
 * created by xulp
 * on 2021/7/29
 * 演示死锁问题
 */
class Ticket implements Runnable {
    private int tick = 100;
    boolean flag = true;
    Object obj = new Object();

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (obj) {
//                    System.out.println("run 当前线程名:"+Thread.currentThread().getName());
                    show();
                }
            }
        } else
            while (true)
                this.show();
    }

    public synchronized void show() {
        System.out.println("show 当前线程名:"+Thread.currentThread().getName());
        synchronized (obj)
        {
            if (tick > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + "....sale...." + tick--);
            }
        }

    }
}
