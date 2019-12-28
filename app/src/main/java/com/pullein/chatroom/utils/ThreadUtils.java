package com.pullein.chatroom.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    private static ScheduledExecutorService executor;

    private static synchronized ScheduledExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newScheduledThreadPool(10);
        }
        return executor;
    }


    public static void runOnBackgroundThread(Runnable runnable){
        getExecutor().execute(runnable);
    }

    public static void runOnBackgroundThread(Runnable runnable, long delay) {
        getExecutor().schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

}
