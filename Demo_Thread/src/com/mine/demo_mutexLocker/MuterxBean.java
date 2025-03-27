package com.mine.demo_mutexLocker;

/**
 * created by xulp
 * on 2022/10/11
 * 一个互斥锁对象
 */
public class MuterxBean {

    //默认锁是可获得的
    private boolean available = true;

    //获得锁
    public void acquire()
    {
        System.out.println("当前线程名称" + Thread.currentThread().getName() + " 当前线程锁的状态--- " + getAvailableStatus());
        while (!available);
        available = false;
    }

    //释放锁
    public void release()
    {
        available = true;
    }

    public boolean getAvailableStatus()
    {
        return available;
    }
}
