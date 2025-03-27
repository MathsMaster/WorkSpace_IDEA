package com.xulp.demo;

import com.xulp.utils.FileUtils;

/**
 * created by xulp
 * on 2022/9/21
 */
public class FileDemo {

    static String path = "/Users/xulp/Downloads/408_考研计算机/2023考研课后习题/01.数据结构";
    public static void main(String [] args)
    {
        System.out.println("Hello World!");
        FileUtils.deleteFileByPath(path,"真题");
    }
}
