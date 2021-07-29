package com.mine.demo;

/**
 * created by xulp
 * on 2021/7/29
 */
class Ticket implements Runnable {
    private int tick = 100;
    boolean flag = true;
    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (this) {
                    if (tick > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        System.out.println(Thread.currentThread().getName() + "...code:" + tick--);
                    }
                }
            }
        }else while (true)
            show();
    }

    public synchronized void show() {
        if (tick > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "...show():" + tick--);
        }
    }
}
