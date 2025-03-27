package com.mine.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * created by xulp
 * on 2021/8/19
 */
public class UDPSocketRecvUtils {

    public static void udpRecv() throws Exception
    {
        DatagramSocket socket = new DatagramSocket(8070);

        byte [] buf = new byte [1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        socket.receive(packet);

        System.out.println("接受到的数据是 : "+new String(packet.getData()).trim());

        /**4、关闭资源*/
        socket.close();
    }

}
