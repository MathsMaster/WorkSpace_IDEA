package com.mine.utils;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * created by xulp
 * on 2021/8/19
 * Socket服务端的版本
 */
public class SocketSeverUtils {

    public static void lanuchSever() {
        ServerSocket sSocket = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            sSocket = new ServerSocket(8888);
            System.out.println("主机名称 : " + InetAddress.getLocalHost());
            s = sSocket.accept();
            is = s.getInputStream();
            os = s.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is));
            bw = new BufferedWriter(new OutputStreamWriter(os));
            String str = br.readLine();
            System.out.println("客户端发来的数据 : " + str);
            bw.write("你好，我是服务器\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
                br.close();
                bw.close();
                s.close();
                sSocket.close();
            } catch (Exception e) {

            }

        }
    }
}
