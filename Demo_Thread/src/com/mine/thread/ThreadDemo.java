package com.mine.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * created by xulp
 * on 2021/11/17
 * java中创建线程的4种方式
 * 1,继承Thread类来实现
 * 2,覆写Runnable()接口来实现
 * 3,覆写Callable接口实现多线程(在当年好像没这个方法)
 * 4,通过线程池启动多线程
 */
public class ThreadDemo {

    public static void main(String[] args) {
        //方法1，继承Thread类来实现
//        MyThread myThread1 = new MyThread();
//        MyThread myThread2 = new MyThread();
//        MyThread myThread3 = new MyThread();
//        myThread1.start();
//        myThread2.start();
//        myThread3.start();

        //  匿名内部类直接实现
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//
//                    try {
//                        Thread.sleep(30);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i + " runnable_2" + " 好多年没用过这种创建线程的方式了.");
//                }
//            }
//        }).start();

        //方法2，实现Runnable接口来实现
//        MyRunnable runnable = new MyRunnable("runnable_1");
//        Thread t1 = new Thread(runnable);
//        t1.start();

        //方法3,使用Callable来创建
        Callable<String> callable = new MyCallThread("线程1");
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread mThread = new Thread(futureTask);
        mThread.start();
    }

    //

    //方法2，实现Runnable接口
    static class MyRunnable implements Runnable {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i + " " + this.getName() + " 好多年没用过这种创建线程的方式了.");
            }
        }
    }


    //1,继承Thread类来实现
    static class MyThread extends Thread {

        public MyThread() {
        }

        @Override
        public void run() {
            super.run();

            for (int i = 0; i < 100; i++) {

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i + " " + this.getName() + " 好多年没用过这种创建线程的方式了.");
            }

        }
    }

    //第三种方法，实现Callable接口
    static class MyCallThread implements Callable<String> {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MyCallThread(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 100; i++) {

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i + " " + this.getName() + " 好多年没用过这种创建线程的方式了.");
            }
            return null;
        }
    }
}
