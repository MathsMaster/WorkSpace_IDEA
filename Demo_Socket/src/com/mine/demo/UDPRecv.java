package com.mine.demo;

import com.mine.utils.UDPSocketRecvUtils;

/**
 * created by xulp
 * on 2021/8/19
 *
 * UDP的接受端
 */
public class UDPRecv {

    public static void main(String[] args) throws Exception {
        System.out.println("UDP接受端启动........\n\n");

        UDPSocketRecvUtils.udpRecv();
    }
}
