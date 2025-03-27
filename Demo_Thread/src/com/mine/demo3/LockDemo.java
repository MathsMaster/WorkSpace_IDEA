package com.mine.demo3;

/**
 * created by xulp
 * on 2021/7/29
 * 演示线程间的通信
 *
 * 这个Demo应该叫管程机制
 *
 * wait(); 使线程进入冻结状态
 * notify(); 将该线程唤醒
 * notifyAll(); 将线程池中的所有线程唤醒
 *
 * 每个监视器对象都有一个对应的线程池；
 * r.wait();       表示当前线程进入r对象的线程池进行阻塞
 * r.notify();     表示唤醒r对象的线程池中的当前线程
 * r.notifyAll();  表示唤醒r对象的线程池中的所有线程
 */
public class LockDemo {

    public static void main(String[] args) {

        Resource r = new Resource();
        Input in = new Input(r);
        Output output = new Output(r);
        new Thread(in).start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
        new Thread(output).start();
    }
}

class Resource {

    String name;
    String sex;
    boolean flag = false;
}

class Input implements Runnable {
    Resource r;

    public Input(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {

        try {
            int x = 0;

            while (true) {

                synchronized (r) {
                    if (r.flag)
                        r.wait();
                    if (x == 0) {
                        r.name = "牛牛";
                        r.sex = "男";
                    } else {
                        r.name = "妞妞";
                        r.sex = "女";
                    }

                    r.flag = true;
                    r.notify();

                    x = (x + 1) % 2;
                }

            }
        } catch (Exception e) {

        }


    }
}

class Output implements Runnable {
    Resource r;

    public Output(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {

        try {
            while (true) {

                synchronized (r) {
                    if (!r.flag)
                        r.wait();
                    System.out.println("name : " + r.name + " ...... sex : " + r.sex);
                    r.flag = false;
                    r.notify();
                }

            }

        } catch (Exception e) {

        }
    }
}


