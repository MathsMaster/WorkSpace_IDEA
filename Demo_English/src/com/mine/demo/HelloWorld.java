package com.mine.demo;

import com.mine.bean.UserBean;
import com.mine.common.MyInterface;

import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        List list;
        new UserBean().getName();

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    static void print() {
        new MyInterface() {
            @Override
            public void add() {

            }
        }.add();
    }
}
