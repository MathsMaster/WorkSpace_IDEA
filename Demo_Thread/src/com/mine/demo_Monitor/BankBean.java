package com.mine.demo_Monitor;

/**
 * created by xulp
 * on 2022/10/11
 * 作为一个临界资源对象,被多个线程持有
 */
public class BankBean {

    private int account = 0;

    public int getAccount() {
        return account;
    }


    public void add(int money) {
        account += money;
    }  
}
