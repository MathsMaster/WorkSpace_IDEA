package com.mine.demo;

import com.mine.utils.UDPSocketSendUtils;

/**
 * created by xulp
 * on 2021/8/19
 *
 * UDP的发送端
 */
public class UDPSend {

    public static void main(String[] args) throws Exception {
        System.out.println("UDP发送端启动........\n\n");

        UDPSocketSendUtils.udpSend();
    }
}
