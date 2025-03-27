package com.mine.demo;

import com.mine.bean.UserBean;
import com.mine.common.MyInterface;

import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        List list;
        new UserBean().getName();

        String str = "10 abound vi.大量存在;(in，with)充满，富于";
        System.out.println(cuttingStr(str));

    }

    static String cuttingStr(String str){
        if(str == null || str.equals(""))
            return "";
        str  = str.substring(str.indexOf(" ")+1);
        return str;
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
