package com.pullein.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainProgram {
    public static final int PORT = 9999;
    //会话结束标识
    public static final String TAG_EXIT = "[exit]";
    private static List<Socket> mList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //服务入口
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket client = null;
            while (true) {
                client = serverSocket.accept();
                mList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
