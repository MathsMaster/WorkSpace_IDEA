package com.mine.demo;

/**
 * created by xulp
 * on 2021/7/29
 */
public class Demo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        try {
            Thread.sleep(10);
        }catch (InterruptedException e){

        }
        t.flag=false;
        t2.start();
    }
}
