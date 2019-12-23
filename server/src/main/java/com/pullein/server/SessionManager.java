package com.pullein.server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionManager{

    private ExecutorService service;

    public SessionManager() {
        service = Executors.newCachedThreadPool();
    }

    public void createSession(Socket socket) {
        service.execute(new Session(socket));
    }

    static class Session implements Runnable {
        private Socket socket;

        public Session(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg;
                while ((msg = reader.readLine()) != null) {
                    MsgBean msgBean = new Gson().fromJson(msg, MsgBean.class);
                    //收到消息之后发送到消息队列中等待处理
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}