package com.mine.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * created by xulp
 * on 2021/8/19
 */
public class UDPSocketSendUtils {

    public static void udpSend() throws Exception
    {

        DatagramSocket socket = new DatagramSocket();
        byte[] bs = "这是UDP发送的数据".getBytes();
        DatagramPacket packet = new DatagramPacket(bs, bs.length, InetAddress.getLocalHost(), 8070);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        socket.close();
    }



}
