package com.mine.demo_Monitor;

/**
 * created by xulp
 * on 2022/10/11
 * 一个线程类
 */
public class MyRunnable implements Runnable {


    private BankBean bank;//临界资源对象
    private Object obj;

    public MyRunnable(BankBean bank, Object obj) {
        this.bank = bank;
        this.obj = obj;
    }


    @Override
    public void run() {


        for (int i = 0; i <= 100; i++) {

            synchronized (obj) {

                bank.add(1);

                if (bank.getAccount() % 2 == 1) {

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    obj.notify();
                }

                System.out.println("当前线程名称" + Thread.currentThread().getName() + " 账户余额是--- " + bank.getAccount());

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }

    }

}
