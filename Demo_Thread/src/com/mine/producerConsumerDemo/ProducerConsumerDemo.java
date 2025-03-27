package com.mine.producerConsumerDemo;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * created by xulp
 * 多生产者多消费者问题
 * 要求:
 * 生产者往缓冲区内放入资源
 * 消费者从缓冲区内取出资源
 * 缓冲区设置为最大是5
 * <p>
 * 多个生产者时，一次只能有一个生产者往缓冲区放入资源(前提是缓冲区没满)
 * 多个消费者时，一次只能有一个消费者从缓冲区取出资源（前提是缓冲区未空）
 */
public class ProducerConsumerDemo {

    //缓冲区的资源数，初始化为0, resourceNum + freeNum = 5
    private static Semaphore resourceNum = new Semaphore(0);
    //缓冲区的空闲位置，初始为5,表示开始有5个空闲位置
    private static Semaphore freeNum = new Semaphore(5);

    // 控制生产者的互斥锁,表示同时只能有一个生产者在行动
    private static Semaphore mutexPro = new Semaphore(1);

    // 控制消费者的互斥锁,表示同时只能有一个消费者在行动
    private static Semaphore mutexCon = new Semaphore(1);

    //用一个list来模拟生产和消费过程，因为需要频繁增删，所以用链表
    private static LinkedList<Object> list = new LinkedList<>();

    public static void main(String[] args) {

        Thread thPro_1 = new Thread(new MyProducerRunnable());

        Thread thCon_1 = new Thread(new MyConsumerRunnable());
        Thread thCon_2 = new Thread(new MyConsumerRunnable());


        thPro_1.start();
        thCon_1.start();
        thCon_2.start();
    }

    static class MyProducerRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                produce();
            }


        }

        void produce() {
            try {
                //如果当前缓冲区的空闲位置数已经<=0了,这里就阻塞
                System.out.println("生产者 --> freeNum.availablePermits() : "+freeNum.availablePermits()+" mutexPro.availablePermits(): "+ mutexPro.availablePermits());
                freeNum.acquire();
                //进入临界区
                mutexPro.acquire();
//                System.out.println("freeNum.availablePermits() : "+freeNum.availablePermits()+" mutexPro.availablePermits(): "+ mutexPro.availablePermits());

                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //生产产品
//            System.out.println("生产者 产前 : 当前线程名称" + Thread.currentThread().getName() + " 生产产品之前  缓冲区资源数 : --- " + list.size());
            list.add(new Object());
//            System.out.println("生产者 产后 : 当前线程名称" + Thread.currentThread().getName() + " 生产产品 +1 缓冲区资源数 : --- " + list.size());
            System.out.println("生产者 --> 生产 : 当前线程名称" + Thread.currentThread().getName());

            //离开临界区，释放互斥信号量
            mutexPro.release();
            //提供产品，缓冲区的资源数+1
            resourceNum.release();
        }
    }

    static class MyConsumerRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                consume();
            }
        }

        void consume() {
            try {
                //如果当前缓冲区的资源数<=0了,这里就阻塞
                System.out.println("消费者 --> 当前线程名称 : " + Thread.currentThread().getName()+" resourceNum.availablePermits() : "+resourceNum.availablePermits()+" mutexCon.availablePermits(): "+ mutexCon.availablePermits());
                resourceNum.acquire();
                //进入临界区
                mutexCon.acquire();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("消费者 --> 消费 : 当前线程名称" + Thread.currentThread().getName());
//            System.out.println("消费者 消费前 : 当前线程名称" + Thread.currentThread().getName() + " 消耗产品之前  缓冲区资源数 : --- " + list.size());
            //消费数据
            list.remove();
//            System.out.println("消费者 消费后 : 当前线程名称" + Thread.currentThread().getName() + " 消耗产品 -1 缓冲区资源数 : --- " + list.size());


            //离开临界区，释放互斥信号量
            mutexCon.release();
            //消耗产品,缓冲区的空闲区+1
            freeNum.release();
        }
    }

}



