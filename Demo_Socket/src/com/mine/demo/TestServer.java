package com.mine.demo;

import com.mine.utils.SocketSeverUtils;

/**
 * created by xulp
 * on 2021/8/19
 */
public class TestServer {

    public static void main(String [] args)
    {
        System.out.println("服务端启动........\n\n");

        SocketSeverUtils.lanuchSever();
    }
}
